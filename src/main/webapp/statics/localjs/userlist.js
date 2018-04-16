function checkEmail(str) {
    var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    if (str == null || str == "" || reg.test(str))
        return true;
    else
        return false;
}


$('.viewusercancel').click(function (e) {
    $("#v_idPic").html('');
    $("#v_bankPic").html('');
    $("#v_isstart").html('');
});

$('.modifyusercancel').click(function (e) {
    $("#m_idPic").html('');
    $("#m_bankPic").html('');
    $("#modify_formtip").html('');
    $("#m_isstart").html('');
});

$('.addusercancel').click(function (e) {
    $("#add_formtip").html('');
    $("#a_idPic").html('');
    $("#a_bankPic").html('');
    $("#selectrole").val('');
    $("#selectusertype").val('');
    $("#selectusertype").html('<option value=\"\" selected=\"selected\">--请选择--</option>');
    $("#a_logincode").val('');
    $("#a_username").val('');
    $("#selectcardtype").val('');
    $("#a_idcard").val('');
    $("#a_mobile").val('');
    $("#a_email").val('');
    $("#a_postCode").val('');
    $("#a_bankname").val('');
    $("#a_bankaccount").val('');
    $("#a_accountholder").val('');
    $("#a_useraddress").val('');
});

$('.viewuser').click(function (e) {
    var m_id = $(this).attr('id');
    $.ajax({
        url: '/backend/getuser.html',
        type: 'POST',
        data: {id: m_id},
        dataType: 'html',
        timeout: 1000,
        error: function () {
            alert("error");
        },
        success: function (result) {
            if ("failed" == result) {
                alert("操作超时！");
            } else if ("nodata" == result) {
                alert("没有数据！");
            } else {
                m = eval('(' + result + ')');
                $("#v_id").val(m.id);
                $("#v_logincode").val(m.loginCode);
                $("#v_username").val(m.userName);
                $("#v_birthday").val(m.birthday);
                $("#v_cardtypename").val(m.cardTypeName);
                $("#v_rolename").val(m.roleName);
                $("#v_usertypename").val(m.userTypeName);
                $("#v_sex").val(m.sex);
                $("#v_idcard").val(m.idCard);
                $("#v_country").val(m.country);
                $("#v_mobile").val(m.mobile);
                $("#v_email").val(m.email);
                $("#v_postcode").val(m.postCode);
                $("#v_bankaccount").val(m.bankAccount);
                $("#v_accountholder").val(m.accountHolder);
                $("#v_bankname").val(m.bankName);
                $("#v_createtime").val(m.createTime);
                var isstart = m.isStart;
                if (isstart == '1') {
                    $("#v_isstart").append("<option value=\"1\" selected=\"selected\">启用</option><option value=\"2\">不启用</option>");
                } else {
                    $("#v_isstart").append("<option value=\"1\">启用</option><option value=\"2\" selected=\"selected\">不启用</option>");
                }

                $("#v_useraddress").val(m.userAddress);
                $("#v_refercode").val(m.referCode);

                $("#v_fileInputIDPath").val(m.idCardPicPath);
                var v_idcardpicpath = m.idCardPicPath;
                if (v_idcardpicpath == null || v_idcardpicpath == "") {
                    $("#v_idPic").append("暂无");
                } else {
                    $("#v_idPic").append("<p><img src=\"" + v_idcardpicpath + "?m=" + Math.random() + "\" /></p>");
                }

                $("#v_fileInputBankPath").val(m.bankPicPath);
                var v_bankpicpath = m.bankPicPath;
                if (v_bankpicpath == null || v_bankpicpath == "") {
                    $("#v_bankPic").append("暂无");
                } else {
                    $("#v_bankPic").append("<p><img src=\"" + v_bankpicpath + "?m=" + Math.random() + "\" /></p>");
                }
                e.preventDefault();
                $('#viewUserDiv').modal('show');
            }
        }
    });
});

