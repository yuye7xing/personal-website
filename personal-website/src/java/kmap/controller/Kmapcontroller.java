package kmap.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 知识图谱
 * @author zyz
 *
 */
@Controller
@RequestMapping(value = "/kmap")
public class Kmapcontroller {
	@RequestMapping("/index")
	public ModelAndView kmap(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String url="kmap/Kmap";
		HashMap<String, Object> paramap=new HashMap<String, Object>();
		return new ModelAndView(url,paramap);
	}
	
			
}
