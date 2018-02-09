package cn.li.action.adinm;

import java.util.Iterator;
import java.util.List;
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
import cn.li.pojo.Groups;
import cn.li.service.admin.IGroupsServiceAdmin;
import cn.li.util.action.AbstractAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 权限组管理
 * @author Administrator
 *
 */

@Repository
@ParentPackage("root")  //注解实现包的定义的
@Namespace("/pages/jsp/admin/role")
@Action("groupsactionadmin")  //注解定义Action
@InterceptorRef("adminStack")  //引入拦截器 
@Results(value={@Result(name = "groups.list", location = "/pages/jsp/admin/role/admin_groups_list.jsp")}) 
public class GroupsActionAdmin extends AbstractAction {
	
	@Resource
	private IGroupsServiceAdmin iGroupsServiceAdmin;
	private Groups groups;
    private Actions actions;
	/**
	 * 权限组列表显示
	 * @return
	 */
	public String list(){
		
		try {
			Map<String, Object> map=(Map<String, Object>) 
	        this.iGroupsServiceAdmin.list(super.getCp(), super.getLs(), super.getCol(), super.getKw());
			super.getRequest().setAttribute("allGroups", map.get("allGroups"));
		} catch (Exception e) {
			//如果出错返回错误页面
			return "error.page";
		}
		return "groups.list";
		
	}
	
	/**
	 * 显示权限组对应的权限信息 
	 */
	public void show(){
		try {
			Groups g =this.iGroupsServiceAdmin.show(this.groups.getGid());
			
			JSONObject json =new JSONObject();
			json.put("gid", g.getGid());
			json.put("title", g.getTitle());
			json.put("note", g.getNote());
			JSONArray arr= new JSONArray();
			Iterator<Actions> ite =g.getActionses().iterator();
			
			while(ite.hasNext()){
				
				Actions act =ite.next();
				JSONObject temp = new JSONObject();
				temp.put("actid", act.getActid());
				temp.put("title", act.getTitle());
				temp.put("url", act.getUrl());
				
				arr.add(temp);
			}    
			json.put("actions", arr);
			super.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改权限组信息  采用ajax 方式
	 */
	public void update(){
		try {
			super.print(this.iGroupsServiceAdmin.update(this.groups));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return "title";
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getNameType() {
		// TODO Auto-generated method stub
		return "权限组";
	}
	
	
	
	public Groups getGroups() {
		return groups;
	}
	public void setGroups(Groups groups) {
		this.groups = groups;
	}
	public Actions getActions() {
		return actions;
	}
	public void setActions(Actions actions) {
		this.actions = actions;
	}
	
	

}
