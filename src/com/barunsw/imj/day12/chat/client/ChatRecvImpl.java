package com.barunsw.imj.day12.chat.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.day12.chat.client.ChatRecvInterface;

public class ChatRecvImpl extends UnicastRemoteObject implements ChatRecvInterface {
	
	private ChatPanel chatPanel;
	

	public ChatRecvImpl() throws RemoteException {
	}
	
	public ChatRecvImpl(ChatPanel chatPanel) throws RemoteException {
		super();
		this.chatPanel = chatPanel;
	}

	@Override
	public int recv(String message) throws RemoteException {
		chatPanel.recvMessage(message);
		return 0;
	}

}
