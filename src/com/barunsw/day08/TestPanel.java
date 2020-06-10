package com.barunsw.day08;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);

	private JScrollPane jScrollPane_List = new JScrollPane();
	private JTable jTable_List = new JTable();
	
	private CommonTableModel tableModel;
	
	public TestPanel() {
		try {
			initComponent();
			initTable();
			
			initData();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {		
		this.setLayout(new GridBagLayout());

		jScrollPane_List.getViewport().add(jTable_List);
		
		this.add(jScrollPane_List, new GridBagConstraints(0, 0, 1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
	}
	
	private void initTable() {
		Vector<String> columnData = new Vector<>();
		columnData.add("이름");
		columnData.add("나이");
		
		tableModel = new CommonTableModel(columnData);
		
		jTable_List.setModel(tableModel);
		
		// row의 높이 지정
		jTable_List.setRowHeight(22);
		
		// 컬럼별 width 지정
		int columnCount = tableModel.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			TableColumn tableColumn = jTable_List.getColumnModel().getColumn(i);
			if (i == 0) {
				tableColumn.setPreferredWidth(50);
			}
			else if (i == 1) {
				tableColumn.setPreferredWidth(200);
			}
		}
	}
	
	private void initData() {
		Vector dataInfo = new Vector();
		
		Vector oneData = new Vector();
		oneData.add("홍길동");
		oneData.add("30");
		
		dataInfo.add(oneData);
		
		tableModel.setData(dataInfo);
		
		// 테이블 내용이 변경되었음을 알린다.
//		tableModel.fireTableDataChanged();
		
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					}
					catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}

					Vector oneData = new Vector();
					oneData.add("홍길동");
					oneData.add("30");

					tableModel.addData(oneData);
					tableModel.fireTableDataChanged();

					LOGGER.debug("테이블 데이터 추가");
				}
			}
		});
		
		t.start();
	}
}
