package com.barunsw.day12.calc.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcTest {
	private static Logger LOGGER = LogManager.getLogger(CalcTest.class);
	
	public static void main(String[] args) {
		CalcInterface calcIf = new CalcImpl();
		
		LOGGER.debug("1 + 1 = " + calcIf.add(1, 1));
	}
}
