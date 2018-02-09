package cn.li.util.action;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;

/**
 * 根据指定的Action对象以及调用的业务方法读取配置文件中指定的验证规则信息
 * @author Administrator
 *
 */
public class GetResourceRules {
  
	private GetResourceRules(){}
	
	public static String getRules(ActionInvocation invocation) throws Exception{
		
		Object actionObject = invocation.getAction();//取得当前执行的Action对象
		String methodNmae =getMethodName() ;
		if(methodNmae!=null){ //表示取得路径
			
			//拼凑Validators.properties文件中验证规则的key
			String ruleskey = actionObject.getClass().getSimpleName()+"."+methodNmae+".rules";
			/*取得验证规则,有了key的名字之后还需要取得具体的验证规则信息，所有的Action都有getText方法
			 *运用这种方式读取可以避免自己编写资源文件读取的重复操作
			 */
			//取得规则内容
			Method textMethod=actionObject.getClass().getMethod("getText", String.class,String.class);
			String rulesValue = (String)textMethod.invoke(actionObject, ruleskey,null);
			
			return rulesValue;
		}
		return null;
		
	}
	
	public static String getMethodName(){
		
		
		String url=ServletActionContext.getRequest().getRequestURI(); //取得处理路径
		if(url!=null){ //表示取得了路径
			//取出业务方法的名字 例如 MemberAction!add.action 取出add
			String methodNmae = url.substring(url.lastIndexOf("!")+1,url.lastIndexOf("."));
			return methodNmae;
		}
		return null;
		
	}
}
