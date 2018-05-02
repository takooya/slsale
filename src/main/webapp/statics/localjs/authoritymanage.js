// 获取功能菜单列表 及 复选框勾选的回显
$(".roleNameAuthority").click(function () {
    var authority = $(this);
    var roleId = authority.attr("roleid");
    var roleName = authority.attr("rolename");
    $("#selectrole").html("当前配置角色为:" + roleName);
    $("#roleidhide").val(roleId);
    //获取functionList
    $.ajax({
        url: '/backend/functions.html',
        type: 'POST',
        data: {rId: roleId},
        dataType: 'json',
        timeout: 1000,
        error: function () {
            alert("加载功能列表失败");
        },
        success: function (result) {
            if (result != null && result == 'nodata') {
                alert("对不起,功能列表加载失败,请重试!")
            } else {
                //拼接字符串给id为functionList的ul,让其显示相关list
                listr = "";
                for (var i = 0; i < result.length; i++) {
                    listr += "<li>";
                    listr += "<ul id='subfuncul" + result[i].mainMenu.id + "' class='subfuncul'>";
                    listr += "<li class='functiontitle'><input id='functiontitle" + result[i].mainMenu.id + "' " +
                        "onchange='mainFunctionSelectChange(this," + result[i].mainMenu.id + ");'" +
                        "funcid='" + result[i].mainMenu.id + "' type='checkbox'>" +
                        result[i].mainMenu.functionName + "</li>";
                    for (var j = 0; j < result[i].subMenu.length; j++) {
                        listr += "<li><input onchange='subFunctionSelectChange(this," + result[i].mainMenu.id + ");' " +
                            "funcid='" + result[i].subMenu[j].id + "' type='checkbox'>" + result[i].subMenu[j].functionName + "</li>";
                    }
                    listr += "</ul></li>";
                }
                $("#functionList").html(listr);
                //通过roleId去回显勾选checkbox框的状态(循环)
                $("#functionList :checkbox").each(function () {
                    var checkbox = $(this);
                    $.ajax({
                        url: '/backend/getAuthorityDefault.html',
                        type: 'POST',
                        data: {rid: $("#roleidhide").val(), fid: $(this).attr("funcid")},
                        dataType: 'html',
                        timeout: 1000,
                        error: function () {
                            alert("回选勾选失败!");
                        },
                        success: function (result) {
                            if ("success" == result) {
                                checkbox.attr("checked", true);
                            } else {
                                checkbox.attr("checked", false);
                            }
                        }
                    });
                });
            }
        }
    });
});

function subFunctionSelectChange(obj, id) {
    if (obj.checked) {
        $("#functiontitle" + id).attr("checked", true);
    }
}

function mainFunctionSelectChange(obj, id) {
    if (obj.checked) {
        $("#subfuncul" + id + " :checkbox").attr("checked", true);
    } else {
        $("#subfuncul" + id + " :checkbox").attr("checked", false);
    }

    //alert($(this) +　id);
}

$("#selectAll").click(function () {//全选
    $("#functionList :checkbox").attr("checked", true);
});

$("#unSelect").click(function () {//全不选
    $("#functionList :checkbox").attr("checked", false);
});

$("#reverse").click(function () {//反选
    $("#functionList :checkbox").each(function () {
        $(this).attr("checked", !$(this).attr("checked"));
    });
});
//提交授权修改
$("#confirmsave").click(function () {
    if(confirm("您确定要修改当前用户的权限吗")){
        //获得roldId和functionIds
        var ids=$("#roleidhide").val()+"-";
        $("#functionList :checkbox").each(function () {
            if($(this).attr("checked")=="checked"){
                ids+=$(this).attr("funcid")+"-";
            }
        });
        alert(ids);
        //ajax递交
        $.ajax({
            url:'/backend/modifyAuthority.html',
            type: 'POST',
            data: {ids: ids},
            dataType: 'html',
            timeout: 1000,
            error: function () {
                alert("修改权限失败!");
            },
            success: function (result) {
                if(result=="nodata"){
                    alert("对不起,功能列表获取失败,请重试");
                }else if(result == "success"){
                    alert("恭喜你,权限修改成功");
                }else {
                    alert("系统异常,请重试");
                }
            }
        });
    }
});