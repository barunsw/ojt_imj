package com.barunsw.day01;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
	private static Logger LOGGER = LogManager.getLogger(Log4jTest.class);
	
	public static void main(String[] args) {
		LOGGER.debug("main");
	}
}
