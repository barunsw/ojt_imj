package com.barunsw.imj.day14.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.AlarmVo;

public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
	private static final Logger LOGGER = LogManager.getLogger(ClientImpl.class);

	private CurrentAlarmPanel parent;
	
	private AlarmVo alarmVo;
	
	public ClientImpl() throws RemoteException {
		super();
	}

	public ClientImpl(CurrentAlarmPanel parent) throws RemoteException {
		super();
		this.parent = parent;
	}
	
	@Override
	public int push(AlarmVo alarmVo) throws RemoteException {   
		
		LOGGER.debug("+++ push");
		LOGGER.debug(alarmVo);
		try {
			parent.push(alarmVo);
		} 
		catch ( Exception ex ) {
			LOGGER.debug(ex.getMessage(), ex);
		}
		LOGGER.debug("--- push");
		return 0;
	}

}
