package com.barunsw.imj.day12.calc.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CalcServer {
	private static Logger LOGGER = LogManager.getLogger(CalcServer.class);
	
	public CalcServer() {
		try {
			initRmi();
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initRmi() throws Exception {
		LOGGER.debug("+++ initRmi");
		CalcImpl calcImpl = new CalcImpl();
		
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("calc", calcImpl);
		
		LOGGER.debug("--- initRmi");
	}
	
	public static void main(String[] args) {
		new CalcServer();
	}
}
