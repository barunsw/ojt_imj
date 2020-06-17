package com.barunsw.imj.day12.chat.client;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.day12.chat.server.ChatInterface;

public class ChatClient {
	private static Logger LOGGER = LogManager.getLogger(ChatClient.class);
	
	public static void main(String[] args) {
		try {
			LOGGER.debug("rmi registry를 찾음");
			Registry registry = LocateRegistry.getRegistry();
			
			LOGGER.debug("chat이란 이름의 remote 객체를 찾는다.");
			Remote remote = registry.lookup("chat");
			
			LOGGER.debug("Remote 객체를 ChatInterface로 캐스팅한다.");
			ChatInterface chatIf = null;
			if ( remote instanceof ChatInterface ) {
				chatIf = (ChatInterface) remote;
			}
			
			// ChatRecvInterface 객체 생성
			ChatRecvInterface recvIf = new ChatRecvImpl();
			
			LOGGER.debug("ChatInterface의 메소드를 호출한다.");
			chatIf.login("철수", recvIf);
			//chatIf.logout("철수");
			chatIf.message("안녕하세요.");
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
			
}
