package cn.li.util.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

/**
 * 完成控制层的一些公共操作
 * @author Administrator
 *
 */
public abstract class AbstractAction extends ActionSupport {
    
	// 定义分页有关的处理操作，分页的参数是简写参数
		private Integer cp = 1;  //当前页
		private Integer ls = 5;  //显示行数
		private String col;      //列名称
		private String kw;       //模糊查询关键字

		public void setCp(Integer cp) {
			this.cp = cp;
		}

		public void setLs(Integer ls) {
			this.ls = ls;
		}

		public void setCol(String col) {
			this.col = col;
		}

		public void setKw(String kw) {
			this.kw = kw;
		}

		public String getKw() {
			if (this.kw == null) {
				return "";
			}
			return kw;
		}

		public Integer getCp() {
			return cp;
		}

		public Integer getLs() {
			return ls;
		}

		public String getCol() {
			if (this.col == null || "".equals(this.col)) { // 没有设置查询列
				return this.getDefaultColumn();
			}
			return this.col;
		}

		/**
		 * 用来取得默认的分页显示列
		 * 
		 * @return
		 */
		public abstract String getDefaultColumn();

		/**
		 * 设置有可能进行模糊查询字段
		 * 
		 * @return
		 */
		public abstract String getColumnData();
		
		/**
		 * 处理分页传递到组件的操作属性
		 * @param allRecorders 当前所在的页面
		 * @param urlKey 要进行分页处理的URL，通过Pages.properties读取
		 * @param paramName 参数名称
		 * @param paramValue 参数内容
		 */
		public void handleSplit(Object allRecorders, String urlKey, String paramName,
				String paramValue) {
			this.getRequest().setAttribute("currentPage", this.getCp());
			this.getRequest().setAttribute("lineSize", this.getLs());
			this.getRequest().setAttribute("column", this.getCol());
			this.getRequest().setAttribute("keyWord", this.getKw());
			this.getRequest().setAttribute("url", this.geturl(urlKey));
			this.getRequest().setAttribute("allRecorders", allRecorders);
			this.getRequest().setAttribute("columnData", this.getColumnData());
			this.getRequest().setAttribute("paramName", paramName);
			this.getRequest().setAttribute("paramValue", paramValue);
		} 
	
	/**
	 * 获取跳转路径
	 * @param key
	 * @return
	 */
	public String geturl(String key){
		return super.getText(key);
		
	}
	
	public String getMessage(String key){
		return super.getText(key,new String []{this.getNameType()});
	}
	
	public void setUrlAndMessage(String urlkey,String msgkey){
		
		this.getRequest().setAttribute("url", this.geturl(urlkey));
		this.getRequest().setAttribute("msg", this.getMessage(msgkey));
	}
	
	/**
	 * 获取模块名称
	 * @return
	 */
	public abstract String getNameType();
	
	/**
	 * 取得response对象
	 * @return
	 */
	public HttpServletResponse getRespose(){
		return ServletActionContext.getResponse();
	}
	/**
	 * 取得request对象
	 * @return
	 */
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
		
	}
	
	/**
	 * @param origFileName
	 * @return
	 */
	public ServletContext getApplication(){
		
		return ServletActionContext.getServletContext();
		
	}
	
	
	//文件上传公共处理================
	/**
	 * 取得文件名称
	 * @param origFileName
	 * @return
	 */
	public static String getFileName(String origFileName){
		return UUID.randomUUID()+origFileName.substring(origFileName.lastIndexOf("."));
	}
	
	
	/**
	 * 保存文件
	 * @param path 保存的子路径
	 * @param file 要保存的文件信息
	 * @return 成返回true 失败返回false
	 */
	public static boolean saveFile(String filePath,File file) throws Exception{
		
		
		File outfile=new File(filePath); //定义输出文件
		if(!outfile.getParentFile().exists()){ //输出的文件路径不存在则创建
			outfile.getParentFile().mkdirs();
		}
		
		//输入流
		InputStream is=null;
		OutputStream os=null;
		try {
			 is=new FileInputStream(file);  //输入的源文件
			 os=new FileOutputStream(outfile); //定义输出文件
			 byte date[]=new byte[2048];
			 int temp=0;
			 while((temp=is.read(date))!=-1){
				 os.write(date, 0, temp);
			 }
			 return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(is!=null){
				is.close();
			}
			if(os!=null){
				os.close();
			}
			
		}
		return false;
	}
	
	
	/**
	 * 删除文件操作
	 * @param fileNmae
	 * @return
	 */
	public static boolean deleteFile(String filePath){
		
		//取得文件保存的完整路径
				String  path=ServletActionContext.getServletContext().getRealPath("/")+filePath;
				File file=new File(path); //定义输出文件
				if(file.getParentFile().exists()){ //如果文件存在则删除
					file.delete();
				}
				
		       return true;
		
	}
	//========================================================================================
	
	/**
	 * 数据输出
	 * @param obj
	 */
	public void print(Object obj){
		
		try {
			this.getRespose().getWriter().println(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	 /**
	  * 以json格式向前台输出数据
	  * @param obj
	  */
     public void printJSON(Object val){
		
    	 JSONObject json =new JSONObject();
    	 json.put("date", val);
		try {
			this.getRespose().getWriter().println(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
