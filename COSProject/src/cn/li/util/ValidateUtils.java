package cn.li.util;

/**
 * 判定数据是否符合验证规则的处理
 * @author Administrator
 *
 */
public class ValidateUtils {
   
	private ValidateUtils(){}
	
   //==================数组验证=============
	    
	/**
	 * 判断一组字符串不为空才可以处理
	 * @param str
	 * @return 如果字符串为空返回false 否则返回true
	 */
	public static boolean isNotNullArray(String[] str){
		  
		for(int x=0;x<str.length;x++){
			if(!ValidateUtils.isNotNull(str[x])){
				return false;
			}
		}
		return true;
		  
		   
	}
	
	
	   /**
	     * 验证一组数据是否是整数
	     * @param str
	     * @return
	     */
	public static boolean isIntArray(String[] str){
		    
			for(int x=0;x<str.length;x++){
				if(!ValidateUtils.isRegex(str[x], "\\d+")){
					return false;
				}
			}
			return true;
	     }
	    
	    /**
	     * 验证一组数据是否是小数
	     * @param str
	     * @return
	     */
	public static boolean isDoubleArray(String[] str){
	    	for(int x=0;x<str.length;x++){
				if(!ValidateUtils.isRegex(str[x], "\\d+(\\.\\d+)?")){
					return false;
				}
			}
			return true;
			
		}
		
	    /**
		 * 判断一组数据是否是日期格式
		 * @param str
		 * @return
		 */
	public static boolean isDateArray(String [] str){
			for(int x=0;x<str.length;x++){
				if(!ValidateUtils.isRegex(str[x], "\\d{4}-\\d{2}-\\d{2}")){
					return false;
				}
			}
			return true;
		}
		
		/**
		 * 判断一组数据是否是日期时间格式
		 * @param str
		 * @return
		 */
	public static boolean isDateTimeArray(String[] str){
			
			for(int x=0;x<str.length;x++){
				if(!ValidateUtils.isRegex(str[x], "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")){
					return false;
				}
			}
			return true;
			
		}
		
		
		
	   //=================普通数据==============
			
		    /**
			  * 判断是否是整数
			  * @param str
			  * @return
			 */
	public static boolean isInt(String str){
				 
					return ValidateUtils.isRegex(str, "\\d+"); 
			 }
			
			 
			 /**
			  * 判断字符串是否是小数
			  * @param str
			  * @return
			  */
	public static boolean isDouble(String str){
				
				return ValidateUtils.isRegex(str, "\\d+(\\.\\d+)?"); 
			}
			
			/**
			 * 判断是否是日期格式
			 * @param str
			 * @return
			 */
	  public static boolean isDate(String str){
				
				return ValidateUtils.isRegex(str, "\\d{4}-\\d{2}-\\d{2}"); 
			}
		
		 
			/**
			 * 判断是否是日期时间格式
			 * @param str
			 * @return
			 */
	public static boolean isDateTime(String str){
				
				return ValidateUtils.isRegex(str, "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"); 
				
				
			}
			
			/**
			 * 验证正则表达式是否与字符串匹配
			 * @param str 要验证的字符串
			 * @param reg 正则表达式
			 * @return 满足返回true 否则返回false
			 */
	public static boolean isRegex(String str,String regex){
				
				if(isNotNull(str)){
					return str.matches(regex);
				}
				return false;
			}
			
	
			/**
			 * 判断字符串不为空才可以处理
			 * @param str
			 * @return 如果字符串为空返回false 否则返回true
			 */
	public static boolean isNotNull(String str){
				  
				return !(str==null||str.isEmpty()||"".equals(str));
				  
				   
			}
}