$('.modifyuser').click(function (e) {
    var m_id = $(this).attr('id');
    $.ajax({
        url: '/backend/getuser.html',
        type: 'POST',
        data: {id: m_id},
        dataType: 'html',
        timeout: 1000,
        error: function () {
            alert("error");
        },
        success: function (result) {
            if ("failed" == result) {
                alert("操作超时！");
            } else if ("nodata" == result) {
                alert("没有数据！");
            } else {
                m = eval('(' + result + ')');
                $("#m_id").val(m.id);
                $("#m_logincode").val(m.loginCode);
                $("#m_username").val(m.userName);
                $("#m_birthday").val(m.birthday);
                $("#m_cardtype").val(m.cardType);
                $("#m_cardtypename").val(m.cardTypeName);
                var cardType = m.cardType;
                var cardTypeName = m.cardTypeName;
                $("#m_cardtype").html('');
                if (cardType == null || cardType == "")
                    $("#m_cardtype").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
                for (var i = 0; i < cardTypeListJson.length - 1; i++) {
                    if (cardTypeListJson[i].valueId == cardType) {
                        $("#m_cardtype").append("<option value=\"" + cardType + "\" selected=\"selected\">" + cardTypeName + "</option>");
                    } else {
                        $("#m_cardtype").append("<option value=\"" + cardTypeListJson[i].valueId + "\">" + cardTypeListJson[i].valueName + "</option>");
                    }
                }
                $("#m_roleId").html('');
                $("#m_rolename").val(m.roleName);
                var roleId = m.roleId;
                var roleName = m.roleName;
                if (roleId == null || roleId == "")
                    $("#m_roleId").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
                for (var i = 0; i < roleListJson.length - 1; i++) {
                    if (roleListJson[i].id == roleId) {
                        $("#m_roleId").append("<option value=\"" + roleId + "\" selected=\"selected\">" + roleName + "</option>");
                    } else {
                        $("#m_roleId").append("<option value=\"" + roleListJson[i].id + "\">" + roleListJson[i].roleName + "</option>");
                    }
                }
                $("#m_selectusertypename").val(m.userTypeName);
                $("#m_selectusertype").html('');
                if (roleId == '2') {
                    var userType = m.userType;
                    var userTypeName = m.userTypeName;
                    if (userType == null || userType == "")
                        $("#m_selectusertype").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
                    $.post("/backend/loadUserTypeList.html", {'s_role': roleId}, function (result) {
                        if (result != "") {
                            for (var i = 0; i < result.length; i++) {
                                if (result[i].valueId == userType) {
                                    $("#m_selectusertype").append("<option value=\"" + userType + "\" selected=\"selected\">" + userTypeName + "</option>");
                                } else {
                                    $("#m_selectusertype").append("<option value=\"" + result[i].valueId + "\">" + result[i].valueName + "</option>");
                                }
                            }
                        } else {
                            alert("用户类型加载失败！");
                        }
                    }, 'json');

                } else if (roleId == "1") {
                    $("#m_selectusertype").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
                }
                var sex = m.sex;
                $("#m_sex").html('');
                if (sex == '') {
                    $("#m_sex").append("<option value=\"\" selected=\"selected\">--请选择--</option><option value=\"男\">男</option><option value=\"女\">女</option>");
                } else if (sex == '男') {
                    $("#m_sex").append("<option value=\"男\" selected=\"selected\">男</option><option value=\"女\">女</option>");
                } else if (sex == "女") {
                    $("#m_sex").append("<option value=\"男\">男</option><option value=\"女\" selected=\"selected\">女</option>");
                }

                $("#m_idcard").val(m.idCard);
                $("#m_country").val(m.country);
                $("#m_mobile").val(m.mobile);
                $("#m_email").val(m.email);
                $("#m_postcode").val(m.postCode);
                $("#m_bankaccount").val(m.bankAccount);
                $("#m_accountholder").val(m.accountHolder);
                $("#m_bankname").val(m.bankName);
                $("#m_createtime").val(m.createTime);
                var isstart = m.isStart;
                if (isstart == '1') {
                    $("#m_isstart").append("<option value=\"1\" selected=\"selected\">启用</option><option value=\"2\">不启用</option>");
                } else {
                    $("#m_isstart").append("<option value=\"1\">启用</option><option value=\"2\" selected=\"selected\">不启用</option>");
                }
                $("#m_useraddress").val(m.userAddress);
                $("#m_refercode").val(m.referCode);

                $("#m_fileInputIDPath").val(m.idCardPicPath);
                var m_idcardpicpath = m.idCardPicPath;
                if (m_idcardpicpath == null || m_idcardpicpath == "") {
                    $("#m_uploadbtnID").show();

                } else {
                    $("#m_idPic").append("<p><span onclick=\"delpic('" + m.id + "','m_idPic','m_uploadbtnID',this,'" + m_idcardpicpath + "','m_fileInputIDPath','m_fileInputID');\">x</span><img src=\"" + m_idcardpicpath + "?m=" + Math.random() + "\" /></p>");
                    $("#m_uploadbtnID").hide();
                }

                $("#m_fileInputBankPath").val(m.bankPicPath);
                var m_bankpicpath = m.bankPicPath;
                if (m_bankpicpath == null || m_bankpicpath == "") {
                    $("#m_uploadbtnBank").show();

                } else {
                    $("#m_bankPic").append("<p><span onclick=\"delpic('" + m.id + "','m_bankPic','m_uploadbtnBank',this,'" + m_bankpicpath + "','m_fileInputBankPath','m_fileInputBank');\">x</span><img src=\"" + m_bankpicpath + "?m=" + Math.random() + "\" /></p>");
                    $("#m_uploadbtnBank").hide();
                }
                e.preventDefault();
                $('#modifyUserDiv').modal('show');
            }
        }
    });
});

