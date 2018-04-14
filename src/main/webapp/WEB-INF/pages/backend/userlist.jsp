<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp" %>
<div>
    <ul class="breadcrumb">
        <li><a href="#">后台管理</a> <span class="divider">/</span></li>
        <li><a href="/backend/userlist.html">用户管理</a></li>
    </ul>
</div>

<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> 用户管理</h2>
            <div class="box-icon">
                <span id="adduser" class="icon32 icon-color icon-add custom-setting adduser"/>
            </div>
        </div>
        <div class="box-content"><%----%>
            <%--start搜索表单--%>
            <form action="/backend/userlist.html" method="post">
                <div class="searcharea">
                    账号:
                    <input type="text" name="s_loginCode" value="${s_loginCode}">
                    推荐人账号:
                    <input type="text" name="s_referCode" value="${s_referCode}">
                    角色:${s_roleId}
                    <select name="s_rodeId" style="width: 100px;">
                        <option value="" selected="selected">--请选择--${s_roleId}</option>
                        <c:forEach items="${roleList}" var="role">
                            <option <c:if test="${s_roleId == role.id}">selected</c:if>
                                    value="${role.id}">${role.roleName}</option>
                        </c:forEach>
                    </select>
                    是否启用:
                    <select name="s_isStart" style="width: 100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="1" <c:if test="${s_isStart==1}">selected="selected"</c:if>>启用</option>
                        <option value="2" <c:if test="${s_isStart==2}">selected="selected"</c:if>>未启用</option>
                    </select>
                    <button class="btn btn-primary"><i class="icon-search icon-white"></i> 查询</button>
                </div>
            </form>
            <%--end搜索表单--%>
            <%--start列表显示菜单--%>
            <table id="myTable" class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>用户账号</th>
                    <th>角色</th>
                    <th>会员类型</th>
                    <th>推荐人账号</th>
                    <th>启用状态(启用/禁用)</th>
                    <th>注册时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${empty page.items}">
                    <tr>
                        <td class="center" colspan="7">
                            <span class="label label-important">无任何数据</span>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${page.items!=null}">
                    <c:forEach items="${page.items}" var="user">
                        <tr>
                            <td class="center">${user.loginCode}</td>
                            <td class="center">
                                <span class="label
                                    <c:if test="${user.roleId==1}">label-important</c:if>
                                    <c:if test="${user.roleId==2}">label-success</c:if>
                                ">${user.roleName}</span>
                            </td>
                            <td class="center">${user.userTypeName}</td>
                            <td class="center">${user.referCode}</td>
                            <td class="center">
                                <input type="checkbox"
                                       <c:if test="${user.isStart==1}">checked="checked"</c:if> id="inlineCheckbox1"
                                       disabled="disabled">
                            </td>
                            <td class="center">
                                <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td class="center">
                                <a class="btn btn-success" href="#">
                                    <i class="icon-zoom-in icon-white"></i>
                                    查看
                                </a>
                                <a class="btn btn-info" href="#">
                                    <i class="icon-edit icon-white"></i>
                                    修改
                                </a>
                                <a class="btn btn-danger" href="#">
                                    <i class="icon-trash icon-white"></i>
                                    删除
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <%--end列表显示菜单--%>
            <%--start分页栏--%>
            <div class="pagination pagination-centered">
                <ul>
                    <c:choose>
                        <c:when test="${page.page==1}">
                            <li class="active"><a href="javascript:void();" title="首页">首页</a></li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="/backend/userlist.html?currentpage=1&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_isStart=${s_isStart}&s_roleId=${s_roleId}"
                                   title="首页">首页</a></li>
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${page.prevPages!=null}">
                        <c:forEach items="${page.prevPages}" var="num">
                            <li>
                                <a href="/backend/userlist.html?currentpage=${num}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_isStart=${s_isStart}&s_roleId=${s_roleId}"
                                   title="${num}">${num}</a></li>
                        </c:forEach>
                    </c:if>
                    <li class="active"><a href="javascript:void();" title="${page.page}">${page.page}</a></li>
                    <c:if test="${page.nextPages!=null}">
                        <c:forEach items="${page.nextPages}" var="num">
                            <li>
                                <a href="/backend/userlist.html?currentpage=${num}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_isStart=${s_isStart}&s_roleId=${s_roleId}"
                                   title="${num}">${num}</a></li>
                        </c:forEach>
                    </c:if>
                    <c:if test="${page.pageCount!=null}">
                        <c:choose>
                            <c:when test="${page.page==page.pageCount}">
                                <li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="/backend/userlist.html?currentpage=${page.pageCount}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_isStart=${s_isStart}&s_roleId=${s_roleId}"
                                       title="尾页">尾页</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${page.pageCount==null}">
                        <li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
                    </c:if>
                </ul>
            </div>
            <%--end分页栏--%>
        </div>

    </div><!--/span-->
