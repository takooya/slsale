<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/pages/common/head.jsp" %>

<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">后台管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="#">商品套餐管理</a>
        </li>
    </ul>
</div>

<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> 商品套餐管理</h2>
            <div class="box-icon">
                <span id="a_goodspackbtn" class="icon32 icon-color icon-add custom-setting"/>
            </div>
        </div>
        <%--start搜索部分--%>
        <div class="box-content">
            <form action="/backend/goodspacklist.html" method="post">
                <div class="searcharea arrows-on-right-horizontal">
                    商品套餐名称: <input type="text" name="s_goodsPackName" value="${s_goodsPackName}">
                    套餐类型:<select id="s_typeName" name="s_typeName"></select>
                    状态: <select id="s_state" name="s_state">
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
                    <th>套餐编号</th>
                    <th>套餐名称</th>
                    <th>套餐总价</th>
                    <th>库存量</th>
                    <th>状态(上架/下架)</th>
                    <th>套餐类型</th>
                    <th>更新时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${goodsPacks}" var="goodsPack">
                    <tr>
                        <td>${goodsPack.goodsPackCode}</td>
                        <td class="center">${goodsPack.goodsPackName}</td>
                        <td class="center">${goodsPack.totalPrice}</td>
                        <td class="center">${goodsPack.num}</td>
                        <td class="center">
                            <input type="checkbox"
                                   <c:if test="${goodsPack.state==1}">checked="checked"</c:if> id="inlineCheckbox1"
                                   disabled="disabled">
                        </td>
                        <td class="center">${goodsPack.typeName}</td>
                        <td class="center">
                            <fmt:formatDate value="${goodsPack.lastUpdateTime}" pattern="yyyy-MM-dd"/>
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
                            <a class="btn btn-danger delgoodspack" href="#"
                               id="${goodsPack.id}" goodsPackCode="${goodsPack.goodsPackCode}"
                               goodsPackName="${goodsPack.goodsPackName}">
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
<%@include file="/WEB-INF/pages/common/foot.jsp" %>
<script type="text/javascript" src="/statics/localjs/goodspacklist.js"></script>