$('.deluser').click(function (e) {
    //delete
    var d = $(this);
    var d_usertype = d.attr('usertype');
    var d_usertypename = d.attr('usertypename');
    var d_logincode = d.attr('logincode');
    var d_id = d.attr('id');
    var d_idcardpicpath = d.attr('idcardpicpath');
    var d_bankpicpath = d.attr('bankpicpath');
    if (confirm("您确定要删除【" + d_logincode + "】这个用户吗？")) {
        //delete
        $.post("/backend/deluser.html", {
            'delId': d_id,
            'delIdCardPicPath': d_idcardpicpath,
            'delBankPicPath': d_bankpicpath,
            'delUserType': d_usertype
        }, function (result) {
            if ("success" == result) {
                alert("删除成功！");
                window.location.href = "/backend/userlist.html";
            } else if ("noallow" == result) {
                alert("该用户类型为：【" + d_usertypename + "】，不允许被删除！");
            } else {
                alert("删除失败！");
            }
        }, 'html');
    }
});


//对 会员类型 的隐藏域中赋值
$("#selectusertype").change(function () {
    $("#selectusertypename").val($("#selectusertype").find("option:selected").text());
});
//对 证件类型 的隐藏域中赋值
$("#selectcardtype").change(function () {
    $("#selectcardtypename").val($("#selectcardtype").find("option:selected").text());
});

$("#m_cardtype").change(function () {
    $("#m_cardtypename").val($("#m_cardtype").find("option:selected").text());
});

$("#m_roleId").change(function () {
    $("#m_rolename").val($("#m_roleId").find("option:selected").text());
    $("#m_selectusertype").empty();
    $("#m_selectusertype").append("<option value=\"\" selected=\"selected\">--请选择--</option>");
    var sel_role = $("#m_roleId").val();
    if (sel_role == 2) {
        $.post("/backend/loadUserTypeList.html", {'s_role': sel_role}, function (result) {
            if (result != "") {
                for (var i = 0; i < result.length; i++) {
                    $("#m_selectusertype").append("<option value=\"" + result[i].valueId + "\">" + result[i].valueName + "</option>");
                }
            } else {
                alert("用户类型加载失败！");
            }
        }, 'json');
    } else {
        $("#m_selectusertypename").val('');
    }
});

