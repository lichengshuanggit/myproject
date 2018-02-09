package cn.li.service.admin.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.li.dao.IGroupsDao;
import cn.li.pojo.Groups;
import cn.li.service.admin.IGroupsServiceAdmin;

@Service
public class GroupsServiceAdminImpl implements IGroupsServiceAdmin {
    
	@Resource
	private IGroupsDao iGroupsDao; //注入数据层的对象
	@Override
	public Map<String, Object> list(Integer currentPage, Integer lineSize, String column, String keyWord)
			throws Exception {
		Map<String,Object> map =new HashMap<>();
		List<Groups> list =this.iGroupsDao.findAllSplit(currentPage, lineSize, column, keyWord);
		
		map.put("allGroups", list);
		map.put("Count", this.iGroupsDao.getAllCount(column, keyWord));
		return map;
	}

	@Override
	public boolean update(Groups vo) throws Exception {
		// TODO Auto-generated method stub
		return this.iGroupsDao.doUpdate(vo);
	}

	@Override
	public Groups show(Integer gid) throws Exception {
		
		Groups vo=this.iGroupsDao.findById(gid);
		if(vo!=null){ //延迟加载默认是打开的
			vo.getActionses().size(); //利用延迟加载取出权限信息
		}
		return vo;
	}

}
