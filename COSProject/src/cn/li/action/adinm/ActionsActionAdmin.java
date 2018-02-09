package cn.li.action.adinm;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.li.pojo.Actions;
import cn.li.service.admin.IActionServiceAdmin;
import cn.li.util.action.AbstractAction;

/**
 * 管理员对权限管理的操作
 * @author Administrator
 *
 */
@Repository //注册bean
@ParentPackage("root")  //注解实现包的定义的
@Namespace("/pages/jsp/admin/role")
@Action("actionsactionadmin")  //注解定义Action
@InterceptorRef("adminStack")  //引入拦截器 
@Results(value={@Result(name = "action.list", location = "/pages/jsp/admin/role/admin_action_list.jsp")}) 

public class ActionsActionAdmin extends AbstractAction {
   
	
	private Actions actions;
	@Resource
	private IActionServiceAdmin iActionServiceAdmin;
	public String list() { // 数据分页显示
		try {
			Map<String, Object> map = this.iActionServiceAdmin
					.list(super.getCp(), super.getLs(), super.getCol(),
							super.getKw());
			super.handleSplit(map.get("Count"), "admin.action.split.url",
					"a", "a");
			super.getRequest()
					.setAttribute("allActions", map.get("allActions"));
		} catch (Exception e) {
			return "error.page";
			//e.printStackTrace();
		}
		return "action.list";
	}

	public void update() {
		
		try {
			System.out.println(this.iActionServiceAdmin.update(this.actions));
			super.print("true");
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}

	@Override
	public String getNameType() {
		return "权限";
	}

	@Override
	public String getDefaultColumn() {
		return "title";
	}

	@Override
	public String getColumnData() {
		return "权限标题:title|权限路径:url";
	}
	
	
	
	
	public Actions getActions() {
		return actions;
	}

	public void setActions(Actions actions) {
		this.actions = actions;
	}

	
}
