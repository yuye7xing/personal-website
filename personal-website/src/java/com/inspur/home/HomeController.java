package com.inspur.home;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	Log logger=LogFactory.getLog(getClass());
	
	/**
	 * 加载主菜单
	 * @return
	 */
	@RequestMapping(value = "/topmenu")
	@ResponseBody
	public String topMenu() {
		return "{menu:{rows:[{id:'model1',url:'',isLeaf:'false',text:'模块一',appCode:'-1'},{id:'model2',url:'',isLeaf:'false',text:'模块二',appCode:'-1'},{id:'model3',url:'',isLeaf:'false',text:'模块三',appCode:'-1'}]}}";
	}
	
	/**
	 * 加载子菜单
	 * @param parentMenu
	 * @return
	 */
	@RequestMapping(value = "/menu/submenu/{parentMenu}")
	@ResponseBody
	public String subMenu(@PathVariable String parentMenu) {
		return "{menu:{rows:[{id:'menu1',url:'',isLeaf:'false',text:'一级菜单',appCode:'-1',children:[{id:'menu11',url:'/jsp/test1.jsp',isLeaf:'true',text:'二级菜单',appCode:'-1'},{id:'menu12',url:'/jsp/test2.jsp',isLeaf:'true',text:'二级菜单',appCode:'-1'}]}]}}";
	}

}
