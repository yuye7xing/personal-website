package dsrw.test;

import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class dsrwtestimp implements Idsrwtest  {
	@Scheduled(cron="0/5 * *  * * ? ")
	 public void job1() {  
		String time = new SimpleDateFormat("MMM d，yyyy KK:mm:ss a",Locale.ENGLISH).format(System.currentTimeMillis());  
        System.out.println("任务进行1:"+time);  
	    }  
}
