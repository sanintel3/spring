package com.cts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cts.di.DIService;

public class TestDI {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "classpath*:spring-*.xml" });
		
		DIService diService = (DIService) context.getBean("diService");
		try {
			diService.test();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
