package com.barunsw.day14.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.day09.CommonTableModel;

public class CurrentAlarmPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(CurrentAlarmPanel.class);

	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private JScrollPane jScrollPane_Alarm = new JScrollPane();
	
	private JTable jTable_Alarm = new JTable();
	
	private CommonTableModel tableModel;
	
	public CurrentAlarmPanel() {
		try {
			initComponent();
			initTable();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
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
}
