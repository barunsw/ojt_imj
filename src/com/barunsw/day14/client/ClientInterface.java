package com.barunsw.day14.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.barunsw.day14.common.AlarmVo;

public interface ClientInterface extends Remote {
	public int push(AlarmVo alarmVo) throws RemoteException;
}
