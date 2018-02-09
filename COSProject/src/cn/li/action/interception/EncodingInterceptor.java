
package cn.li.action.interception;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 进行中文乱码处理
 * @author Administrator
 *
 */
public class EncodingInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		
		    //解决请求乱码
		    ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		    //响应乱码
		    ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
			return invocation.invoke();

	}

}

