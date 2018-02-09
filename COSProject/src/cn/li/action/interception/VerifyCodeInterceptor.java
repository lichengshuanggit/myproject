package cn.li.action.interception;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 验证码验证拦截器
 * @author Administrator
 *
 */
public class VerifyCodeInterceptor extends AbstractInterceptor{

	@Override   
	public String intercept(ActionInvocation invocation) throws Exception {
		
		HttpServletRequest request=ServletActionContext.getRequest();
		String code=request.getParameter("code");
	   if(code!=null){
		if(request.getSession().getAttribute("rand").toString().equalsIgnoreCase(code)){
			//验证码合格放行
			return invocation.invoke();
		}else{
			request.setAttribute("url", "/login.jsp");
			request.setAttribute("msg", "验证码输入错误，请重新输入");
		}
		  
	   }else{
		   request.setAttribute("url", "/login.jsp");
		   request.setAttribute("msg", "验证码不能为空，请重新输入"); 
	   }
	    
	  
		return "forward.page";
		
	}

}
