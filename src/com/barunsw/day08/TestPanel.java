package com.barunsw.day08;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;
import com.barunsw.day05.PersonDao;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);

	private JButton jButton_Reload = new JButton("재조회");
	
	private JScrollPane jScrollPane_List = new JScrollPane();
	private JTable jTable_List = new JTable();
	
	private CommonTableModel tableModel;
	
	private PersonDao personDao = new PersonDao();
	
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

		this.add(jButton_Reload, new GridBagConstraints(0, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		jScrollPane_List.getViewport().add(jTable_List);
		
		this.add(jScrollPane_List, new GridBagConstraints(0, 1, 1, 1,
				1.0, 1.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		jButton_Reload.addActionListener(new TestPanel_jButton_Reload_ActionListener(this));
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
//			DefaultTableCellRenderer cellRenderer = (DefaultTableCellRenderer)tableColumn.getCellRenderer();
			
			if (i == 0) {
				tableColumn.setPreferredWidth(50);
//				cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
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
		
//		Thread t = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					try {
//						Thread.sleep(1000);
//					}
//					catch (Exception ex) {
//						LOGGER.error(ex.getMessage(), ex);
//					}
//
//					Vector oneData = new Vector();
//					oneData.add("홍길동");
//					oneData.add("30");
//
//					tableModel.addData(oneData);
//					tableModel.fireTableDataChanged();
//
//					LOGGER.debug("테이블 데이터 추가");
//				}
//			}
//		});
//		
//		t.start();
	}
	
	void jButton_Test_actionPerformed(ActionEvent e) {
		List<Person> personList = personDao.getList(new Person());
		
		Vector tableData = new Vector();
		
		for (Person onePerson : personList) {
			Vector oneData = new Vector();
			oneData.add(onePerson.getName());
			oneData.add(onePerson.getAge());
			
			tableData.add(oneData);
		}
		
		tableModel.setData(tableData);
		tableModel.fireTableDataChanged();
	}
}

class TestPanel_jButton_Reload_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Reload_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Test_actionPerformed(e);
	}
}