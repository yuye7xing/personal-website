package personal.home.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import lucene.util.Aboutlucence;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import personal.home.service.Igetdatabymysql;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
	@Resource
	public Igetdatabymysql getdatabymysql;
	  @RequestMapping("/index")
	    public String index() throws IOException, ParseException {
		  List list=new ArrayList();
		  Date date=new Date();
//		  list=getdatabymysql.getdata();
		  Date date2=new Date();
		  System.out.println("查询耗时：------"+(date2.getTime()-date.getTime()));
//		  Aboutlucence.createIndex(list);
		  Date date3=new Date();
		  System.out.println("建索引耗时：------"+(date3.getTime()-date2.getTime()));
		  List reslist=Aboutlucence.searchindex("zlnr", "国税", 10);
		  System.out.println(reslist);
	      return "nfzx/tsgl/index";
	    }
}
