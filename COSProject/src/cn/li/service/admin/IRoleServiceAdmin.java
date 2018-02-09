package cn.li.service.admin;

import java.util.Map;

import cn.li.pojo.Role;

/**
 * 管理员对角色信息维护  服务层接口
 * @author Administrator
 *
 */
public interface IRoleServiceAdmin {
   
	 /**
	  * 角色修改前 对权限组的列表显示<br>
	  * <li>因为修改操作需要用到权限组所以 调用IGroupsDao.findAll来获取所有的权限组</li>
	  * <li>修改前需要根据角色的编号查出要修改角色的信息所以调用IRoleDao.findById</li>
	  * <li>为了可以进行数据的回填操作，需要使用IRoleDAO.findRoleGroups()查询每个角色对应的权限组编号<li>
	  * @param vo
	  * @return  结果以map的形式返回
	  * <li>key="allGroups" value=IGroupsDao.findAll</li>
	  * <li>key="Role" value=IRoleDao.findById</li>
	  * <li>key = gids、value = IRoleDAO.findRoleGroups()</li>
	  * @throws Exception
	  */
	public Map<String,Object> updatetPre(Integer rid) throws Exception;
	
	 /**
	  * 角色增加前 对权限组的列表显示<br>
	  * <li>增加调用IGroupsDao.findAll</li>
	  * @param vo
	  * @return  结果以map的形式返回
	  * <li>key="allGroups" value=IGroupsDao.findAll</li>
	  * @throws Exception
	  */
	public Map<String,Object> insertPre() throws Exception;
	
	/**
	 * 角色信息列表显示
	 * @return 结果以map的形式返回<br>
	 * <li>key="allRole" value=IRoleDao.findAll</li>
	 * @throws Exception
	 */
	public Map<String,Object> list() throws Exception;
	
	/**
	 * 
	 * 角色信息添加操作<br>
	 * <li>为了保证添加的角色名称不重复调用IRoleDao.findByTitle()进行验证</li>
	 * <li>修改时调用IRoleDao.doCreate()自动维护关联数据</li>
	 * @param vo
	 * @return  
	 * @throws Exception
	 */
	public boolean insert(Role vo) throws Exception;
	
	/**
	 * <li>检查排除指定的角色之外的其它角色名称是否存在，调用IRoleDAO.findByTitleAndNotId()方法</li>
	 * <li>修改时调用IRoleDao.doUpdate()自动维护关联数据</li>
	 * @param vo 保存了角色信息 和权限组信息
	 * @return
	 * @throws Exception
	 */
	public boolean update(Role vo) throws Exception;
	
	/**
	 * 利用ajax检查给定的角色名称是否存在
	 * @param title
	 * @return   如果存在返回false 否则返回true
	 * @throws Exception
	 */
	public boolean check(String title) throws Exception;
	
	/**
	 * 利用ajax验证排除指定编号后是否还有重复的名称  
	 * @param title 角色的名称
	 * @param rid   角色的编号
	 * @return
	 * @throws Exception
	 */
	public boolean check(String title,Integer rid) throws Exception;
}
