package cn.li.action;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import cn.li.pojo.Member;
import cn.li.service.common.IMemberServiceCommon;
import cn.li.util.MD5Code;
import cn.li.util.action.AbstractAction;

@Repository
@ParentPackage("root")  //注解实现包的定义的
@Namespace("/")
@Action("memberlogin")  //注解定义Action
//<!-- 验证失败跳转的页面  组成是  业务方法+error-->
@Results(value={@Result(name="login.error",location="/login.jsp")})
@InterceptorRef("loginverifyStack")  //引入数据验证拦截器

public class MemberLoginActionCommon extends AbstractAction{
    
	private Member member =new Member();
	
	@Resource
	private IMemberServiceCommon iMemberServiceCommon; //注入业务层接口对象
	/**
	 * 用户登录
	 * 根据用户类别跳转道不同的页面
	 * @return
	 */
	public String login(){
		
		//为了避免一个人同时登录多个不同权限的账号  登录前清除登录的session
		super.getRequest().getSession().removeAttribute("admin");
		super.getRequest().getSession().removeAttribute("member");
		super.getRequest().getSession().removeAttribute("manager");
		
		try {
			Member rusultMember=iMemberServiceCommon.login(this.member.getMid() 
					,new MD5Code().getMD5ofStr(this.member.getPassword()));
			if(rusultMember!=null){ //表示用户登录成功
				if(rusultMember.getLevel()==0||rusultMember.getLevel()==1){ //如果是管理员 调到管理员的主页
					//设置跳转页面,提示信息
					super.setUrlAndMessage("admin.index.pages", "login.success");
					//保存session
					super.getRequest().getSession().setAttribute("admin", rusultMember);
					
				}else if(rusultMember.getLevel()==2){ //项目经理
					//设置跳转页面,提示信息
					super.setUrlAndMessage("manager.index.pages", "login.success");
					//保存session
					super.getRequest().getSession().setAttribute("manager", rusultMember);
					
				}else if(rusultMember.getLevel()==3){ //普通用户
					//设置跳转页面,提示信息
					super.setUrlAndMessage("member.index.pages", "login.success");
					//保存session
					super.getRequest().getSession().setAttribute("member", rusultMember);
				}
			}else{
				   
				super.setUrlAndMessage("login.page", "login.defeat");
			}
			
		} catch (Exception e) {
			super.setUrlAndMessage("login.page", "login.defeat");
			e.printStackTrace();
			
		}
		
		return "forward.page";
		
	}
	
	

	
	
	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
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
