package cn.li.util.action;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionInvocation;

import cn.li.util.ValidateUtils;

/**
 * 数据验证处理
 * @author Administrator
 *
 */
public class Actionvalidator {
     
	private Actionvalidator(){}
	
	/**
	 * 实现指定的Action数据验证处理
	 * @param invocation 用于取得所有接收的处理参数
	 * @param rulesvalue      验证规则
	 * @return
	 */
	public static boolean validate(ActionInvocation invocation,String rulesvalue) throws Exception{
		
		//取得所有参数
		Map<String, Object> paramMap=invocation.getInvocationContext().getParameters();
		//为了能够将错误信息保存 我们需要action中的addFieldError方法
		Object actionObject=invocation.getAction();
		Method addFieldErrorName = actionObject.getClass().getMethod("addFieldError", String.class,String.class);
		//我们还需要找到getText 来读取配置文件的信息
		Method getTextName= actionObject.getClass().getMethod("getText", String.class);//这里不考虑null 所以只穿一个参数
		//为了能够判断最终是否有错误出现 可以找到 getFieldErrors()方法
		Method getFieldErrorsName=actionObject.getClass().getMethod("getFieldErrors");

		//拆分验证规则
		String result[] =rulesvalue.split("\\|");
		for(int x=0;x<result.length;x++){
			
			String temp[] =result[x].split("\\:");
			
			String paramVlaue=null;    //普通参数内容
			String paramVlaues[]=null; //数组参数内容
			
				
				if(temp[0].endsWith("[]")){  //数组处理
				try{
					paramVlaues=(String[]) paramMap.get(temp[0]);
				  }catch(Exception e){
						
					}
				
					if("string".equalsIgnoreCase(temp[1])){
	                	if(!ValidateUtils.isNotNullArray(paramVlaues)){
	                		
	                		String msg=(String) getTextName.invoke(actionObject, "validate.string.error");
	                		//保存错误提示信息
	                		addFieldErrorName.invoke(actionObject,temp[0], msg);
	                	 }
	                 }else if("int".equals(temp[1])){
	  
	                	 if(!ValidateUtils.isIntArray(paramVlaues)){
	                		 String msg=(String) getTextName.invoke(actionObject, "validate.int.error");
	                			addFieldErrorName.invoke(actionObject,temp[0], msg);
	                	 }
	                 }else if("double".equals(temp[1])){
	                	 if(!ValidateUtils.isDoubleArray(paramVlaues)){
	                		 String msg=(String) getTextName.invoke(actionObject, "validate.double.error");
	                			addFieldErrorName.invoke(actionObject,temp[0], msg);
	                	 }
	                 }else if("date".equals(temp[1])){
	                	 if(!ValidateUtils.isDateArray(paramVlaues)){
	                		 String msg=(String) getTextName.invoke(actionObject, "validate.date.error");
	                			addFieldErrorName.invoke(actionObject,temp[0], msg);
	                	 }
	                 }else if("datetime".equals(temp[1])){
	                	 if(!ValidateUtils.isDateTimeArray(paramVlaues)){
	                		 String msg=(String) getTextName.invoke(actionObject, "validate.datetime.error");
	                			addFieldErrorName.invoke(actionObject,temp[0], msg);
	                	 }
	                 }
				}else{ //普通参数处理
					
				try{
					paramVlaue = ((String[])paramMap.get(temp[0]))[0];
				}catch(Exception e){
					
				}
		                if("string".equalsIgnoreCase(temp[1])){
		                	if(!ValidateUtils.isNotNull(paramVlaue)){
		                		String msg=(String) getTextName.invoke(actionObject, "validate.string.error");
		                		addFieldErrorName.invoke(actionObject,temp[0], msg);
		                		
		                	 }
		                 }else if("int".equals(temp[1])){
		  
		                	 if(!ValidateUtils.isInt(paramVlaue)){
		                		 String msg=(String) getTextName.invoke(actionObject, "validate.int.error");
		                		 addFieldErrorName.invoke(actionObject,temp[0], msg);
		                		
		                	 }
		                 }else if("double".equals(temp[1])){
		                	 if(!ValidateUtils.isDouble(paramVlaue)){
		                		 String msg=(String) getTextName.invoke(actionObject, "validate.double.error");
		                			addFieldErrorName.invoke(actionObject,temp[0], msg);
		                		   
		                	 }
		                 }else if("date".equals(temp[1])){
		                	 if(!ValidateUtils.isDate(paramVlaue)){
		                		 String msg=(String) getTextName.invoke(actionObject, "validate.date.error");
		                			addFieldErrorName.invoke(actionObject,temp[0], msg);
		                		
		                	 }
		                 }else if("datetime".equals(temp[1])){
		                	 if(!ValidateUtils.isDateTime(paramVlaue)){
		                		 String msg=(String) getTextName.invoke(actionObject, "validate.datetime.error");
		                		addFieldErrorName.invoke(actionObject,temp[0], msg);
		                		
		                	 }
		                 }
				}
        
			
		}
		
		Map<String,List<String>> map=(Map<String, List<String>>) getFieldErrorsName.invoke(actionObject);
		
		return map.size()==0;
		
	}
}
