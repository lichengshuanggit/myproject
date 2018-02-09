package cn.li.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.li.dao.IMemberDao;
import cn.li.pojo.Member;
import cn.li.util.AbstractDaoImpl;

@Component
public class MemberDaoImpl extends AbstractDaoImpl implements IMemberDao {
	
	/*@Autowired
	public MemberDaoImpl(SessionFactory sessionfactory){
		//子类实例化时默认调用父类构造将sessionfactory传过去
		super.setSessionfactory(sessionfactory);
	}*/
	
	@Override
	public Member findLogin(String mid, String password) throws Exception {
		 
		System.out.println(super.getSessionfactory().getCurrentSession());
		String hql ="FROM Member as m WHERE mid=? AND password=?";
		Query query =super.getQuery(hql);
		query.setParameter(0, mid);
		query.setParameter(1, password);
		
		return (Member)query.uniqueResult();
	}

	@Override
	public boolean daoUpdatePassword(String mid, String newpassword) throws Exception {
		
		String hql="UPDATE Member AS m SET m.password=? WHERE m.mid=?";
		Query query =super.getQuery(hql);
		query.setParameter(0, newpassword);
		query.setParameter(1, mid);
		System.out.println(newpassword);
		return query.executeUpdate()>0;
	}

	@Override
	public boolean daoUpdate(Member vo) throws Exception {
		
		String hql="UPDATE Member AS m SET m.name=?,m.phone=?,m.email=?,m.photo=? WHERE m.mid=?";
		Query query =super.getQuery(hql);
		
		query.setParameter(0, vo.getName());
		query.setParameter(1, vo.getPhone());
		query.setParameter(2, vo.getEmail());
		query.setParameter(3, vo.getPhoto());
		query.setParameter(4, vo.getMid());
		return query.executeUpdate()>0;
	}

	@Override
	public Member findById(String mid) throws Exception {
		
		Member m =(Member)super.getSession().get(Member.class, mid);
		return m;
	}

}
