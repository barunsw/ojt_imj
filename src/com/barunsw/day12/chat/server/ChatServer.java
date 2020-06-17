package com.barunsw.day12.chat.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatServer {
	private static Logger LOGGER = LogManager.getLogger(ChatServer.class);
	
	public ChatServer() {
		try {
			initRmi();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initRmi() throws Exception {
		LOGGER.debug("+++ initRmi");
		ChatImpl chatImpl = new ChatImpl();
		
		// 외부 rmi registry를 참조한다.
		//Registry registry = LocateRegistry.getRegistry();
		
		// 내부에 rmi registry를 생성한다.
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("chat", chatImpl);
		
		LOGGER.debug("--- initRmi");
	}
	
	public static void main(String[] args) {
		new ChatServer();
	}
}
