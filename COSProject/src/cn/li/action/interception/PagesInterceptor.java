package cn.li.action.interception;

import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * pages目录下所有页面的登录检查拦截器
 * @author Administrator
 *
 */
public class PagesInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 三类用户 admin manager member  任何一个用登陆成功就可访问pages页面
		Map <String,Object> map = invocation.getInvocationContext().getSession();
		
		  
		if(map.get("admin")==null){
			if(map.get("manager")==null){
				if(map.get("member")==null){
					System.out.println("没有检查到session");
					 ServletActionContext.getRequest().setAttribute("msg", "您还未登录 请登录后再试!!!!！");
					 ServletActionContext.getRequest().setAttribute("url", "/login.jsp");
					 return "forward.page"; 
				}else{
					return invocation.invoke();
				}
			}else{
				return invocation.invoke();
			}
		}else{
			return invocation.invoke();
		}
		
	}

}
