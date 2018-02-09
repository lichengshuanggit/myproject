package cn.li.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import cn.li.dao.IActionsDao;
import cn.li.pojo.Actions;
import cn.li.util.AbstractDaoImpl;

@Component
public class ActionsDaoImpl extends AbstractDaoImpl implements IActionsDao {

	@Override
	public List<Actions> getAllBygroups(Integer gid) throws Exception {
		
		String hql="FROM Actions AS a WHERE a.groups.gid=? ";
		Query query =super.getQuery(hql);
		query.setParameter(0, gid);
		return query.list();
	}

	@Override
	public List<Actions> findAll() throws Exception {
		// TODO Auto-generated method stub
		return super.handleList(Actions.class);
	}

	@Override
	public List<Actions> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		// TODO Auto-generated method stub
		return super.handleListSplit(Actions.class, currentPage, lineSize, column, keyWord);
	}

	@Override
	public Integer getAllCount(String column, String keyWord) throws Exception {
		// TODO Auto-generated method stub
		return super.handleCount("Actions", column, keyWord);
	}

	@Override
	public boolean doCreate(Actions vo) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean doUpdate(Actions vo) throws Exception {
		String hql="UPDATE Actions AS a SET a.title=?,a.url=? WHERE a.actid=?";
		Query query =super.getQuery(hql);
		query.setParameter(0, vo.getTitle());
		query.setParameter(1, vo.getUrl());
		query.setParameter(2, vo.getActid());
		return query.executeUpdate()>0;
	}

	@Override
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
