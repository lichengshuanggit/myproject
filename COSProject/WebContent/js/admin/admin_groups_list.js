$(function() {
	$("button[id*='updateBtn-']").each(function(){	// 取得修改按钮
		var gid = this.id.split("-")[1];	// 分离出id信息
		$(this).on("click",function(){
			var title = $("#title-" + gid).val() ;
			var note = $("#note-" + gid).val() ;
			console.log("gid = " + gid + "，title = " + title + "，note = " + note) ;
			// 编写Ajax异步更新操作
			$.post("pages/jsp/admin/role/groupsactionadmin!update.action",
					{"groups.gid":gid,"groups.title":title,"groups.note":note},
					function(data){
						
						operateAlert(data='true',"权限信息修改成功！","权限信息修改失败！") ;
					},"text") ;
			
		}) ;
	}) ;
	
	$("button[id*='showBtn-']").each(function(){	// 取得显示按钮
		var gid = this.id.split("-")[1];	// 分离出id信息
		$(this).on("click",function(){
			
			// 编写Ajax异步更新操作，读取所有的权限信息
			$.post("pages/jsp/admin/role/groupsactionadmin!show.action",
					{"groups.gid":gid},
					function(data){
			
						//显示之前先清空表格
						$("#actionsTable tr:gt(0)").remove();
						//标题
						$("#grouptitleSpan").text(data.title);
						//显示tr
						for(var i=0;i<data.actions.length;i++){
							var tr="<tr><td>"+data.actions[i].actid+"</td><td>"+data.actions[i].title+"</td><td>"+data.actions[i].url+"</td></tr>";
							$("#actionsTable").append($(tr));
							
							//数据加载成功后再显示模态窗口
							//$("#groupsInfo").modal("toggle");
						}
						//operateAlert(data='true',"权限信息修改成功！","权限信息修改失败！") ;
					},"json") ;
			$("#groupsInfo").modal("toggle") ;
		}) ;
	}) ;
})