$("#m_selectusertype").change(function () {
    $("#m_selectusertypename").val($("#m_selectusertype").find("option:selected").text());
});
// (上传按钮)上传身份证照片,增加的时候
$("#a_uploadbtnID").click(function () {
    //0:表示添加,a_fileInputID:表示文件表单域,a_uploadbtnID:表示上传按钮,a_idPic:显示图片的区域,a_fileInputIDPath:记录文件路径的隐藏域
    TajaxFileUpload('0', 'a_fileInputID', 'a_uploadbtnID', 'a_idPic', 'a_fileInputIDPath');
});
// (上传按钮)上传银行卡照片,增加的时候
$("#a_uploadbtnBank").click(function () {
    TajaxFileUpload('0', 'a_fileInputBank', 'a_uploadbtnBank', 'a_bankPic', 'a_fileInputBankPath');
});

$("#m_uploadbtnID").click(function () {
    TajaxFileUpload($("#m_id").val(), 'm_fileInputID', 'm_uploadbtnID', 'm_idPic', 'm_fileInputIDPath');
});

$("#m_uploadbtnBank").click(function () {
    TajaxFileUpload($("#m_id").val(), 'm_fileInputBank', 'm_uploadbtnBank', 'm_bankPic', 'm_fileInputBankPath');
});

$("#selectrole").change(function () {
    //对 角色 的隐藏域中赋值
    $("#selectrolename").val($("#selectrole").find("option:selected").text());
//添加用户时,通过角色选择,异步加载用户类型
    $("#selectusertype").empty();
    $("#selectusertype").append("<option value='' selected='selected'>--请选择--</option>");
    var sel_role = $("#selectrole").val();
    if (sel_role == 2) {
        //ajax:post方法形式:jQuery.post(url,data,success(data, textStatus, jqXHR),dataType)
        $.post("/backend/loadUserTypeList.html", {'s_roleId': sel_role}, function (result) {
            //result为/backend/loadUserTypeList.html方法返回的List<DataDictionary>
            if (result != "") {
                for (var i = 0; i < result.length; i++) {
                    $("#selectusertype").append("<option value='" + result[i].valueId + "'>" + result[i].valueName + "</option>");
                }
            } else {
                alert("用户类型加载失败！");
            }
        }, 'json');
    }
});
//增加用户时,判断是否重名
$("#a_logincode").blur(function () {
    var alc = $("#a_logincode").val();
    if (alc != "") {
        //ajax:post方法形式:jQuery.post(url,data,success(data, textStatus, jqXHR),dataType)
        $.post("/backend/logincodeisexist.html", {'loginCode': alc, 'id': '-1'}, function (result) {
            if (result == "repeat") {
                $("#add_formtip").css("color", "red");
                $("#add_formtip").html("<li>对不起，该用户名已存在。</li>");
                $("#add_formtip").attr("key", "1");
                ;
                result = false;
            } else if (result == "failed") {
                alert("操作超时!");
            } else if (result == "only") {
                $("#add_formtip").css("color", "green");
                $("#add_formtip").html("<li>该用户名可以正常使用。</li>");
                $("#add_formtip").attr("key", "0");
            }
        }, 'html');
    }
});
//修改用户时,判断是否重名
$("#m_logincode").blur(function () {
    var mlc = $("#m_logincode").val();
    if (mlc != "") {
        $.post("/backend/logincodeisexit.html", {'loginCode': mlc, 'id': $("#m_id").val()}, function (result) {
            if (result == "repeat") {
                $("#modify_formtip").css("color", "red");
                $("#modify_formtip").html("<li>对不起，该用户名已存在。</li>");
                $("#modify_formtip").attr("key", "1");
                result = false;
            } else if (result == "failed") {
                alert("操作超时!");
            } else if (result == "only") {
                $("#modify_formtip").css("color", "green");
                $("#modify_formtip").html("<li>该用户名可以正常使用。</li>");
                $("#modify_formtip").attr("key", "0");
            }
        }, 'html');
    }
});

//添加用户时,对email进行验证,验证的具体方法在第一行,即checkEmail方法
$("#a_email").blur(function () {
    var flag = checkEmail($("#a_email").val());
    if (flag == false) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").html("<li>email格式不正确</li>");
        $("#add_formtip").attr("email", "1");
    } else {
        $("#add_formtip").html("");
        $("#add_formtip").attr("email", "0");
    }
});

