package cn.li.service.common.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.li.dao.IActionsDao;
import cn.li.dao.IGroupsDao;
import cn.li.dao.IMemberDao;
import cn.li.pojo.Actions;
import cn.li.pojo.Groups;
import cn.li.pojo.Member;
import cn.li.pojo.Role;
import cn.li.service.common.IMemberServiceCommon;

@Service 
public class MemberServiceCommonImpl implements IMemberServiceCommon {
    
	@Resource  //注入数据层实例化对象
	private IMemberDao iMemberDao;
	@Resource 
	private IGroupsDao iGroupsDao;
	@Resource 
	private IActionsDao iActionsDao;
	@Override
	public Member login(String mid, String password) throws Exception {
		
		Member member =new Member(); //创建一个非持久态的对象
		Member m= this.iMemberDao.findLogin(mid, password);
	
		if(m!=null){ //如果用户存在 
			//1.+根据用户的角色ID 找到用户的权限组信息
			List<Groups> allgroups =iGroupsDao.findGroupsByRole(m.getRole().getRid());
			//2. 查询权限组具备的权限
			Iterator<Groups> ite =allgroups.iterator();
			while(ite.hasNext()){
				Groups groups= ite.next();
				Set<Actions> allactions=new HashSet<Actions>();
				allactions.addAll(iActionsDao.getAllBygroups(groups.getGid()));
				//3.将查询到的权限设置到对象的权限组中
				groups.setActionses(allactions);
			}
			
			//4.将权限组信息设置到角色中；
			Role role =new Role(); //此处如果用 m.getRole() 
              //那么根据hibernate对象持久态的特性会再一次发送查询语句,造成不必要的性能损耗
			Set <Groups> allg =new TreeSet<Groups>();
			allg.addAll(allgroups);
			role.setGroupses(allg);
			
			//设置非持久态对象的属性
			member.setRole(role); 
			member.setLastlogin(m.getLastlogin());
			member.setMid(m.getMid());
			member.setPassword(m.getPassword());
			member.setLevel(m.getLevel());
			member.setPhone(m.getPhone());
			member.setEmail(m.getEmail());
			member.setPhoto(m.getPhoto());
			member.setLockflag(m.getLockflag());
			member.setName(m.getName());
			
			//==============================
			//更新用户最后一次登录时间
			m.setLastlogin(new Date());
		}
		
		
		return member;
	}
	@Override
	public boolean updatepassword(String mid, String oldpassword, String newpassword) throws Exception {
		Member m=iMemberDao.findLogin(mid, oldpassword);
		if(m!=null){
			return iMemberDao.daoUpdatePassword(mid, newpassword);
			
		}
		return false;
	}
	
	
	@Override
	public Member updatePre(String mid) throws Exception {
		// TODO Auto-generated method stub
		return this.iMemberDao.findById(mid);
	}
	@Override
	public boolean update(Member vo) throws Exception {
		// TODO Auto-generated method stub
		return this.iMemberDao.daoUpdate(vo);
	}

}
