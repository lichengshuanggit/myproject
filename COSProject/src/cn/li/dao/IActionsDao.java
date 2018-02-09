package cn.li.dao;

import java.util.List;
import java.util.Set;

import cn.li.pojo.Actions;

/**
 * 权限表
 * @author Administrator
 *
 */
public interface IActionsDao {
   
	
	/**
	 * 实现数据的增加操作
	 * @param vo 包含了要增加数据的VO对象
	 * @return 数据保存成功返回true，否则返回false
	 * @throws Exception SQL执行异常
	 */
	public boolean doCreate(Actions vo) throws Exception ;
	/**
	 * 实现数据的修改操作，本次修改是根据id进行全部字段数据的修改
	 * @param vo 包含了要修改数据的信息，一定要提供有ID内容
	 * @return 数据修改成功返回true，否则返回false
	 * @throws Exception SQL执行异常
	 */
	public boolean doUpdate(Actions vo) throws Exception ;
	/**
	 * 执行数据的批量删除操作，所有要删除的数据以Set集合的形式保存
	 * @param ids 包含了所有要删除的数据ID，不包含有重复内容
	 * @return 删除成功返回true（删除的数据个数与要删除的数据个数相同），否则返回false。
	 * @throws Exception SQL执行异常
	 */
	public boolean doRemoveBatch(Set<Integer> ids) throws Exception ;
	/**
	 * 根据权限组编号查询对应的权限信息
	 * @param gid
	 * @return
	 * @throws Exception
	 */
	public List<Actions> getAllBygroups(Integer gid) throws Exception;
	
	
	/**
	 * 查询指定数据表的全部记录，并且以集合的形式返回
	 * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用List集合返回，<br>
	 * 如果没有数据，那么集合的长度为0（size() == 0，不是null）
	 * @throws Exception SQL执行异常
	 */
	public List<Actions> findAll() throws Exception ;
	
	
	/**
	 * 分页进行数据的模糊查询，查询结果以集合的形式返回
	 * @param currentPage 当前所在的页
	 * @param lineSize 每页要显示数据行数
	 * @param column 要进行模糊查询的数据列
	 * @param keyWord 模糊查询的关键字
	 * @return 如果表中有数据，则所有的数据会封装为VO对象而后利用List集合返回，<br>
	 * 如果没有数据，那么集合的长度为0（size() == 0，不是null）
	 * @throws Exception SQL执行异常
	 */
	public List<Actions> findAllSplit(Integer currentPage, Integer lineSize, String column, String keyWord)
	   throws Exception;
	
	
	
	/**
	 * 进行模糊查询数据量的统计，如果表中没有记录统计的结果就是0
	 * @param <T>
	 * @param column 要进行模糊查询的数据列
	 * @param keyWord 模糊查询的关键字
	 * @return 返回表中的数据量，如果没有数据返回0
	 * @throws Exception SQL执行异常
	 */ 
	public Integer getAllCount(String column,String keyWord) throws Exception ;
}