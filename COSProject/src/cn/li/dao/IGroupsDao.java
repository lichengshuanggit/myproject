package cn.li.dao;

import java.util.List;

import cn.li.pojo.Groups;

/**
 * 权限组
 * @author Administrator
 *
 */
public interface IGroupsDao extends IDao<Groups, Integer>{
     
	/**
	 * 根据角色编号查找权限组信息
	 * @param rid 角色编号
	 * @return
	 * @throws Exception
	 */
	public List<Groups> findGroupsByRole(Integer rid)throws Exception;
}
