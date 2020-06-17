package com.barunsw.imj.day12.calc.client;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.day12.calc.server.CalcInterface;

public class CalcClient {
	private static Logger LOGGER = LogManager.getLogger(CalcClient.class);
	
	public static void main(String[] args) {
		try {
			LOGGER.debug("rmi registry를 찾음");
			Registry registry = LocateRegistry.getRegistry();
			
			LOGGER.debug("calc이란 이름으로 remote 객체를 찾음");
			Remote remote = registry.lookup("calc");
			
			LOGGER.debug("Remote 객체를 CalcInterface로 캐스팅");
			CalcInterface calcIf = null;
			if ( remote instanceof CalcInterface )  {
				calcIf = (CalcInterface) remote;
			}
			
			LOGGER.debug("CalcInterface의 메소드 호출");
			int a = 10;
			int b = 2;
			
			calcIf.add(a, b);
			calcIf.substract(a, b);
			calcIf.multiply(a, b);
			calcIf.devide(a, b);
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
