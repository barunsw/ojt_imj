package com.barunsw.imj.day12.chat.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.barunsw.imj.day12.chat.client.ChatRecvInterface;

public class ChatImpl extends UnicastRemoteObject implements ChatInterface {
	private Map<String, ChatRecvInterface> clientRepo = new HashMap<>();

	public ChatImpl() throws RemoteException {
		super();
	}

	@Override
	public int login(String userId, ChatRecvInterface chatRecvIf) throws RemoteException {
		clientRepo.put(userId, chatRecvIf);
		for(ChatRecvInterface oneChatRecv : clientRepo.values()) {
			oneChatRecv.recv(userId + " 님이 들어왔습니다.");
		}
		
		return 0;
	}

	@Override
	public int logout(String userId) throws RemoteException {
		clientRepo.remove(userId);
		for(ChatRecvInterface oneChatRecv : clientRepo.values()) {
			oneChatRecv.recv(userId + " 님이 나갔습니다.");
		}
		return 0;
	}

	@Override
	public int message(String message) throws RemoteException {
		Collection<ChatRecvInterface> recvList = clientRepo.values();
		for (ChatRecvInterface oneChatRecv : recvList) {
			oneChatRecv.recv(message);
		}
		return 0;
	}
	
	
}