$("#m_email").blur(function () {
    var flag = checkEmail($("#m_email").val());
    if (flag == false) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").html("<li>email格式不正确</li>");
        $("#modify_formtip").attr("email", "1");
    } else {
        $("#modify_formtip").html("");
        $("#modify_formtip").attr("email", "0");
    }
});


//添加用户信息验证,返回boolean,即是否可请求
function addUserFunction() {
    $("#add_formtip").html("");
    var result = true;//可递交状态
    if ($("#selectrole").val() == "") {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，角色不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_logincode").val()) == "" || $("#a_logincode").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，用户名不能为空。</li>");
        result = false;
    } else {
        if ($("#add_formtip").attr("key") == "1") {
            $("#add_formtip").css("color", "red");
            $("#add_formtip").append("<li>对不起，该用户名已存在。</li>");
            result = false;
        }
    }
    if ($.trim($("#a_username").val()) == "" || $("#a_username").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，真实姓名不能为空。</li>");
        result = false;
    }
    if ($("#selectcardtype").val() == "") {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，证件类型不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_idcard").val()) == "" || $("#a_idcard").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，证件号码不能为空。</li>");
        result = false;
    } else {
        if ($("#a_idcard").val().length < 6) {
            $("#add_formtip").css("color", "red");
            $("#add_formtip").append("<li>对不起，证件号码长度必须超过6位。</li>");
            result = false;
        }
    }
    if ($.trim($("#a_mobile").val()) == "" || $("#a_mobile").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，联系电话不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_bankname").val()) == "" || $("#a_bankname").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，开户行不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_bankaccount").val()) == "" || $("#a_bankaccount").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，开户卡号不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_accountholder").val()) == "" || $("#a_accountholder").val() == null) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，开户人不能为空。</li>");
        result = false;
    }
    if ($.trim($("#a_email").val()) != "" && $("#a_email").val() != null && $("#add_formtip").attr("email") == "1") {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>email格式不正确</li>");
        result = false;
    }
    if (result == true) alert("添加成功 ^_^");
    return result;
}

//修改用户信息验证
function modifyUserFunction() {
    $("#modify_formtip").html("");
    var result = true;
    if ($("#m_roleId").val() == "") {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，角色不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_logincode").val()) == "" || $("#m_logincode").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，用户名不能为空。</li>");
        result = false;
    } else {
        if ($("#modify_formtip").attr("key") == "1") {
            $("#modify_formtip").append("<li>对不起，该用户名已存在。</li>");
            result = false;
        }
    }
    if ($.trim($("#m_username").val()) == "" || $("#m_username").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，真实姓名不能为空。</li>");
        result = false;
    }
    if ($("#m_cardtype").val() == "") {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，证件类型不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_idcard").val()) == "" || $("#m_idcard").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，证件号码不能为空。</li>");
        result = false;
    } else {
        if ($("#m_idcard").val().length < 6) {
            $("#modify_formtip").css("color", "red");
            $("#modify_formtip").append("<li>对不起，证件号码长度必须超过6位。</li>");
            result = false;
        }
    }
    if ($.trim($("#m_mobile").val()) == "" || $("#m_mobile").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，联系电话不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_bankname").val()) == "" || $("#m_bankname").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，开户行不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_bankaccount").val()) == "" || $("#m_bankaccount").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，开户卡号不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_accountholder").val()) == "" || $("#m_accountholder").val() == null) {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>对不起，开户人不能为空。</li>");
        result = false;
    }
    if ($.trim($("#m_email").val()) != "" && $("#m_email").val() != null && $("#modify_formtip").attr("email") == "1") {
        $("#modify_formtip").css("color", "red");
        $("#modify_formtip").append("<li>email格式不正确</li>");
        result = false;
    }
    if (result == true) alert("修改成功 ^_^");
    return result;
}


