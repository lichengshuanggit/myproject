<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://www.li.cn/c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>协同办公管理系统</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<jsp:include page="/pages/include_javascript.jsp" />
<script type="text/javascript" src="js/admin/admin_groups_list.js"></script>
</head>
<body class="back">
	<div class="container contentback">
		<div id="headDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<jsp:include page="/pages/include_menu_admin.jsp" />
			</div>
		</div>
		<div id="contentDiv" class="row">
			<div class="col-md-12 col-xs-12">
				<div class="panel panel-success">
					<div class="panel-heading">
						<strong>权限组列表</strong>
					</div>
					<div class="panel-body">
						<table class="table table-bordered table-hover table-condensed">
							<thead>
								<tr>
									<td style="width:5%;" class="text-center"><strong>编号</strong></td> 
									<td style="width:20%;" class="text-center"><strong>权限组名称</strong></td>
									<td style="width:50%;" class="text-center"><strong>权限组描述</strong></td>
									<td style="width:25%;" class="text-center"><strong>操作</strong></td>
								</tr>
							</thead>
							<tbody>
							    <c:if test="${allGroups!=null}">
							    <c:forEach items="${allGroups}" var="group">
								<tr>
									<td class="text-center">${group.gid}</td> 
									<td class="text-center">
										<input type="text" id="title-${group.gid}" name="title-${group.gid}" class="form-control input-sm" value="${group.title}">
									</td>
									<td class="text-center">
										<input type="text" id="note-${group.gid}" name="note-${group.gid}" class="form-control input-sm" value="${group.note}">
									</td>
									<td class="text-center"> 
										<button class="btn btn-primary" id="updateBtn-${group.gid}"><span class="glyphicon glyphicon-edit"></span>&nbsp;修改</button>
										<button class="btn btn-info" id="showBtn-${group.gid}"><span class="glyphicon glyphicon-list"></span>&nbsp;查看权限</button>
									</td>
								</tr>
								</c:forEach>
								</c:if>
								
							</tbody>
						</table>
					</div>
					<div class="panel-footer">
						<div class="alert alert-success" id="alertDiv" style="display: none;">
	                        <button type="button" class="close" data-dismiss="alert">&times;</button>
	                        <span id="alertText"></span>
	                    </div>
					</div>
				</div>
			</div>
		</div>
		<div id="footDiv" class="row navbar-fixed-bottom">
			<jsp:include page="/pages/include_foot.jsp" />
		</div>
		<div class="modal fade" id="groupsInfo"  tabindex="-1" aria-labelledby="modalTitle" aria-hidden="true" data-keyboard="true">
			<div class="modal-dialog" style="width: 1000px">
				<div class="modal-content">
					<div class="modal-header">
						<button class="close" type="button" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h3 class="modal-title">
							<span class="glyphicon glyphicon-folder-open"></span>
							<strong>查看“<span id="grouptitleSpan"></span>”信息</strong></h3>
					</div>
					<div class="modal-body">
						<table id="actionsTable" class="table table-bordered table-hover table-condensed table-responsive">
							<thead>
								<tr>
									<td style="width:10%;" class="text-center"><strong>编号</strong></td> 
									<td style="width:30%;" class="text-center"><strong>权限名称</strong></td>
									<td style="width:60%;" class="text-center"><strong>访问路径</strong></td>
								</tr>
							</thead>
							<tbody>
								 <tr>
									<td class="text-center">1</td> 
									<td class="text-center">权限一</td>
									<td class="text-left">/pages/</td>
								</tr> 
								
							</tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
