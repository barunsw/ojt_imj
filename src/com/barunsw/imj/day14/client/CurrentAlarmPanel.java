package com.barunsw.imj.day14.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.AlarmVo;
import com.barunsw.imj.day09.CommonTableModel;
import com.barunsw.imj.day14.server.ServerInterface;

public class CurrentAlarmPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(CurrentAlarmPanel.class);

	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JScrollPane jScrollPane_Alarm = new JScrollPane();
	
	private JTable jTable_Alarm = new JTable();
	
	private CommonTableModel tableModel;
	
	private ServerInterface serverIf;
	
	private ClientInterface clientIf;
	
	public CurrentAlarmPanel() {
		try {
			initRmi();
			initComponent();
			initTable();
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	private void initRmi() throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		LOGGER.debug(LocateRegistry.getRegistry());
		
		Remote remote = registry.lookup("alarm");
		
		if ( remote instanceof ServerInterface ) {
			serverIf = (ServerInterface) remote;
		}
		
	}
	
	private void initComponent() {
		this.setLayout(gridBagLayout);
		
		this.add(jScrollPane_Alarm, new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5), 0, 0));
		jScrollPane_Alarm.getViewport().add(jTable_Alarm);
	}
	
	private void initTable() {
		Vector columnInfo = new Vector();
		columnInfo.add("등급");
		columnInfo.add("ID");
		columnInfo.add("위치");
		columnInfo.add("원인");
		columnInfo.add("시간");
		
		tableModel = new CommonTableModel(columnInfo);
		
		jTable_Alarm.setModel(tableModel);
	}
	
	public void initData() throws Exception {
		
		clientIf = new ClientImpl(this);

		serverIf.register(clientIf);
		
		Vector tableData = new Vector();

		List<AlarmVo> alarmList = new ArrayList<>();
		
		for ( AlarmVo oneAlarm : alarmList ) {
			Vector oneData = new Vector();
			oneData.add(oneAlarm.getSeverity());
			oneData.add(oneAlarm.getAlarmId());
			oneData.add(oneAlarm.getLocation());
			oneData.add(oneAlarm.getAlarmMessage());
			oneData.add(oneAlarm.getEventTime());
			
			tableData.add(oneData);
			
		}
		LOGGER.debug("tableData: " + tableData);
		
		
//		Vector oneData = new Vector();
//		oneData.add(alarmVo.getAlarmId());
		
		tableModel.setData(tableData);
		tableModel.fireTableDataChanged();
		
	}
	
	public void push(AlarmVo alarmVo) throws Exception {
		Vector oneData = new Vector();
		oneData.add(alarmVo.getSeverity());
		oneData.add(alarmVo.getAlarmId());
		oneData.add(alarmVo.getLocation());
		oneData.add(alarmVo.getAlarmMessage());
		oneData.add(alarmVo.getEventTime());
		
		LOGGER.debug("push 메소드받음" + alarmVo);
		tableModel.addData(oneData);
		tableModel.fireTableDataChanged();
		
		
	}
}
