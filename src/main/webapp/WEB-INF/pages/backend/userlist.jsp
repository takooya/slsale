<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/WEB-INF/pages/common/head.jsp" %>

<%--<div>
	<ul class="breadcrumb">
		<li>
			<a href="#">后台管理</a> <span class="divider">/</span>
		</li>
		<li>
			<a href="/backend/userlist.html">用户管理</a>
		</li>
	</ul>
</div>
<div class="row-fluid sortable">		
	<div class="box span12">
		<div class="box-header well" data-original-title>
			<h2><i class="icon-user"></i> 用户列表</h2>
			<div class="box-icon">
				<span class="icon32 icon-color icon-add adduser"/>
			</div>
		</div>
 <div class="box-content">
 <form action="/backend/userlist.html" method="post">
 	<div class="searcharea">
 	用户名称：
 	<input type="text" name="s_loginCode" value="${s_loginCode }">
 	推荐人：
 	<input type="text" name="s_referCode" value="${s_referCode }">
 	角色：
 	<select name="s_roleId" style="width:100px;">
 		<option value="" selected="selected">--请选择--</option>
 		<c:forEach items="${roleList }" var="role">
 			<option <c:if test="${s_roleId == role.id }">selected = "selected"</c:if>
 			value="${role.id }">${role.roleName }</option>
 		</c:forEach>
 	</select>
 	 是否启用：
 	 <select name="s_isStart" style="width:100px;">
 		<option value="" selected="selected">--请选择--</option>
 		 <c:if test="${s_isStart == 1}">  
		　　<option value="1" selected="selected">启用</option>
			<option value="2">未启用</option>
	　　 </c:if>  
	　　 <c:if test="${s_isStart == 2}">  
		　    <option value="2" selected="selected">未启用</option>
			<option value="1">启用</option>
	 	 </c:if>
	　　  <c:if test="${s_isStart == null || s_isStart == ''}">  
		　    <option value="2">未启用</option>
			<option value="1">启用</option>
	 	</c:if>
 	</select>
 	<button class="btn btn-primary"><i class="icon-search icon-white"></i> 查询 </button>
 	</div>
 </form>
	<table class="table table-striped table-bordered bootstrap-datatable datatable">
	  <thead>
		  <tr>
			  <th>用户名</th>
			  <th>角色</th>
			  <th>会员类型</th>
			  <th>推荐人</th>
			  <th>状态(启用/禁用)</th>
			  <th>注册时间</th>
			  <th>操作</th>
		  </tr>
	  </thead>   
	  <tbody>
		<c:if test="${page.items != null}">
		  <c:forEach items="${page.items}" var="user">
			<tr>
				<td class="center">${user.loginCode}</td>
				<td class="center">${user.roleName}</td>
				<td class="center">${user.userTypeName}</td>
				<td class="center">${user.referCode}</td>
				<td class="center">
				<c:if test="${user.isStart == 2}"><input type="checkbox" disabled="disabled"/></c:if>
                <c:if test="${user.isStart == 1}"><input type="checkbox" checked="true" disabled="disabled"/></c:if>
				</td>
				<td class="center">
				<fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td class="center">
					<a class="btn btn-success viewuser" href="#" id="${user.id}">
						<i class="icon-zoom-in icon-white"></i>  
						查看                                           
					</a>
					<a class="btn btn-info modifyuser" href="#" id="${user.id}">
						<i class="icon-edit icon-white"></i>  
						修改                                            
					</a>
					<a class="btn btn-danger deluser" href="#" usertype="${user.userType}" usertypename="${user.userTypeName}" logincode="${user.loginCode}" id="${user.id}" idcardpicpath="${user.idCardPicPath}" bankpicpath="${user.bankPicPath}">
						<i class="icon-trash icon-white"></i> 
						删除
					</a>
				</td>
			</tr>
		  </c:forEach>
		 </c:if>
		</tbody>
	</table>
	<div class="pagination pagination-centered">
	  <ul>
	  <c:choose>
	  	<c:when test="${page.page == 1}">
	  	<li class="active"><a href="javascript:void();" title="首页">首页</a></li>
	  	</c:when>
	  	<c:otherwise>
	  	<li><a href="/backend/userlist.html?currentpage=1&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}" title="首页">首页</a></li>
	  	</c:otherwise>
	  </c:choose>
		<c:if test="${page.prevPages!=null}">
			<c:forEach items="${page.prevPages}" var="num">
				<li><a href="/backend/userlist.html?currentpage=${num}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}"
					class="number" title="${num}">${num}</a></li>
			</c:forEach>
		</c:if>
		<li class="active">
		  <a href="#" title="${page.page}">${page.page}</a>
		</li>
		<c:if test="${page.nextPages!=null}">
			<c:forEach items="${page.nextPages}" var="num">
				<li><a href="/backend/userlist.html?currentpage=${num}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}" title="${num}">
				${num} </a></li>
			</c:forEach>
		</c:if>
		<c:if test="${page.pageCount !=null}">
			<c:choose>
		  	<c:when test="${page.page == page.pageCount}">
		  	<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
		  	</c:when>
		  	<c:otherwise>
		  	<li><a href="/backend/userlist.html?currentpage=${page.pageCount}&s_loginCode=${s_loginCode}&s_referCode=${s_referCode}&s_roleId=${s_roleId}&s_isStart=${s_isStart}" title="尾页">尾页</a></li>
		  	</c:otherwise>
		    </c:choose>
	    </c:if>
		<c:if test="${page.pageCount == null}">
		<li class="active"><a href="javascript:void();" title="尾页">尾页</a></li>
	  	</c:if>
		
	  </ul>
	</div>   
  </div>
