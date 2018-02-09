package cn.li.service.admin;

import java.util.Map;

import cn.li.pojo.Actions;

/**
 * 管理员操作接口定义
 * @author Administrator
 *
 */
public interface IActionServiceAdmin {
    
	/**
	 * 数据的列表显示操作<br>
	 * <li>调用IActionsDao.findAllSplit()取得数据</li>
	 * <li>调用IActionsDao.getAllCount()取得数据总和</li>
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return  结果以map形式返回 <br>
	 * <li>key="allActions"  value=IActionsDao.findAllSplit()</li>
	 * <li>key="Count"  value=IActionsDao.getAllCount()</li>
	 * @throws Exception
	 */
	public Map<String,Object> list(Integer currentPage, Integer lineSize, String column, String keyWord) throws Exception;
	
	/**
	 * 权限更新
	 * @param vo 保存了更新数据的Actions 对象
	 * @return
	 * @throws Exception
	 */
	public boolean update(Actions vo) throws Exception;
}
