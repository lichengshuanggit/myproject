package cn.li.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.stereotype.Component;

import cn.li.dao.IGroupsDao;
import cn.li.pojo.Groups;
import cn.li.util.AbstractDaoImpl;

@Component //将本来的创建对象交由spring容器管理
public class GroupsDaoImpl extends AbstractDaoImpl implements IGroupsDao {

	@Override
	public List<Groups> findGroupsByRole(Integer rid) throws Exception {
		
		String sql="SELECT gid,title,note FROM groups WHERE gid IN(SELECT gid FROM role_groups WHERE rid=?)";
		Query query =super.getSQLQuery(sql);
		query.setResultTransformer(new AliasToBeanResultTransformer(Groups.class));
		query.setParameter(0, rid);
		
		return query.list();
	}

	@Override
	public boolean doCreate(Groups vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Groups vo) throws Exception {

        String hql="UPDATE Groups AS g SET g.title=? ,g.note=? WHERE g.gid=?";
        Query query =super.getQuery(hql);
        query.setParameter(0, vo.getTitle());
        query.setParameter(1, vo.getNote());
        query.setParameter(2, vo.getGid());
		return query.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Groups findById(Integer id) throws Exception {
		Session session=super.getSession();
		return (Groups) session.get(Groups.class, id);
	}

	@Override
	public List<Groups> findAll() throws Exception {
		
		return super.handleList(Groups.class);
	}

	@Override
	public List<Groups> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return super.handleListSplit(Groups.class, currentPage, lineSize, column, keyWord);
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return super.handleCount("Groups", column, keyWord);
	}

}