</div>
<!--/row-->
<%--start添加的模式窗口--%>
<div class="modal hide fade" id="addUserDiv">
    <form action="/backend/adduser.html" method="post" enctype="multipart/form-data"
          onsubmit="return addUserFunction()">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>添加用户信息</h3>
            <div id="add_formtip"></div>
        </div>
        <div class="modal-body">
            <%--表单域--%>
            <ul class="topul">
                <li>
                    <label>角色：</label>
                    <input type="hidden" id="selectrolename" name="roleName" value=""/>
                    <select id="selectrole" name="roleId" style="width: 100px">
                        <option value="" selected>--请选择--</option>
                        <c:if test="${not empty roleList}">
                            <c:forEach items="${roleList}" var="role">
                                <option value="${role.id}">${role.roleName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <span style="color: red;font-weight: bold">*</span>
                </li>
                <%--角色--%>
                <li>
                    <label>会员类型：</label>
                    <input type="hidden" id="selectusertypename" name="userTypeName" value=""/>
                    <select id="selectusertype" name="userType" style="width: 100px">
                        <option value="" selected>--请选择--</option>
                    </select>
                </li>
                <%--会员类型--%>
                <li>
                    <label>用户名：</label> <%--onkeyup:不能输入中文的正则表达式--%>
                    <input type="text" id="a_logincode" name="loginCode"
                           onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--用户名--%>
                <li>
                    <label>姓名：</label><input type="text" id="a_username" name="userName"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--姓名--%>
                <li>
                    <label>性别：</label>
                    <select name="sex" style="width:100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="男">男</option>
                        <option value="女">女</option>
                    </select>
                </li>
                <%--性别--%>
                <li>
                    <label>证件类型：</label>
                    <input id="selectcardtypename" type="hidden" name="cardTypeName" value=""/>
                    <select id="selectcardtype" name="cardType" style="width:100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <c:if test="${cardTypeList != null}">
                            <c:forEach items="${cardTypeList}" var="cardType">
                                <option value="${cardType.valueId}">${cardType.valueName}</option>
                            </c:forEach>
                        </c:if>
                    </select>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--证件类型--%>
                <li>
                    <label>证件号码：</label>
                    <input type="text" id="a_idcard" name="idCard" onkeyup="value=value.replace(/[^\w\.\/]/ig,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--证件号码--%>
                <li>
                    <label>生日：</label>
                    <%--<input class="Wdate" id="a_birthday" size="15" name="birthday"
                           readonly="readonly" type="text" onClick="WdatePicker()"/>--%>
                    <input type="text" class="input-xlarge datepicker" id="a_birthday" name="birthday" value=""
                           readonly="readonly"/>
                </li>
                <%--生日--%>
                <li>
                    <label>收货国家：</label><input type="text" name="country" value="中国"/>
                </li>
                <%--收货国家--%>
                <li>
                    <label>联系电话：</label><input type="text" id="a_mobile" name="mobile"
                                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                                               onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--联系电话--%>
                <li>
                    <label>Email：</label>
                    <input type="text" id="a_email" name="email"/>
                </li>
                <%--email--%>
                <li>
                    <label>邮政编码：</label><input type="text" id="a_postCode" name="postCode"
                                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                                               onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                </li>
                <%--邮编--%>
                <li>
                    <label>开户行：</label><input type="text" id="a_bankname" name="bankName"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--开户行--%>
                <li>
                    <label>开户卡号：</label><input type="text" id="a_bankaccount" name="bankAccount"
                                               onkeyup="this.value=this.value.replace(/\D/g,'')"
                                               onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--开户卡号--%>
                <li>
                    <label>开户人：</label><input type="text" id="a_accountholder" name="accountHolder"/>
                    <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--开户人--%>
                <li>
                    <label>推荐人：</label><input type="text" name="referCode" value="${user.loginCode}"
                                              readonly="readonly"/>
                </li>
                <%--推荐人--%>
                <li>
                    <label>注册时间：</label>
                    <input type="text" id="a_cdate" value="" readonly="readonly"/>
                </li>
                <%--注册时间--%>
                <li>
                    <label>是否启用：</label>
                    <select name="isStart" style="width:100px;">
                        <option value="1" selected="selected">启用</option>
                        <option value="2">不启用</option>
                    </select> <span style="color:red;font-weight: bold;">*</span>
                </li>
                <%--是否启用--%>
                <li class="lastli">
                    <label>收货地址：</label><textarea id="a_useraddress" name="userAddress"></textarea>
                </li>
                <%--收货地址--%>

            </ul>
            <div class="clear"></div>
            <ul class="downul">
                <li>
                    <label>上传身份证图片
                        (正反面)：</label>
                    <input type="hidden" id="v_fileInputIDPath" value=""/>
                    <div id="v_idPic"></div>
                </li>
            </ul>
            <ul class="downul">
                <li>
                    <label>上传银行卡图片：</label>
                    <input type="hidden" id="v_fileInputBankPath" value=""/>
                    <div id="v_bankPic"></div>
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal">取消</a>
            <input type="submit" class="btn btn-primary" value="保存"/>
        </div>
    </form>

</div>
<%--end添加的模式窗口--%>
<%@include file="/WEB-INF/pages/common/foot.jsp" %>

<script type="text/javascript" src="/statics/localjs/userlist.js"></script>