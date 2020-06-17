package com.barunsw.imj.day12.chat.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRecvInterface extends Remote {
	public int recv(String message) throws RemoteException;
}
