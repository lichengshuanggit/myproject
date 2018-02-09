package cn.li.service.admin;

import java.util.Map;

import cn.li.pojo.Groups;

/**
 * 管理员对权限组的管理
 * @author Administrator
 *
 */
public interface IGroupsServiceAdmin {
     
	/**
	 * 进行权限组的列表显示 执行如下功能<br>
	 * <li>调用IGroupsDao.findAllSplit()取出全部的分页数据</li>
	 * <li>调用IGroupsDao.getAllCount()取得全部数据的量</li>
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return   结果以Map的形式返回<br>
	 * <li>key="allGroups"  value=IGroupsDao.findAllSplit()</li>
	 * <li>key="Count"  value=IGroupsDao.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String,Object> list(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;
	
	/**
	 * 进行权限组更新 操作
	 * @param vo  保存了要更新数据的vo类对象
	 * @return  更新成功返回true 否则返回false
	 * @throws Exception
	 */
	public boolean update(Groups vo) throws Exception;
	
	/**
	 * 显示一个权限组的完整信息 同时查出此权限组对应的所有权限
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public Groups show(Integer gid) throws Exception;
}
