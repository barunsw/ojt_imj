package com.barunsw.day12.chat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.day12.chat.client.ChatRecvInterface;

public class ChatImpl extends UnicastRemoteObject implements ChatInterface {
	private static Logger LOGGER = LogManager.getLogger(ChatImpl.class);
	
	private Map<String, ChatRecvInterface> clientRepo = new HashMap<>();
	
	public ChatImpl() throws RemoteException {
		super();
	}
	
	@Override
	public int login(String userId, ChatRecvInterface chatRecvIf) throws RemoteException {
		LOGGER.debug("login:" + userId);
		clientRepo.put(userId, chatRecvIf);
		return 0;
	}

	@Override
	public int logout(String userId) throws RemoteException {
		LOGGER.debug("logout:" + userId);
		clientRepo.remove(userId);
		return 0;
	}

	@Override
	public int message(String message) throws RemoteException {
		LOGGER.debug("message:" + message);
		Collection<ChatRecvInterface> recvList = clientRepo.values();
		for (ChatRecvInterface oneChatRecv : recvList) {
			oneChatRecv.recv(message);
		}
		
		return 0;
	}
}
