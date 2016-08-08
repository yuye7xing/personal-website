package personal.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/test")
public class HomeController {
	  @RequestMapping("/index")
	    public String index() {
		  System.out.println("home测试");
	        return "nfzx/tsgl/index";
	    }
}
