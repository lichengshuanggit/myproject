package cn.li.service.admin.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.li.dao.IActionsDao;
import cn.li.pojo.Actions;
import cn.li.service.admin.IActionServiceAdmin;

@Service
public class ActionServiceAdminImpl implements IActionServiceAdmin {
   
	@Resource
	private IActionsDao iActionsDao;
	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("allActions", this.iActionsDao.findAllSplit(currentPage, lineSize, column, keyWord));
		map.put("Count", this.iActionsDao.getAllCount(column, keyWord));
		return map;
	}

	@Override
	public boolean update(Actions vo) throws Exception {
		// TODO Auto-generated method stub
		return this.iActionsDao.doUpdate(vo);
	}

}
