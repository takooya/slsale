package org.slsale.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.slsale.common.*;
import org.slsale.pojo.DataDictionary;
import org.slsale.pojo.Role;
import org.slsale.pojo.User;
import org.slsale.service.DataDictionaryService;
import org.slsale.service.RoleService;
import org.slsale.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author takooya
 * @Description
 * @Date:created in 20:57 2018/4/10
 */
@Controller
@Slf4j
public class UserController extends BaseController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisAPI redis;
    @Autowired
    private DataDictionaryService dataDictionaryService;

    /**
     * 修改本人密码
     */
    @RequestMapping(value = "/backend/modify.html", method = RequestMethod.POST)
    @ResponseBody
    public Object modify(HttpSession session, @RequestParam String info) {
        if (null == info || "".equals(info)) {
            return "nodata";
        }
        JSONObject jsonObject = JSONObject.parseObject(info);
        String oldPassword = (String) jsonObject.get("old");
        String newPassword = (String) jsonObject.get("new");
        String confirm = (String) jsonObject.get("confirm");
        if (!newPassword.equals(confirm)) {
            return "failed";
        }
        try {
            Object targetObject = session.getAttribute(Constants.SESSION_USER);
            User targetUser = null;
            if (targetObject == null) {
                return "nologincode";
            } else if (!(targetObject instanceof User)) {
                return "failed";
            } else {
                targetUser = (User) targetObject;
            }
            targetUser.setPassword(oldPassword);
            User confirmUser = userService.getLoginUser(targetUser);
            if (null == confirmUser) {
                return "pwderror";
            }
            confirmUser.setPassword(newPassword);
            confirmUser.setLastUpdateTime(new Date());
            userService.modifyUser(confirmUser);
            session.setAttribute(Constants.SESSION_USER, confirmUser);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        return "success";
    }

    /**
     * 获得用户列表
     */
    @RequestMapping(value = "/backend/userlist.html")
    public ModelAndView userlist(Model model, HttpSession session,
                                 @RequestParam(value = "s_loginCode", required = false) String s_loginCode,
                                 @RequestParam(value = "s_referCode", required = false) String s_referCode,
                                 @RequestParam(value = "s_rodeId", required = false) Integer s_rodeId,
                                 @RequestParam(value = "s_isStart", required = false) Integer s_isStart,
                                 @RequestParam(value = "currentpage", required = false) Integer currentpage) {
        Map<String, Object> baseModel = (Map<String, Object>) session.getAttribute(Constants.SESSION_BASE_MODEL);
        log.error("前台传来了数据如下:\ns_loginCode={}\ns_referCode={}\ns_rodeId={}\ns_isStart={}", s_loginCode, s_referCode, s_rodeId, s_isStart);
        s_loginCode = s_loginCode != null ? s_loginCode.trim() : null;
        s_referCode = s_referCode != null ? s_referCode.trim() : null;
        if (model == null) {
            return new ModelAndView("redirect:/");
        } else {
            //获取roleList
            List<Role> roleList = null;
            try {
                if (!redis.exicts("roleList")) {
                    roleList = roleService.getRoleList();
                    redis.set("roleList", JSONObject.toJSONString(roleList));
                } else {
                    try {
                        roleList = JSONObject.parseArray(redis.get("roleList"), Role.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        roleList = roleService.getRoleList();
                        redis.set("roleList", JSONObject.toJSONString(roleList));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            log.error("roleList:", roleList);
            //获取cardTypeList(在DataDictionary表中)
            List<DataDictionary> cardTypeList = null;

            if (!redis.exicts("cardTypeList")) {
                DataDictionary dataDictionary = new DataDictionary();
                dataDictionary.setTypeCode("CARD_TYPE");
                try {
                    cardTypeList = dataDictionaryService.getDataDictionaries(dataDictionary);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                redis.set("cardTypeList", JSONObject.toJSONString(cardTypeList));
            } else {
                try {
                    cardTypeList = JSONObject.parseArray(redis.get("cardTypeList"), DataDictionary.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    DataDictionary dataDictionary = new DataDictionary();
                    dataDictionary.setTypeCode("CARD_TYPE");
                    try {
                        cardTypeList = dataDictionaryService.getDataDictionaries(dataDictionary);
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    redis.set("cardTypeList", JSONObject.toJSONString(cardTypeList));
                }
            }
            //查询用户功能
            User user = new User();
            if (null != s_loginCode && !"".equals(s_loginCode)) {
                user.setLoginCode("%" + SQLTools.transfer(s_loginCode) + "%");
            }
            if (null != s_referCode && !"".equals(s_referCode)) {
                user.setReferCode("%" + SQLTools.transfer(s_referCode) + "%");
            }
            if (null != s_rodeId) {
                //start获取roleroleList中的idList
                Integer[] roleIds = new Integer[roleList.size()];
                for (int i = 0; i < roleIds.length; i++) {
                    roleIds[i] = roleList.get(i).getId();
                }
                //end获取roleroleList中的idList
                if (Arrays.binarySearch(roleIds, s_rodeId) >= 0) {
                    user.setRoleId(s_rodeId);
                }
            }
            if (null != s_isStart && Arrays.binarySearch(new Integer[]{1, 2}, s_isStart) >= 0) {
                user.setIsStart(s_isStart);
            }
            log.error("分页功能前s_roleId:{}", s_rodeId);
            //分页功能
            PageSupport page = new PageSupport();
            try {
                page.setTotalCount(userService.count(user));
            } catch (Exception e) {
                e.printStackTrace();
                page.setTotalCount(0);
            }
            if (page.getTotalCount() > 0) {
                if (currentpage != null) {
                    page.setPage(currentpage);
                } else {
                    page.setPage(1);
                }
                if (page.getPage() <= 0) {
                    page.setPage(1);
                }
                if (page.getPage() > page.getPageCount()) {
                    page.setPage(page.getPageCount());
                }

            } else {
                page.setItems(null);
                page.setPage(1);
            }
            //mysql--分页查询limit?(起始下标:(当前页-1)*页容量),?(页容量)
            user.setStartNum((page.getPage() - 1) * page.getPageSize());
            user.setPageSize(page.getPageSize());
            List<User> userList = null;
            try {
                userList = userService.getUserList(user);
            } catch (Exception e) {
                e.printStackTrace();
                userList = null;
                if (page == null) {
                    page = new PageSupport();
                    page.setItems(null);
                }
            }
            page.setItems(userList);
            model.addAllAttributes(baseModel);
            model.addAttribute("roleList", roleList);
            model.addAttribute("cardTypeList", cardTypeList);
            model.addAttribute("page", page);
            log.error("前台传来了数据如下:\ns_loginCode={}\ns_referCode={}\ns_rodeId={}\ns_isStart={}", s_loginCode, s_referCode, s_rodeId, s_isStart);
            model.addAttribute("s_rodeId", s_rodeId);
            model.addAttribute("s_loginCode", s_loginCode);
            model.addAttribute("s_referCode", s_referCode);
            model.addAttribute("s_isStart", s_isStart);
            return new ModelAndView("backend/userlist");
        }
    }

    /**
     * 加载用户类型列表
     */
    @RequestMapping(value = "/backend/loadUserTypeList.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String loadUserTypeList(@RequestParam(value = "s_roleId", required = false) String s_role) {
        String cjson = "";
        if (redis.exicts("USER_TYPE")) {
            cjson = redis.get("USER_TYPE");
        } else {
            try {
                DataDictionary data = new DataDictionary();
                data.setTypeCode("USER_TYPE");
                List<DataDictionary> userTypes = dataDictionaryService.getDataDictionaries(data);
                cjson = JSONObject.toJSONString(userTypes);
                redis.set("USER_TYPE", cjson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cjson;
    }


    /**
     * 判断logincode是否存在
     */
    @RequestMapping(value = "/backend/logincodeisexist.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String logincodeisexist(
            @RequestParam(value = "loginCode", required = false) String loginCode,
            @RequestParam(value = "id", required = false) Integer id) {
        String result = "failed";
        User formUser = new User();
        formUser.setLoginCode(loginCode);
        if (!id.equals(-1)) {//不等于-1,为修改操作
            formUser.setId(-1);
        }
        try {
            if (userService.loginCodeIsExist(formUser) == 0) {//用户不存在
                result = "only";
            } else {//用户存在(重复)
                result = "repeat";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    /**
     * 管理员添加用户
     */
    @RequestMapping(value = "/backend/adduser.html", method = RequestMethod.POST)
    public ModelAndView adduser(HttpSession session,
                                @ModelAttribute("addUser") User addUser,
                                @RequestParam(value = "birthday") String birthday) {
        if (session.getAttribute(Constants.SESSION_BASE_MODEL) == null) {
            return new ModelAndView("redirect:/");
        } else {
            try {
                String idCard = addUser.getIdCard();
                String ps = idCard.substring(idCard.length() - 6);
                addUser.setPassword(ps);
                addUser.setPassword2(ps);
                addUser.setCreateTime(new Date());
                addUser.setReferId(this.getCurrentUser().getId());
                addUser.setReferCode(this.getCurrentUser().getReferCode());
                addUser.setLastUpdateTime(new Date());
                Date birthdayDate = (new SimpleDateFormat("MM/dd/yyyy")).parse(birthday);
                addUser.setBirthday(birthdayDate);
                userService.addUser(addUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ModelAndView("redirect:/backend/userlist.html");
        }
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "/backend/upload.html", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public Object upload(@RequestParam(value = "a_fileInputID", required = false) MultipartFile cardFile,
                         @RequestParam(value = "a_fileInputBank", required = false) MultipartFile aBankFile,
                         @RequestParam(value = "m_fileInputID", required = false) MultipartFile mIdFile,
                         @RequestParam(value = "m_fileInputBank", required = false) MultipartFile mBankFile,
                         @RequestParam(value = "loginCode", required = false) String loginCode,
                         HttpSession session, HttpServletRequest request) {
        log.error("[进入文件上传方法]loginCode:{}", loginCode);
        //创建服务器存放图片的路径
        String path = session.getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
        log.error("[进入文件上传方法]新建的path:{}", path);
        DataDictionary dataDictionary = new DataDictionary();
        dataDictionary.setTypeCode("PERSONALFILE_SIZE");
        List<DataDictionary> list = null;
        try {
            //从数据库获得上传文件大小的限制
            list = dataDictionaryService.getDataDictionaries(dataDictionary);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int filesize = 50000;
        if (list != null) {
            filesize = list.size() == 1 ? Integer.valueOf(list.get(0).getValueName()) : filesize;
        }
        //上传图片的文件对象
        if (cardFile != null) {
            //获得的是客户端的文件名,例如:img.jpg(在前端我们已经限制不传客户端的路径)
            String oldFilename = cardFile.getOriginalFilename();
            String suffix = FilenameUtils.getExtension(oldFilename);
            log.error("[进入文件上传方法]获得的客户端文件名:{}", oldFilename);
            log.error("[进入文件上传方法]获得的客户端文件拓展名:{}", suffix);
            //如果上传文件大小超出我们的限制
            if (cardFile.getSize() > filesize) {
                return "1";
                //下面是判断上传文件的后缀名(判断文件类型)
            } else if (suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("png")
                    || suffix.equalsIgnoreCase("jpeg") || suffix.equalsIgnoreCase("pneg")) {
                //源文件(存放在服务器时的文件名)重命名:系统毫秒数+100W以内随机数
                String fileName = System.currentTimeMillis() + "" +
                        new Random().nextInt(900000) + 100000 + "_IDcard." + suffix;
                log.error("[进入文件上传方法]新建的fileName:{}", fileName);
                File targetFile = new File(path, fileName);
                //判断文件是否存在
                if (!targetFile.exists()) {
                    //创建一个空文件
                    targetFile.mkdirs();
                }
                //上传操作
                try {
                    //将前台接收的cardFile内容transfer给targetFIle
                    cardFile.transferTo(targetFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //获取图片在服务器的(工程路径+文件名)
                String url = request.getContextPath() + "/statics/uploadfiles/" + fileName;
                return url;
            } else {
                return "2";//格式不正确
            }
        }
        return null;
    }

    /**
     * 删除图片
     */
    @RequestMapping(value = "/backend/delpic.html", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String delpic(@RequestParam(value = "picpath") String picpath,
                         @RequestParam(value = "id") Integer id,
                         HttpSession session) {
        //初始化为:删除失败
        String result = "failed";
        //当传入的图片路径为空,返回success
        if (picpath == null || picpath.trim().equals("")) {
            return "success";
        } else {//解析图片路径
            String[] paths = picpath.split("/");
            log.warn("解析图片路径,整体输出:{}", paths);
            for (String path : paths) {
                log.warn("解析图片路径,分别输出:{}", path);
            }
            //path[0]:是一个空字符串;path[1]:statics,path[2]:文件名,path[3]:拓展名
            String path = session.getServletContext().getRealPath(paths[1] + File.separator + paths[2] + File.separator + paths[3]);
            File file = new File(path);
            if (file.exists()) {
                //删除图片
                if (file.delete()) {
                    if (id.equals("0")) {//增加页面,删除上传图片
                        result = "success";
                    } else {//修改页面图,删除上传图片
                        User user = new User();
                        user.setId(Integer.valueOf(id));
                        if (picpath.indexOf("_IDcard") != -1) {
                            user.setIdCardPicPath(picpath);
                        } else if (picpath.indexOf("_bank") != -1) {
                            user.setBankPicPath(picpath);
                        }
                        //将user赋值并修改原数据
                    }
                }
            }
        }
        return result;
    }

    /**
     * 管理员查看某个用户信息
     */
    @RequestMapping(value = "/backend/getuser.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    @ResponseBody
    public String getuser(@RequestParam(value = "id") Integer id,
                          HttpSession session) {
        String result = "failed";
        if (id == null) {
            return "nodata";
        }
        User user = new User();
        user.setId(id);
        User v_user = null;
        try {
            v_user = userService.getUserById(user);
        } catch (Exception e) {
            e.printStackTrace();
            return "failed";
        }
        if (v_user == null) {
            return "nodata";
        }
        //格式化显示的时间
        result = JSON.toJSONStringWithDateFormat(v_user, "yyyy-MM-dd");
        return result;
    }

    /**
     * 管理员修改用户
     */
    @RequestMapping(value = "/backend/modifyuser.html", produces = "text/html;charset=UTF-8", method = RequestMethod.POST)
    public ModelAndView modifyuser(HttpSession session, @ModelAttribute("modifyUser") User modifyuser) {
        log.warn("modifyuser:{}", modifyuser);
        if (session.getAttribute(Constants.SESSION_BASE_MODEL) == null) {
            return new ModelAndView("redirect:/");
        } else {
            if (modifyuser == null) {
                return new ModelAndView("/backend/userlist.html");
            } else {
                try {
                    User targetUser = null;
                    log.error("getBirthday无法获取:{}", modifyuser.getBirthday());
                    targetUser = userService.getUserById(modifyuser);
                    //Bean数据的copy方法,注意被copy的对象不是同类型也可以使用
                    //使用此方法注意两个bean的属性名要一致
                    //此方法取两个对象属性名相同的合集(相同的部分)赋值
                    BeanUtils.copyProperties(modifyuser, targetUser, ForBeanUtils.getNullPropertiesName(modifyuser));
                    if (userService.modifyUser(targetUser) <= 0) {
                        log.warn("复制后的目标user:{}", targetUser);
                        return new ModelAndView("redirect:/backend/userlist.html");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ModelAndView("redirect:/backend/userlist.html");
                }
                return new ModelAndView("forward:/backend/userlist.html");
            }
        }
    }

    @RequestMapping(value = "/backend/deluser.html")
    @ResponseBody
    public String deluser(@RequestParam(value = "delId") Integer delId,
                          @RequestParam(value = "delIdCardPicPath") String delIdCardPicPath,
                          @RequestParam(value = "delBankPicPath") String delBankPicPath,
                          @RequestParam(value = "delUserType") Integer delUserType,
                          HttpSession session) {
        User delUser=new User();
        delUser.setId(delId);
        if (session.getAttribute(Constants.SESSION_BASE_MODEL) == null) {
            return "redirect:/";
        } else {
            if (delUserType == 2 || delUserType == 3 || delUserType == 4) {
                return "notallow";
            }
            if (this.delpic(delIdCardPicPath, delId, session).equals("success") && this.delpic(delBankPicPath, delId, session).equals("success")) {
                try {
                    if(userService.deleteUser(delUser) > 0)
                        return "success";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "failed";
                }
            }
        }
        return "failed";
    }
}
