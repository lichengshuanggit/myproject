package cn.li.action.adinm;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import cn.li.pojo.Groups;
import cn.li.pojo.Role;
import cn.li.service.admin.IRoleServiceAdmin;
import cn.li.util.action.AbstractAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Repository //注册bean
@ParentPackage("root")  //注解实现包的定义的
@Namespace("/pages/jsp/admin/role")
@Action("roleactionadmin")  //注解定义Action
@InterceptorRef("adminStack")  //引入数据验证拦截器 
@Results(value={
		         @Result(name="role.insert",location="/pages/jsp/admin/role/admin_role_insert.jsp"),
		         @Result(name="role.list",location="/pages/jsp/admin/role/admin_role_list.jsp")
               }) 
public class RoleActionAdmin extends AbstractAction {
   
	@Resource
	private IRoleServiceAdmin  iRoleServiceAdmin;
	private Role role;
	private Integer[] gids; // 处理权限组编号，在增加的时候使用
	private String ugid ;	// 更新时使用的gid数据
	
	/**
	 * 添加角色前需要对对权限组进行列表显示
	 * @return
	 */
	public String insertPre(){
		try {
			Map<String,Object> map =this.iRoleServiceAdmin.insertPre();
			super.getRequest().setAttribute("rolegroups",map.get("allGroups") );
		} catch (Exception e) {
			return "error.page";
		}
		return "role.insert";
		
	}
	
	/**
	 * 更新操作前数据准备
	 */
	public void updatePre(){
		
		try {
			Map<String,Object>map=this.iRoleServiceAdmin.updatetPre(this.role.getRid());
			System.out.println("----------------"+this.role.getRid());
			List<Groups> list=(List<Groups>) map.get("allGroups");
			Role role=(Role)map.get("Role");
			Map<Integer,Boolean> rgids = (Map<Integer,Boolean>) map.get("gids") ;
			JSONObject json =new JSONObject();
			json.put("rid", role.getRid());
			json.put("title", role.getTitle());
			json.put("note", role.getNote());
			
			
			Iterator <Groups> ite =list.iterator();
			JSONArray arr =new JSONArray();
			while(ite.hasNext()){
				Groups groups =ite.next();
				JSONObject temp =new JSONObject();
				temp.put("gid", groups.getGid());
				temp.put("title", groups.getTitle());
				temp.put("note", groups.getNote());
				if (rgids.get(groups.getGid()) != null) {
					temp.put("ckd", "checked") ;
				} else {
					temp.put("ckd", "") ;
				}
				arr.add(temp);
			}
			json.put("groups", arr);
			super.print(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(){
		
		Set<Groups> set =new HashSet<Groups>();
		if (this.ugid != null) {
			String result [] = this.ugid.split("\\|") ;
			for (int x = 0 ; x < result.length ; x ++) {
				Groups g = new Groups() ;
				g.setGid(Integer.parseInt(result[x]));
				set.add(g) ;
			}
			//设置关系
			this.role.setGroupses(set);
		}
		try {
			super.print(this.iRoleServiceAdmin.update(this.role));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 角色插入
	 * @return
	 */
	public String insert(){
		
		Set<Groups> set = new HashSet<Groups>();//添加角色包含的权限组
		for(int x=0;x<this.gids.length;x++){
			System.out.println(this.gids[x]);
			Groups groups =new Groups(); 
			groups.setGid(this.gids[x]);
			set.add(groups);
		}
		this.role.setGroupses(set);
		//保存数据
		try {
			if(this.iRoleServiceAdmin.insert(this.role)){
				//添加成功返回到添加 页面
				super.setUrlAndMessage("admin.role.insertpre", "insert.success");
			}else{
				super.setUrlAndMessage("admin.role.insertpre", "insert.defeat");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			super.setUrlAndMessage("admin.role.insertpre", "insert.defeat");
			e.printStackTrace();
		}
	
		return "forward.page";
		
	}
	public void checkinsert(){
		try {
			super.print(this.iRoleServiceAdmin.check(this.role.getTitle()));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 修改验证
	 */
	public void checkTitleUpdate() {
		try {
			System.out.println(this.role.getTitle()+""+this.role.getRid());
			super.print(this.iRoleServiceAdmin.check(this.role.getTitle(),
					this.role.getRid()));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String list(){
		
		try {
		     Map<String,Object> map=this.iRoleServiceAdmin.list();
			 super.getRequest().setAttribute("allRoles", map.get("allRole"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "role.list";
		
	}
	
	//=================setter
	
	
	
	public Role getRole() {
		return role;
	}

	public Integer[] getGids() {
		return gids;
	}

	public void setGids(Integer[] gids) {
		this.gids = gids;
	}

	public String getUgid() {
		return ugid;
	}

	public void setUgid(String ugid) {
		this.ugid = ugid;
	}

	public void setRole(Role role) {
		this.role = role;
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
		return "角色";
	}

}
