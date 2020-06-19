package com.barunsw.day14.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.barunsw.day14.client.ClientInterface;

public interface ServerInterface extends Remote {
	public int register(ClientInterface clientIf) throws RemoteException;
	public int deregister(ClientInterface clientIf) throws RemoteException;
}
