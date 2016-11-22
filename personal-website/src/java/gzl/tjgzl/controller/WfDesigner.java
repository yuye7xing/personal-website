package gzl.tjgzl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 添加工作流等相关方法
 * @author zyz
 *
 */
@Controller
@RequestMapping(value = "/wfdesigner")
public class WfDesigner {
	 @RequestMapping("/index")
	 public String index() {
		  return "gzl/designer/detail";
	 }
}