//上传图片中的删除按钮
//id:表示的是增加还是修改(修改传的是数据id),closeSpan:显示图片路径的span,uploadBtn:上传按钮,
//obj:当前对象,picpath:图片路径,picText:记录文件路径的隐藏域,fileinputid:表示文件表单域
function delpic(id, closeSpan, uploadBtn, obj, picpath, picText, fileinputid) {
    //delete
    $.post("/backend/delpic.html", {'id': id, 'picpath': picpath}, function (result) {
        if ("success" == result) {
            alert("删除成功！");
            $('#' + picText).val('');
            $("#uniform-" + fileinputid + " span:first").html('无文件');
            //document.getElementById(closeSpan).removeChild(obj.parentElement);
            $("#"+closeSpan).html('');//这种方式跟上面一样效果
            $('#' + uploadBtn).show();//最后再把上传的按钮显示
        } else{
            alert("删除失败！");
        }
    }, 'html');

}

//0:表示添加,t1=a_fileInputID:表示文件表单域,t2=a_uploadbtnID:表示上传按钮,
// t3=a_idPic:显示图片的区域,t4=a_fileInputIDPath:记录文件路径的隐藏域
//'0','a_fileInputID','a_uploadbtnID','a_idPic','a_fileInputIDPath'
function TajaxFileUpload(flag, t1, t2, t3, t4) {
    if ($("#" + t1 + "").val() == '' || $("#" + t1 + "").val() == null) {
        alert("请选择上传文件！");
    } else {
        $.ajaxFileUpload
        ({
            url: '/backend/upload.html', //处理上传文件的服务端
            secureuri: false,
            fileElementId: t1,//文件表单域
            dataType: 'json',
            success: function (data) {
                //data为图片的服务器路径
                data = data.replace(/(^\s*)|(\s*$)/g, "");
                if (data == "1") {//自定义返回值:1=超出上传文件大小
                    alert("上传图片大小不得超过50K！");
                    $("#uniform-" + t1 + " span:first").html('无文件');
                    //标签选择器:选择name=*的input标签,其值发生改变时
                    $("input[name='" + t1 + "']").change(function () {
                        var fn = $("input[name='" + t1 + "']").val(); //取出要上传的文件名
                        if ($.browser.msie) {//判断浏览器（因为不同的浏览器，取出来内容不一样）
                            //ie浏览器获fn=路径+文件名,Chrome浏览器fn=文件名(我们只保留文件名)
                            fn = fn.substring(fn.lastIndexOf("\\") + 1);//我们只取文件名
                        }
                        $("#uniform-" + t1 + " span:first").html(fn);
                    });
                } else if (data == "2") {//自定义返回值:2=格式不正确
                    alert("上传图片格式不正确！");
                    $("#uniform-" + t1 + " span:first").html('无文件');
                    $("input[name='" + t1 + "']").change(function () {
                        var fn = $("input[name='" + t1 + "']").val();
                        if ($.browser.msie) {
                            fn = fn.substring(fn.lastIndexOf("\\") + 1);
                        }
                        $("#uniform-" + t1 + " span:first").html(fn);
                    });
                } else {
                    // t3=a_idPic:显示图片的区域,t4=a_fileInputIDPath:记录文件路径的隐藏域,t2=a_uploadbtnID:表示上传按钮,t1=a_fileInputID:表示文件表单域
                    $("#" + t3 + "").append("<p><span onclick=\"delpic('" + flag + "','" + t3 + "','" + t2 + "',this,'" + data +
                        "','" + t4 + "','" + t1 + "');\">x</span>" +
                        "<img src=\"" + data + "?m=" + Math.random() + "\" /></p>");
                    //(上面)?m="+Math.random() 是为了解决浏览器缓存
                    $("#" + t2 + "").hide();
                    $("#" + t4 + "").val(data);
                    $("input[name='" + t1 + "']").change(function () {
                        var fn = $("input[name='" + t1 + "']").val();
                        if ($.browser.msie) {
                            fn = fn.substring(fn.lastIndexOf("\\") + 1);
                        }
                        $("#uniform-" + t1 + " span:first").html(fn);
                    });
                }
            },
            error: function () {
                alert("上传失败！");
            }
        });
    }
}

$(".adduser").click(function (e) {
    $("#add_formtip").html('');
    e.preventDefault();
    $("#addUserDiv").modal('show');
    $("#uniform-a_fileInputID span:first").html('无文件');
    $("#uniform-a_fileInputBank span:first").html('无文件');
});