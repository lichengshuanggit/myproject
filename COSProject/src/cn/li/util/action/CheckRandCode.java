package cn.li.util.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;

/**
 * 异步方式检查验证码
 * @author Administrator
 *
 */

@ParentPackage("root")  //注解实现包的定义的
@Namespace("/")
@Action("checkcode")  //注解定义Action
public class CheckRandCode extends AbstractAction {
    
	private String code;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	   
	/**
	 * 检查验证码是否正确
	 * 如果正确输出true 否则输出false
	 */
	public void chack(){
		
		String rand =(String)super.getRequest().getSession().getAttribute("rand");
		
		super.print(this.code.equalsIgnoreCase(rand));
		
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
	@Override
	public String getNameType() {
		// TODO Auto-generated method stub
		return null;
	}


}
