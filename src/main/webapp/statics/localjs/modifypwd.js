$("#modifySavePassword").click(function () {
    oldPassword = $.trim($("#oldPassword").val());
    newPassword = $.trim($("#newPassword").val());
    confirmPassword = $.trim($("#confirmPassword").val());
    $("#oldPassword").val('');
    $("#newPassword").val('');
    $("#confirmPassword").val('');
    if (oldPassword == "" || oldPassword == null) {
        $("#modifypwdtip").css("color", "red");
        $("#modifypwdtip").html("对不起,原密码不能为空");
        $("#oldPassword").focus();
    } else if (newPassword == "" || newPassword == null) {
        $("#modifypwdtip").css("color", "red");
        $("#modifypwdtip").html("对不起,新密码不能为空");
        $("#newPassword").focus();
    } else if (confirmPassword == "" || confirmPassword == null) {
        $("#modifypwdtip").css("color", "red");
        $("#modifypwdtip").html("请确认您输入的密码");
        $("#confirmPassword").focus();
    } else if (confirmPassword != newPassword) {
        $("#modifypwdtip").css("color", "red");
        $("#modifypwdtip").html("您要修改的密码不一致,请重新输入");
        $("#newPassword").val("");
        $("#confirmPassword").val("");
        $("#newPassword").focus();
    } else {
        $("#modifypwdtip").html("");
        $.ajax({
            type: 'POST',
            url: '/backend/modify.html',
            data: {info: JSON.stringify({"old": oldPassword, "new": newPassword, "confirm": confirmPassword})},
            datatype: 'json',
            timeout: 1000,
            error: function () {
                $("#modifypwdtip").css("color", "red");
                $("#modifypwdtip").html("连接失败,请重试");
            },
            success: function (result) {
                if (result != "" && result == "success") {//若成功,跳转到'/main.html'
                    $("#modifypwdtip").css("color", "green");
                    $("#modifypwdtip").html("修改成功,2秒后跳转到登录页面");
                    $("#loadingImg").show();
                    window.setTimeout("window.location.href='/logout.html'", 2000);
                } else if (result == "failed") {
                    $("#modifypwdtip").css("color", "red");
                    $("#modifypwdtip").html("修改失败,您的登录信息有误,请重新登录");
                    $("#loadingImg").show();
                    window.setTimeout("window.location.href='/logout.html'", 2000);
                } else if (result == "nologincode") {
                    $("#modifypwdtip").css("color", "red");
                    $("#modifypwdtip").html("登录状态失效,请重新登录");
                    $("#loadingImg").show();
                    window.setTimeout("window.location.href='/logout.html'", 2000);
                } else if (result == "pwderror") {
                    $("#modifypwdtip").css("color", "red");
                    $("#modifypwdtip").html("登录失败,密码输入有误,请重试");
                    $("#oldPassword").val('');
                    $("#newPassword").val('');
                    $("#confirmPassword").val('');
                    $("#oldPassword").focus();
                } else if (result == "nodata") {
                    $("#modifypwdtip").css("color", "red");
                    $("#modifypwdtip").html("没有任何数据需要处理,请重试");
                    $("#oldPassword").val('');
                    $("#newPassword").val('');
                    $("#confirmPassword").val('');
                    $("#oldPassword").focus();
                }
            }
        });
    }
});
$('#myModal').on('shown.bs.modal', function () {
    $("#oldPassword").val('');
    $("#newPassword").val('');
    $("#confirmPassword").val('');
    $("#oldPassword").focus();
});