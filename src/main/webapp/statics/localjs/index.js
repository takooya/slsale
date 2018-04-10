$("#loginbtn").click(function () {
    var user = new Object();
    user.loginCode = $.trim($("#loginCode").val());
    user.password = $.trim($("#password").val());
    user.isStart = 1;

    if(user.loginCode==""||user.loginCode==null){
        $("#loginCode").focus();
        $("#formtip").css("color","red");
        $("#formtip").html("对不起,用户名不能为空");
    }else if(user.password==""||user.password==null){
        $("#password").focus();
        $("#formtip").css("color","red");
        $("#formtip").html("对不起,密码不能为空");
    }else{
        $("#formtip").html("");

        $.ajax({
            type:'POST',
            url:'/login.html',
            data:{userJson:JSON.stringify(user)},
            datatype:'html',
            timeout:1000,
            error:function(){
                $("#formtip").css("color","red");
                $("#formtip").html("连接失败,请重试");
            },
            success:function (result) {
                if(result!=""&&result=="success"){//若成功,跳转到'/main.html'
                    window.location.href='/main.html'
                }else if(result=="failed"){
                    $("#formtip").css("color","red");
                    $("#formtip").html("登录失败,请重试");
                    $("#loginCode").val('');
                    $("#password").val('');
                    $("#loginCode").focus();
                }else if(result=="nologincode"){
                    $("#formtip").css("color","red");
                    $("#formtip").html("登录失败,无此账号,请重试");
                    $("#loginCode").val('');
                    $("#password").val('');
                    $("#loginCode").focus();
                }else if(result=="pwderror"){
                    $("#formtip").css("color","red");
                    $("#formtip").html("登录失败,密码输入有误,请重试");
                    $("#loginCode").val('');
                    $("#password").val('');
                    $("#loginCode").focus();
                }else if(result=="nodata"){
                    $("#formtip").css("color","red");
                    $("#formtip").html("没有任何数据需要处理,请重试");
                    $("#loginCode").val('');
                    $("#password").val('');
                    $("#loginCode").focus();
                }
            }
        });
    }
});