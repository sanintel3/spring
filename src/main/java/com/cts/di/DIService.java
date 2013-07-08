package com.cts.di;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.stereotype.Service;

@Service("diService")
public class DIService {

	@NumberFormat(style = Style.PERCENT)
	private BigDecimal decimal = new BigDecimal(100.123);

	//@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date date = new Date();

	public void test() {

		System.out.println(decimal);
		System.out.println(date);

	}

}
