package cn.li.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.li.dao.IMemberDao;
import cn.li.pojo.Actions;
import cn.li.pojo.Groups;
import cn.li.pojo.Member;
import cn.li.pojo.Role;
import cn.li.service.admin.IActionServiceAdmin;
import cn.li.service.admin.IGroupsServiceAdmin;
import cn.li.service.admin.IRoleServiceAdmin;
import cn.li.service.common.IMemberServiceCommon;
import cn.li.util.MD5Code;

public class Test {

	public static void main(String[] args) throws Exception{
		
		ApplicationContext ctx= new ClassPathXmlApplicationContext("applicationContext.xml");
		
		
		// IMemberServiceCommon imsc = ctx.getBean("memberServiceCommonImpl", IMemberServiceCommon.class);
		IActionServiceAdmin iasa=ctx.getBean("actionServiceAdminImpl", IActionServiceAdmin.class);
		IGroupsServiceAdmin igsa= ctx.getBean("groupsServiceAdminImpl",IGroupsServiceAdmin.class);
		IRoleServiceAdmin irsa=ctx.getBean("roleServiceAdnibImpl",IRoleServiceAdmin.class);
		/*Map<String,Object> map=iasa.list(1, 10, "title", "");
		List<Actions> list=(List<Actions>)map.get("allActions");
		Iterator<Actions> ite =list.iterator();
		while(ite.hasNext()){
			Actions g=ite.next();
			System.out.println(g);
		}*/
		//System.out.println(map.get("Count"));
		Map<String,Object> map=irsa.list();
		
		List<Role> list=(List<Role>)map.get("allRole");
		
		Iterator<Role> ite =list.iterator();
	  while(ite.hasNext()){
			Role r=ite.next();
			System.out.println(r.getGroupses());
		}
		
		//System.out.println(igsa.show(1));
	}

}
