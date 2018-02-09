package cn.li.dao;

import cn.li.pojo.Member;

/**
 * 定义用户表数据层操作标准
 * @author Administrator
 *
 */
public interface IMemberDao {
    
	/**
	 * 用户登录操作
	 * @param mname   用户名
	 * @param password 密码
	 * @return  登录成功返回用户对象 失败返回null
	 * @throws Exception
	 */
	public Member findLogin(String mid,String password) throws Exception;
	
	/**
	 * 修改用户密码
	 * @param mid  用户id
	 * @param oldpassword 新密码
	 * @return
	 * @throws Exception
	 */
	public boolean daoUpdatePassword(String mid,String newpassword) throws Exception;
	
	/**
	 * 修改操作
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean daoUpdate(Member vo) throws Exception;
	
	/**
	 * 根据编号取得用户信息
	 * @param mid
	 * @return
	 * @throws Exception
	 */
	public Member findById(String mid) throws Exception;
}
