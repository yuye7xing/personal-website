package personal.home.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import personal.home.dao.getdataDao;

@Service
public class getdataservice implements Igetdatabymysql {
    @Resource
    getdataDao dao;
	@Override
	public List getdata() {
		List datalist=new ArrayList();
		datalist=dao.getdatalist();
		return datalist;
	}

}
