package cn.li.dao;

import java.util.Map;

import cn.li.pojo.Role;

/**
 * 角色信息维护数据层
 * @author Administrator
 *
 */
public interface IRoleDao extends IDao<Role, Integer>{
    
	/**
	 * 根据角色名称查找角色  目的是进行ajax验证
	 * @return
	 * @throws Exception
	 */
	public Role findByTitle(String title) throws Exception;
	
	/**
	 * 根据角色名称查找角色 但是要排除就是编号 方便更新操作
	 * @param title
	 * @param rid
	 * @return
	 * @throws Exception
	 */
	public Role findByTitleNotId(String title,Integer rid) throws Exception;
	/**
	 * 根绝角色编号查询对应的权限组编号
	 * @param rid
	 * @return   结果以map形式返回<br>
	 * @throws Exception
	 */
	public Map<Integer,Boolean> findRoleGroups(Integer rid) throws Exception;
}
