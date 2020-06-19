package com.barunsw.day14.server;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.day14.common.AlarmVo;
import com.barunsw.day14.common.Severity;

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
	
	public AlarmGenerator() {
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
			
			LOGGER.debug(alarmVo);
			
			try {
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
