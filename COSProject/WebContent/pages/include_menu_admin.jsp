<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://www.li.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String logoutUrl =basePath+"/memberlogout!logout.action" ;
	String  updatepawpre =basePath+"/pages/jsp/admin/actionadmin!updatepasswordper.action";
	String updatedate= basePath+"/pages/jsp/admin/actionadmin!updatePre.action";
%>
 <!-- 导航栏 -->
  <nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
	<div class="navbar-header">
				<a class="navbar-brand" href="pages/jsp/admin/admin_index.jsp"><strong>协同办公管理系统（管理员）</strong></a>
	</div>
	<ul class="nav navbar-nav">
		<li><a href="pages/jsp/admin/admin_index.jsp">首页</a></li>
			  <c:if test="${admin!=null}">
					<c:forEach items="${admin.role.groupses}" var="gro">
					  <li class="dropdown"><a href="#" class="dropdown-toggle"
									 data-toggle="dropdown">${gro.title}<span class="caret"></span></a>
					  <ul class="dropdown-menu">
						  <c:forEach items="${gro.actionses}" var="act">
							 <li><a href="<%=basePath %>/${act.url}">${act.title}</a></li>
							 <li><a href="#"></a></li>
							 </c:forEach>
						 <c:if test="${admin.level==0}">
						   <c:if test="${gro.gid==1}">
							 <li><a href="#">添加管理员</a></li>
						 </c:if>
						 </c:if>
					</ul></li>  
				</c:forEach>          
		  </c:if>       
	</ul> 
				<ul class="nav navbar-nav navbar-right">
					<li class="dropdown"><a href="javascript:;"
						class="dropdown-toggle" data-toggle="dropdown"> <i
						class="glyphicon glyphicon-user"></i>&nbsp;${admin.mid}&nbsp;<span
						class="glyphicon glyphicon-chevron-down"></span></a>
						<ul class="dropdown-menu main-list">
							<li><a href="<%=updatepawpre%>"><i class="glyphicon glyphicon-edit"></i>&nbsp;修改密码</a></li>
							<li><a href="<%=updatedate%>"><i class="glyphicon glyphicon-info-sign"></i>&nbsp;个人资料</a></li>
							<li class="divider"></li>
							<li><a href="<%=logoutUrl%>"><i class="glyphicon glyphicon-off"></i>&nbsp;登录注销</a></li>
						</ul></li>
						<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
							</ul>
                       </nav> <!-- 导航栏部分 -->										   
