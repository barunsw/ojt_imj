package com.barunsw.day10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadTest {
	private static Logger LOGGER = LogManager.getLogger(ThreadTest.class);
	
	public static void main(String[] args) {
		TestThread1 t1 = new TestThread1();
		t1.setName("TestThread_1");
		t1.start();
		
//		Runnable r = new Runnable() {
//			@Override
//			public void run() {
//				int i = 2;
//				while (true) {
//					LOGGER.debug(String.format("[%d]", i));
//					i += 3;
//
//					try {
//						Thread.sleep(1000);
//					}
//					catch (Exception ex) {
//						LOGGER.error(ex.getMessage(), ex);
//					}
//				}
//			}
//		};
//		
//		TestThread2 t2 = new TestThread2(r);
//		t2.start();
		
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 2;
				while (true) {
					LOGGER.debug(String.format("[%d]", i));
					i += 3;

					try {
						Thread.sleep(1000);
					}
					catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			}
		});
		
		t2.start();
		
		
		int i = 0;
		while (true) {
			LOGGER.debug(String.format("[%d]", i));
			
			i += 3;
			
			try {
				Thread.sleep(1000);
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		}
	}
}

class TestThread1 extends Thread {
	private static Logger LOGGER = LogManager.getLogger(TestThread1.class);
	
	@Override
	public void run() {
		int i = 1;
		while (true) {
			LOGGER.debug(String.format("[%d]", i));
			i += 3;

			try {
				Thread.sleep(1000);
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		}
	}
}

class TestThread2 extends Thread {
	private static Logger LOGGER = LogManager.getLogger(TestThread2.class);
	
	public TestThread2(Runnable r) {
		super(r, "test2");
	}
}