</div>
</div>--%>
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">后台管理</a> <span class="divider">/</span>
        </li>
        <li>
            <a href="/backend/userlist.html">用户管理</a>
        </li>
    </ul>
</div>

<div class="row-fluid sortable">
    <div class="box span12">
        <div class="box-header well" data-original-title>
            <h2><i class="icon-user"></i> 用户管理</h2>
            <div class="box-icon">
                <span class="icon32 icon-color icon-add adduser"/>
            </div>
        </div>
        <div class="box-content">
            <form action="/backend/userlist.html" method="post">

                <div class="searcharea">
                    用户名:
                    <input type="text" name="loginCode" value="${loginCode}">
                    推荐人:
                    <input type="text" name="referCode" value="${referCode}">
                    角色:
                    <select name="rodeId" style="width: 100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <c:forEach items="${roleList}" var="role">
                            <option value="${role.id}"
                                    <c:if test="${role.id == s_roleId}">selected="selected"</c:if>>
                                    ${role.roleName}
                            </option>
                        </c:forEach>
                    </select>
                    是否启用:
                    <select name="isStart" style="width: 100px;">
                        <option value="" selected="selected">--请选择--</option>
                        <option value="1" <c:if test="${isStart==1}">selected="selected"</c:if>>启用</option>
                        <option value="2" <c:if test="${isStart==2}">selected="selected"</c:if>>未启用</option>
                    </select>
                    <button class="btn btn-primary"><i class="icon-search icon-white"></i> 查询</button>
                </div>
            </form>
            <table class="table table-striped table-bordered bootstrap-datatable datatable">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>角色</th>
                    <th>会员类型</th>
                    <th>推荐人</th>
                    <th>启用状态(启用/禁用)</th>
                    <th>注册时间</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <td class="center">David R</td>
                    <td class="center">2012/01/01</td>
                    <td class="center">Member</td>
                    <td class="center">2012/01/01</td>
                    <td class="center">Member</td>
                    <td class="center">
                        <span class="label label-important">管理员</span>
                        <span class="label label-success">会员</span>
                        <span class="label">其他</span>
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
                </tbody>
            </table>
            <div class="pagination pagination-centered">
                <ul>
                    <li><a href="#">Prev</a></li>
                    <li class="active">
                        <a href="#">1</a>
                    </li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">Next</a></li>
                </ul>
            </div>
        </div>
    </div><!--/span-->
</div>
<!--/row-->


<%@include file="/WEB-INF/pages/common/foot.jsp" %>