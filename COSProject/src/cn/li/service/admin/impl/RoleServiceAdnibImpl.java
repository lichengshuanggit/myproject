package cn.li.service.admin.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.li.dao.IGroupsDao;
import cn.li.dao.IRoleDao;
import cn.li.pojo.Groups;
import cn.li.pojo.Role;
import cn.li.service.admin.IRoleServiceAdmin;

@Service
public class RoleServiceAdnibImpl implements IRoleServiceAdmin {
    
	@Resource
	private IGroupsDao iGroupsDao;
	@Resource
	private IRoleDao   iRoleDao;
	
	@Override
	public Map<String, Object> insertPre() throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allGroups", this.iGroupsDao.findAll());
		
		return map;
	}
	
	@Override
	public Map<String, Object> updatetPre(Integer rid) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allGroups", this.iGroupsDao.findAll());
		map.put("Role", this.iRoleDao.findById(rid));
		map.put("gids", this.iRoleDao.findRoleGroups(rid));
		return map;
	}

	@Override
	public Map<String, Object> list() throws Exception {

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("allRole", this.iRoleDao.findAll());
		return map;
	}

	@Override
	public boolean insert(Role vo) throws Exception {
		// TODO Auto-generated method stub
		if(this.iRoleDao.findByTitle(vo.getTitle())==null){
			return this.iRoleDao.doCreate(vo);
		}
		return false;
	}

	@Override
	public boolean update(Role vo) throws Exception {
		if(this.iRoleDao.findByTitleNotId(vo.getTitle(), vo.getRid())==null){
			return this.iRoleDao.doUpdate(vo);
		}
		return false;
	}

	@Override
	public boolean check(String title) throws Exception {
		// TODO Auto-generated method stub
		return this.iRoleDao.findByTitle(title)==null;
	}

	@Override
	public boolean check(String title,Integer rid) throws Exception {
		// TODO Auto-generated method stub
		return this.iRoleDao.findByTitleNotId(title, rid)==null;
	}

}
