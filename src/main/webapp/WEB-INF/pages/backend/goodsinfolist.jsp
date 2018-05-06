<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/head.jsp" %>

<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">后台管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">商品管理</a>
        </li>
    </ul>
</div>


<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> 商品管理</h2>
            <div class="box-icon">
                <span id="a_goodsinfobtn" class="icon32 icon-color icon-add custom-setting"/>
            </div>
        </div>
        <%--start搜索部分--%>
        <div class="box-content">
            <form action="/backend/goodsinfolist.html" method="post">
                <div class="searcharea arrows-on-right-horizontal">
                    商品名称: <input type="text" name="s_goodsName" value="${s_goodsName}">
                    状态: <select id="selectState" name="s_state">
                    <option
                            <c:if test="${s_state==null or s_state==''}">selected="selected"</c:if> value="">所有
                    </option>
                    <option
                            <c:if test="${s_state==1}">selected="selected"</c:if> value="1">上架
                    </option>
                    <option
                            <c:if test="${s_state==2}">selected="selected"</c:if> value="2">下架
                    </option>
                </select>
                    <button class="btn btn-small btn-primary">查询</button>
                </div>
            </form>
        </div>
        <%--end搜索部分--%>
        <%--start列表部分--%>
        <div class="box-content">
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th>市场价(元)</th>
                    <th>优惠价(元)</th>
                    <th>库存</th>
                    <th>状态(上架/下架)</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goodsInfos}" var="goodsInfo">
                    <tr>
                        <td>${goodsInfo.goodsName}</td>
                        <td class="center">${goodsInfo.marketPrice}</td>
                        <td class="center">${goodsInfo.realPrice}</td>
                        <td class="center">${goodsInfo.num} ${goodsInfo.unit}</td>
                        <td class="center">
                            <input type="checkbox"
                                   <c:if test="${goodsInfo.state==1}">checked="checked"</c:if> id="inlineCheckbox1"
                                   disabled="disabled">
                        </td>
                        <td class="center">
                            <fmt:formatDate value="${goodsInfo.lastUpdateTime}" pattern="yyyy-MM-dd"/>
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
                            <a class="btn btn-danger delgoodsinfo" href="#"
                               id="${goodsInfo.id}" goodsName="${goodsInfo.goodsName}"
                               goodsSn="${goodsInfo.goodsSn}">
                                <i class="icon-trash icon-white"></i>
                                删除
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <%--end列表部分--%>

    </div><!--/span-->
</div>
<!--/row-->

<%--start新增的模式窗口--%>
<div class="modal hide fade" id="a_goodsinfo">
    <form action="/backend/addgoodsinfo.html" method="post" enctype="multipart/form-data"
          onsubmit="return addGoodsFunction()">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal">×</button>
            <h3>添加商品信息</h3>
            <div id="add_formtip"></div>
        </div>
        <div class="modal-body">
            <ul class="topul">
                <li><label>产品名称：</label>
                    <input type="text" id="add_goodsName" name="goodsName" value=""/>
                    <span style="color:red;font-weight: bold;">*</span></li>
                <li><label>产品编号：</label>
                    <input type="text" id="add_goodsSn" name="goodsSn" value=""/>
                    <span style="color:red;font-weight: bold;">*</span></li>
                <li><label>市场价：</label>
                    <input type="text" id="add_marketPrice" name="marketPrice" value=""
                           onkeyup="value=value.replace(/[^\d.]/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span></li>
                <li><label>优惠价：</label>
                    <input type="text" id="add_realPrice" name="realPrice" value=""
                           onkeyup="value=value.replace(/[^\d.]/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span></li>
                <li><label>库存：</label>
                    <input type="text" id="add_num" name="num" value="" onkeyup="value=value.replace(/\D/g,'')"/>
                    <span style="color:red;font-weight: bold;">*</span></li>
                <li><label>单位：</label>
                    <input type="text" id="add_unit" name="unit" value=""/>
                    <span style="color:red;font-weight: bold;">*</span></li>
            </ul>
            <div class="clear"></div>
            <ul style="list-style-type:none">
                <li>
                    状态：<select id="add_state" name="state">
                    <option value="1">上架</option>
                    <option value="2">下架</option>
                </select></li>
                <li>产品详细：</li>
                <li>产品说明：
                </li>
            </ul>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn add_clear">清除</a>
            <input type="submit" class="btn btn-primary" value="保存">
        </div>
    </form>
</div>
<%--end新增商品的模式窗口--%>
<%@include file="/WEB-INF/pages/common/foot.jsp" %>
<script type="text/javascript" src="/statics/localjs/goodsinfolist.js"></script>