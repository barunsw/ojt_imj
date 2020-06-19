package com.barunsw.imj.day14.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.AlarmVo;
import com.barunsw.imj.common.Severity;
import com.barunsw.imj.day14.client.ClientInterface;

public class AlarmGenerator extends Thread {
	private static Logger LOGGER = LogManager.getLogger(AlarmGenerator.class);
	
	private Severity[] severityList = {Severity.CRITICAL, Severity.MAJOR, Severity.MINOR, Severity.NORMAL};
	private String[] alarmIdList = {
			"1000"
			, "2000"
			, "3000"
	};
	
	private String[] messageList = {
			"BOARD DOWN"
			, "PORT DOWN"
			, "NODE DOWN"
	};
	
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ServerImpl serverIf;
	private ClientInterface clientIf;
	

	
	public AlarmGenerator() {
		try {
			initRmi();
		}
		catch ( Exception ex ) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initRmi() throws Exception {
		LOGGER.debug("+++ initRmi");
		
		serverIf = new ServerImpl();

		
		
		Registry registry = LocateRegistry.createRegistry(1099);
		registry.bind("alarm", serverIf);
		
	}
	
	public void send() {
		
	}
	
	@Override
	public void run() {
		while (true) {
			int alarmIndex = (int)(Math.random() * alarmIdList.length);
			String alarmId	 	= alarmIdList[alarmIndex];
			String alarmMessage = messageList[alarmIndex];
			
			int sevIndex = (int)(Math.random() * severityList.length);
			Severity sev = severityList[sevIndex];
			
			AlarmVo alarmVo = new AlarmVo();
			alarmVo.setAlarmId(alarmId);
			alarmVo.setAlarmMessage(alarmMessage);
			alarmVo.setSeverity(sev);
			alarmVo.setLocation("-");
			alarmVo.setEventTime(sdf.format(Calendar.getInstance().getTime()));
			
//			LOGGER.debug(alarmVo);
			
//			for(ClientInterface onedata : clientRepo ) {
//				LOGGER.debug("clientRepo" + clientRepo);
//			}
			
			try {
				List<ClientInterface> clientRepo = serverIf.getClientRepo();
				
				if( clientRepo != null && clientRepo.size() > 0 ) {
					
					for(ClientInterface oneClient : clientRepo ) {
						oneClient.push(alarmVo);
					}
				}
				
				Thread.sleep(1000);
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}

		}
	}
	
	public static void main(String[] args) {
		new AlarmGenerator().start();
	}
}
