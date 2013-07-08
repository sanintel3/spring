package com.cts.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service("springExecutor")
public class SpringExecutor {
	
	@Async
	public void testAsyncExecutor(){
		System.out.println("Testing async executor");		
	}
	
	@Scheduled(cron = "1 0 0 6 * ?")
	public void testScheduleJob(){		
		System.out.println("Testing scheduler");		
	}

}
