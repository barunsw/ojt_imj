package com.barunsw.day12.chat.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatRecvImpl extends UnicastRemoteObject implements ChatRecvInterface {
	private static Logger LOGGER = LogManager.getLogger(ChatRecvImpl.class);
	
	public ChatRecvImpl() throws RemoteException {
		
	}
	
	@Override
	public int recv(String message) throws RemoteException {
		LOGGER.debug("recv message:" + message);
		return 0;
	}

}
