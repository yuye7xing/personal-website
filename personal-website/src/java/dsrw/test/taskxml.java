package dsrw.test;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
@Component("myTaskXml")
public class taskxml {
	 public void job2() {  
	        System.out.println("任务进行2中。。。");  
	    }
	 @Scheduled(cron="0/5 * *  * * ? ")
	 public void job3() { 
		 	String time = new SimpleDateFormat("MMM d，yyyy KK:mm:ss a",Locale.ENGLISH).format(System.currentTimeMillis());  
	        System.out.println("任务进行3:"+time);  
	    }
}
