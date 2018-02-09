package cn.li.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

import cn.li.util.action.AbstractAction;

/**
 * 用户注销操作
 * @author Administrator
 *
 */

@ParentPackage("root")
@Namespace("/")
@Action("memberlogout")  //注解定义Action
public class MemberLogoutCommon extends AbstractAction {
    
	/**
	 * 用户注销
	 * @return
	 */
	public String logout(){
		
		super.getRequest().getSession().invalidate();
		super.getRequest().setAttribute("msg", "用户已成功注销");
		super.getRequest().setAttribute("url", "/login.jsp");
		return "forward.page";
	}
	
	@Override
	public String getNameType() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		// TODO Auto-generated method stub
		return null;
	}

}
