package cn.li.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.aspectj.asm.IRelationship;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.li.dao.IRoleDao;
import cn.li.pojo.Role;
import cn.li.util.AbstractDaoImpl;

@Component  //<bean>
public class RoleDaoImpl extends AbstractDaoImpl implements IRoleDao {

	@Override
	public boolean doCreate(Role vo) throws Exception {
		// TODO Auto-generated method stub
		return super.getSession().save(vo)!=null;
	}

	@Override
	public boolean doUpdate(Role vo) throws Exception {
		
		super.getSession().update(vo);// 因为牵扯到级联数据的维护
		/**
		 * 如果自己手动更新那么就需要进行如下步骤
		 * 1.删除role_groups 关系表中对应的记录从新保存关系
		 */
		return true;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Role findById(Integer id) throws Exception {
		
		String hql="FROM Role AS r WHERE r.rid=?";
		Query query =super.getQuery(hql);
		query.setParameter(0, id);
		return (Role) query.uniqueResult();
	}

	@Override
	public List<Role> findAll() throws Exception {
		// TODO Auto-generated method stub
		return super.handleList(Role.class);
	}

	@Override
	public List<Role> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return super.handleListSplit(Role.class, currentPage, lineSize, column, keyWord);
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return super.handleCount("Role", column, keyWord);
	}

	
	@Override
	public Role findByTitle(String title) throws Exception {

       String hql="FROM Role AS r WHERE r.title=?";
       Query query =super.getQuery(hql);
       query.setParameter(0, title);
		return (Role) query.uniqueResult();
	}

	@Override
	public Role findByTitleNotId(String title, Integer rid) throws Exception {
		 String hql="FROM Role AS r WHERE r.title=? AND r.rid!=?";
	       Query query =super.getQuery(hql);
	       query.setParameter(0, title);
	       query.setParameter(1, rid);
			return (Role) query.uniqueResult();
	}

	@Override
	public Map<Integer, Boolean> findRoleGroups(Integer rid) throws Exception {
		// TODO Auto-generated method stub
		Map<Integer, Boolean> map =new HashMap<Integer, Boolean>();
		String sql="SELECT gid FROM role_groups WHERE rid=? ";
		Query query = super.getSQLQuery(sql);
		query.setParameter(0, rid);
		List gids = query.list();
		Iterator iter =gids.iterator();
		while(iter.hasNext()){
			Integer gid =(Integer) iter.next();
			map.put(gid, true);
		}
		return map;
	}

}
