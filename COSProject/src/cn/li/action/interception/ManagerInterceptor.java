
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
public class ManagerInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 三类用户 admin manager member  任何一个用登陆成功就可访问pages页面
		Map <String,Object> map = invocation.getInvocationContext().getSession();
	
		if(map.get("manager")!=null){
			return invocation.invoke();
		}
		
		 ServletActionContext.getRequest().setAttribute("msg", "您不具备该操作权限 请联系管理员");
		 ServletActionContext.getRequest().setAttribute("url", "/login.jsp");
		 return "forward.page";     
		    
		
	}

}

