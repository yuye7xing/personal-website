package gov.gdntax.test.home.bsdt.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/bsdt")
public class BsdtController {

	@RequestMapping("/index")
	public String test() {
			System.out.println("test");
	        return "nfzx/bsdt/bsdt4";
	}
	
}
