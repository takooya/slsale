$(".delgoodsinfo").click(function () {
    var d_id = $(this).attr('id');
    var d_goodsName = $(this).attr('goodsName');
    var d_goodsSn = $(this).attr('goodsSn');
    if (confirm("您确定要删除商品编号为【" + d_goodsSn + "】的" + d_goodsName + "吗？")) {
        $.post("/backend/delgoodsinfo.html", {
            'delId': d_id
        }, function (result) {
            if ("success" == result) {
                alert("删除成功！");
                window.location.href = "/backend/goodsinfolist.html";
            } else if ("noallow" == result) {
                alert("该商品已绑定套餐，不允许被删除！");
            } else {
                alert("删除失败！");
            }
        }, 'html');
    }
});

//显示添加表单域
$("#a_goodsinfobtn").click(function (e) {
    e.preventDefault();
    $('#a_goodsinfo').modal('show');
});

//添加表单域的清除按钮
$(".add_clear").click(function (e) {
    e.preventDefault();
    $("#add_goodsName").val('');
    $("#add_goodsSn").val('');
    $("#add_realPrice").val('');
    $("#add_marketPrice").val('');
    $("#add_num").val('');
    $("#add_unit").val('');
    $("#add_formtip").html('')
});

function isMoney(s) {
    var regu = /^\d+(?:\.\d{2})?$/;
    var re = new RegExp(regu);
    if (re.test(s)) {
        return true;
    } else {
        return false;
    }
}

//添加信息验证,返回boolean,即是否可递交给controller
function addGoodsFunction() {
    $("#add_formtip").html("");
    var result = true;
    if ($("#add_goodsName").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，商品名称不能为空。</li>");
        result = false;
    }
    if ($("#add_goodsSn").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，产品编号不能为空。</li>");
        result = false;
    }
    if ($("#add_marketPrice").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，市场价格不能为空。</li>");
        result = false;
    } else if (!isMoney($("#add_marketPrice").val())) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，市场价格式输入有误。</li>");
        $("#add_marketPrice").val('')
        result = false;
    }
    if ($("#add_realPrice").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，商品优惠价不能为空。</li>");
        result = false;
    } else if (!isMoney($("#add_realPrice").val())) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，商品优惠价格式输入有误。</li>");
        $("#add_realPrice").val('');
        result = false;
    }
    if ($("#add_num").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，商品库存不能为空。</li>");
        result = false;
    } else if (!isMoney($("#add_num").val())) {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，库存输入有误。</li>");
        $("#add_num").val('');
        result = false;
    }
    if ($("#add_unit").val() == '') {
        $("#add_formtip").css("color", "red");
        $("#add_formtip").append("<li>对不起，商品单位不能为空。</li>");
        result = false;
    }
    if (result == true) alert("添加成功 ^_^");
    return result;
}