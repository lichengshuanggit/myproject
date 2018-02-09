package cn.li.action.manager;

import java.io.File;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Repository;

import com.opensymphony.xwork2.ActionSupport;

import cn.li.pojo.Member;
import cn.li.service.common.IMemberServiceCommon;
import cn.li.util.MD5Code;
import cn.li.util.action.AbstractAction;

/**
 * 项目经理控制器
 * @author Administrator
 *  
 */
@Repository
@ParentPackage("root")
@Namespace("/pages/jsp/manager")
@Action("actionmanager")
@InterceptorRef("managerStack")  //引入数据验证拦截器
@Results(value={@Result(name="updatepassword.error",location="/pages/jsp/manager/manager/manager_password_edit.jsp"),
		        @Result(name="udpdatepawpre",location="/pages/jsp/manager/manager/manager_password_edit.jsp"),
		        @Result(name="member.update",location="/pages/jsp/manager/manager/manager_manager_update.jsp")}) //数据验证失败返回路径
public class MemberActionManager extends AbstractAction {     
	
	@Resource
	private IMemberServiceCommon iMemberServiceCommon; //注入服务层对象
	private String newpassword;
	private String oldpassword;
	
	private Member user;
	private File photo;
	private String fileFileName;
	private String fileContentType;
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	public String updatepasswordper(){
		return "udpdatepawpre";
		
	}
	
	/**
	 * 密码修改
	 * @return
	 */
	public String updatepassword(){
		
		//取出用户id
		Member m =(Member)super.getRequest().getSession().getAttribute("manager");
		try {
			boolean flag=this.iMemberServiceCommon.updatepassword(m.getMid(), new MD5Code().getMD5ofStr(oldpassword), new MD5Code().getMD5ofStr(newpassword));
			if(flag){
				super.setUrlAndMessage("login.page", "updatepassword.success");
			}else{
				super.setUrlAndMessage("manager.index.pages", "updatepassword.defeat");
			}
		} catch (Exception e) {
			super.setUrlAndMessage("manager.index.pages", "updatepassword.defeat");
			e.printStackTrace();
		}
		return "forward.page";  
	}
	
	
	/**
	 * 跳转到修改用户信息页面
	 * @return
	 */
	public String updatePre(){
		//1.取得用户
		Member m =(Member) super.getRequest().getSession().getAttribute("manager");
		//2.将用户保存在request属性范围内
		super.getRequest().setAttribute("user", m);
		return "member.update";
	}
	
	/**
	 * 修改用户信息
	 * @return
	 */
	public String update(){
		
		if(this.photo!=null){
			//判断用户是否是第一次上传头像
			if("nophoto.jpg".equals(this.user.getPhoto())){
				this.user.setPhoto(super.getFileName(fileFileName));
				
			}
		}
		
		try {
			if(this.iMemberServiceCommon.update(this.user)){
				
				if(this.photo!=null){ //有文件上传 就保存文件
					//设置文件保存的路径
					String filePath=super.getApplication().getRealPath("/upload/user/")+this.user.getPhoto();
					System.out.println(filePath);
					//保存文件
					super.saveFile(filePath, this.photo);
				}
				//修改成功后还需要同步更新页面数据
				Member m =(Member) super.getRequest().getSession().getAttribute("manager");
				m.setName(this.user.getName());
				m.setPhone(this.user.getPhone());
				m.setEmail(this.user.getEmail());
				m.setPhoto(this.user.getPhoto());
				super.getRequest().getSession().setAttribute("manager", m);
				super.setUrlAndMessage("manager.update.pages", "update.success");
			}else{
				super.setUrlAndMessage("admin.update.pages", "update.defeat");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			super.setUrlAndMessage("manager.update.pages", "update.defeat");
			e.printStackTrace();
			
		}
		
		return "forward.page";
	}
	
	
	
	
	
	
	
	public String getNewpassword() {
		return newpassword;
	}


	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}


	public String getOldpassword() {
		return oldpassword;
	}


	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}



	public Member getUser() {
		return user;
	}

	public void setUser(Member user) {
		this.user = user;
	}

	public File getPhoto() {
		return photo;
	}

	public void setPhoto(File photo) {
		this.photo = photo;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
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
