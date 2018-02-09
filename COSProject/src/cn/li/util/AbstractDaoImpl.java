package cn.li.util;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

/**
 * 抽象出数据层一些重复的操作
 * @author Administrator
 *
 */
public abstract class AbstractDaoImpl {
    
	@Resource
	private SessionFactory sessionfactory;

	
	/**
	 * 设置SessionFactory 在子类实例化对象是调用设置
	 * @param sessionfactory
	 */
	public void setSessionfactory(SessionFactory sessionfactory) {
		this.sessionfactory = sessionfactory;
	}
	
	/**
	 * 取得SessionFactory 对象    一般涉及到二级缓存才会调用此操作
	 * @return
	 */
	public SessionFactory getSessionfactory() {
		return sessionfactory;
	}
	
	/**
	 * 取得Session对象
	 * @return
	 */
	public Session getSession(){
		return this.sessionfactory.getCurrentSession();
	}
	
	/***
	 * 取得query对象
	 * @param hql
	 * @return
	 */
	public Query getQuery(String hql){
		
		return this.getSession().createQuery(hql);
	}
	
	/**
	 * 取得SQL语句查询的query对象
	 * @param sql
	 * @return
	 */
	public Query getSQLQuery(String sql){
		return this.getSession().createSQLQuery(sql);
	}
	
	/**
	 * 取得Criteria对象
	 * @param cls
	 * @return
	 */
	public Criteria getCriteria(Class<?> cls){
		return this.getSession().createCriteria(cls);
	}
	
	/**
	 * 实现数据的统计查询
	 * @param className pojo的类名称
	 * @param column    查询的列名称
	 * @param keyword   关键字
	 * @return          返回记录数
	 */
	public Integer handleCount(String className,String column,String keyWord){
		
		String hql = "SELECT COUNT(*) FROM " + className + " AS p WHERE p."
				+ column + " LIKE ?";
		Query query = this.getQuery(hql);
		query.setParameter(0, "%" + keyWord + "%");
		return ((Long) query.uniqueResult()).intValue();
		
	}
	
	/**
	 * 实现分页查询
	 * @param cls 要处理的pojo类名称 
	 * @param currentpage   当前页
	 * @param linesize      每页显示行数
	 * @param column        列名称
	 * @param keyword       模糊关键字
	 * @return              结果以list的形式返回
	 */
	@SuppressWarnings("rawtypes")
	public List handleListSplit(Class<?> cls,Integer currentPage,Integer lineSize,String column,String keyWord){

		Criteria criteria = this.getCriteria(cls);
		criteria.add(Restrictions.like(column, "%" + keyWord + "%"));
		criteria.setFirstResult((currentPage - 1) * lineSize);
		criteria.setMaxResults(lineSize);
		return criteria.list() ; 
		
	}
	
	/**
	 * 
	 * 每页分页的列表显示
	 * @param cls
	 * @return
	 */
	public List handleList(Class<?> cls){
		Criteria criteria=this.getCriteria(cls);
		return criteria.list();
	}
	
}
