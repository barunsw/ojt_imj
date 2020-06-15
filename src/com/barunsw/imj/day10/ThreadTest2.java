package com.barunsw.imj.day10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadTest2 {
	private static Logger LOGGER = LogManager.getLogger(ThreadTest2.class);
	
	public static void main(String[] args) {
		final int THREAD_NUM = 10;
		final int SLEEP_TIME = 5000;
		
		for (int i = 0; i < THREAD_NUM; i++) {
			final int num = i;
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					int startNum = num;
					
					while (true) {
						LOGGER.debug(String.format("[%d]", startNum));
						
						startNum += THREAD_NUM;
						
						try {
							Thread.sleep(SLEEP_TIME);
						}
						catch (Exception ex) {
							LOGGER.error(ex.getMessage(), ex);
						}
					}
				}
			});
			
			t.start();
		}
	}
}
