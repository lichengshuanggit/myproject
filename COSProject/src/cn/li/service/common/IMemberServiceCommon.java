package cn.li.service.common;

import cn.li.pojo.Member;

/**
 * 用户服务层公共的操作
 * @author Administrator
 *
 */
public interface IMemberServiceCommon {
    
	/**
	 * 用户登录
	 * <li>调用数据层IMemberDao的findLogin方法进行用户查询</li>
	 * <li>利用角色编号调用IGroupsDao的findGroupsByRole找到对应的权限组</li>
	 * @param mid
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public Member login(String mid, String password) throws Exception;
	
	/**
	 * 根据id取得用户信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Member updatePre(String mid) throws Exception;
	
	/**
	 * 更新操作
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean update(Member vo) throws Exception;
	
	/**
	 * 修改用户明码操作
	 * <li>调用IMemberDao的findLogin判断旧密码是否正确</li>
	 * <li>调用IMemberDao的daoUpdatePassword进行密码修改</li>
	 * @param mid
	 * @param oldpassword
	 * @param newpassword
	 * @return
	 * @throws Exception
	 */
	public boolean updatepassword(String mid,String oldpassword,String newpassword) throws Exception;
}
