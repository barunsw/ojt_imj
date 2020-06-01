package com.barunsw.imj.day01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
	private static Logger LOGGER = LogManager.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		LOGGER.debug("main");
//		LOGGER.trace("tracemain");
//		LOGGER.error("errormain");
	}
}
