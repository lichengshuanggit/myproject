package cn.li.util.interceptor;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import cn.li.util.action.Actionvalidator;
import cn.li.util.action.GetResourceRules;

/***
 * 数据验证拦截器
 * @author Administrator
 *
 */
public class InvalidateInterceptor extends AbstractInterceptor {

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		
		//取得验证规则
		String rulesvalue = GetResourceRules.getRules(invocation);
		
		if(rulesvalue==null){   //如果没有验证规则就放行
			return invocation.invoke();
		}
		
		//否者就进行数据验证
		if(Actionvalidator.validate(invocation, rulesvalue)){ //如果验证通过 放行
			return invocation.invoke();
		}else{  //跳转到错误提示页
			 //取得方法名称 
			 String methodName=GetResourceRules.getMethodName();
			 return methodName+".error";
		}
		
	}

}
