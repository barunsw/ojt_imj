package com.barunsw.imj.day14.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.AlarmVo;
import com.barunsw.imj.common.Severity;
import com.barunsw.imj.day14.client.ClientInterface;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {
	private static final Logger LOGGER = LogManager.getLogger(ServerImpl.class);
	
//	private Map<String, ClientInterface> clientRepo = new HashMap<>();
	
	private AlarmGenerator alarmGenerator;
	private AlarmVo alarmVo;
	
	private List<ClientInterface> clientRepo = new ArrayList<>();

	public ServerImpl() throws RemoteException {
		super();
	}
	
	public void setClientRepo(List<ClientInterface> clientRepo) {
		this.clientRepo = clientRepo;
	}
	
	public List<ClientInterface> getClientRepo() {
		return clientRepo;
	}
	
	@Override
	public int register(ClientInterface clientIf) throws RemoteException {
		// 클라이언트 실행
		clientRepo.add(clientIf);
//		
//		for ( ClientInterface oneclient : clientRepo) {
//			alarmVo.getSeverity();
//			alarmVo.getAlarmId();
//			alarmVo.getAlarmMessage();
//			alarmVo.getLocation();
//			alarmVo.getEventTime();
//			
//			oneclient.push(alarmVo);
//		}
		return 0;
	}

	@Override
	public int deregister(ClientInterface clientIf) throws RemoteException {
		// 클라이언트 종료시 
		clientRepo.remove(clientIf);
		return 0;
	}

}
