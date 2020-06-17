package com.barunsw.imj.day12.chat.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.barunsw.imj.day12.chat.client.ChatRecvInterface;

public interface ChatInterface extends Remote {
	public int login(String userId, ChatRecvInterface chatRecvIf) throws RemoteException;
	public int logout(String userId) throws RemoteException;
	public int message(String message) throws RemoteException;
}
