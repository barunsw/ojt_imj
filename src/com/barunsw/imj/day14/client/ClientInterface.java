package com.barunsw.imj.day14.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import com.barunsw.imj.common.AlarmVo;

public interface ClientInterface extends Remote {
	public int push(AlarmVo alarmVo) throws RemoteException;
}
