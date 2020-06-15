package com.barunsw.imj.day08;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.constants.Gender;
import com.barunsw.imj.common.Person;
import com.barunsw.imj.day05.PersonDao;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	// Table 셀 인덱스 정의
	private int idx = 0;
	private final int TABLE_CELL_INDEX_ID = idx++;
	private final int TABLE_CELL_INDEX_NAME = idx++;
	private final int TABLE_CELL_INDEX_AGE = idx++;
	private final int TABLE_CELL_INDEX_GENDER = idx++;
	private final int TABLE_CELL_INDEX_PHONE = idx++;
	private final int TABLE_CELL_INDEX_ADDRESS = idx++;
	
	private JLabel jLabel_Id 			= new JLabel("Id");
	private JTextField jTextField_Id 	= new JTextField();
	
	private JLabel jLabel_Name 			= new JLabel("이름");
	private JTextField jTextField_Name 	= new JTextField();
	
	private JLabel jLabel_Age 		= new JLabel("나이");
	private JSpinner jSpinner_Age	= new JSpinner();
	
	private JLabel jLabel_Gender 			= new JLabel("성별");
	private ButtonGroup buttonGroup_Gender 	= new ButtonGroup();
	private JRadioButton jRadioButton_Man 	= new JRadioButton("남자");
	private JRadioButton jRadioButton_Woman = new JRadioButton("여자");
	
	private JLabel jLabel_Phone 			= new JLabel("전화번호");
	private JTextField jTextField_Phone 	= new JTextField();
	
	private JLabel jLabel_Address 			= new JLabel("주소");
	private JTextField jTextField_Address 	= new JTextField();
	
	public JButton jButton_Add 		= new JButton("추가");
	private JButton jButton_Change 	= new JButton("변경");
	private JButton jButton_Reload 	= new JButton("재조회");
	
	//private TestButtonPanel Panal_Button 	= new TestButtonPanel(this);
	private JPanel jPanel_Command	= new JPanel();

	private JScrollPane jScrollPane_List = new JScrollPane();
	private JTable jTable_List			 = new JTable();
	
	private GridBagLayout gridBagLayout = new GridBagLayout();
	
	private PersonDao personDao = new PersonDao();

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
		this.setLayout(gridBagLayout);
		
		jPanel_Command.setLayout(gridBagLayout);
		
		jLabel_Id.setPreferredSize(new Dimension(100, 22));
		jLabel_Name.setPreferredSize(new Dimension(100, 22));
		jLabel_Age.setPreferredSize(new Dimension(100, 22));
		
		this.add(jLabel_Id, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jLabel_Name, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Name, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		String name = jTextField_Name.toString();
		System.out.println(name);

		this.add(jLabel_Age, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));

		this.add(jSpinner_Age, new GridBagConstraints(3, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Gender, new GridBagConstraints(4, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		jRadioButton_Woman.setSelected(true);
		
		// 라디오버튼 그룹화
		buttonGroup_Gender.add(jRadioButton_Man);
		buttonGroup_Gender.add(jRadioButton_Woman);
		
		this.add(jRadioButton_Man, new GridBagConstraints(5, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jRadioButton_Woman, new GridBagConstraints(6, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Phone, new GridBagConstraints(0, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Phone, new GridBagConstraints(1, 2, 6, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Address, new GridBagConstraints(0, 3, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Address, new GridBagConstraints(1, 3, 6, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		
		// button
		this.add(jPanel_Command, new GridBagConstraints(0, 4, 7, 1, 
				1.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 
				0, 0));
		
		// table
		jScrollPane_List.getViewport().add(jTable_List);
		
		this.add(jScrollPane_List, new GridBagConstraints(0, 5, 7, 1, 
				1.0, 1.0, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
		jPanel_Command.add(jButton_Add, new GridBagConstraints(0, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
		jPanel_Command.add(jButton_Change, new GridBagConstraints(1, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 5),
				0, 0));
		jPanel_Command.add(jButton_Reload, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		jButton_Add.addActionListener(new Day08_TestPanel_jButton_Add_ActionListener(this));
		jButton_Change.addActionListener(new Day08_TestPanel_jButton_Change_ActionListener(this));
		jButton_Reload.addActionListener(new Day08_TestPanel_jButton_Reload_ActionListener(this));
	}
	
	private void initTable() {
		Vector<String> columnData = new Vector<>();
		columnData.add("ID");
		columnData.add("Name");
		columnData.add("Age");
		
		tableModel = new CommonTableModel(columnData);
		
		jTable_List.setModel(tableModel);
		
		// row의 높이 지정
		jTable_List.setRowHeight(22);
		
		// 컬럼별 width 지정
		int columnCount = tableModel.getColumnCount();
		for ( int i = 0; i < columnCount; i++ ) {
			TableColumn tableColumn = jTable_List.getColumnModel().getColumn(i);
			if ( i == 0 ) {
				tableColumn.setPreferredWidth(100);
			} else if ( i == 1 ) {
				tableColumn.setPreferredWidth(200);
			} else if ( i == 2 ) {
				tableColumn.setPreferredWidth(50);
			}
		}
	}
	
	public void initData() {
		List<Person> personList = personDao.getList(new Person());
		
		Vector tableData = new Vector();
		
		for ( Person onePerson : personList ) {
			Vector oneData = new Vector();
			
			oneData.add(onePerson.getId());
			oneData.add(onePerson.getName());
			oneData.add(onePerson.getAge());
			
			tableData.add(oneData);
		}
		
		tableModel.setData(tableData);
		tableModel.fireTableDataChanged();
		
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
	
	
	public JTextField getjTextField_Id() {
		return jTextField_Id;
	}

	public void setjTextField_Id(JTextField jTextField_Id) {
		this.jTextField_Id = jTextField_Id;
	}

	public JTextField getjTextField_Name() {
		return jTextField_Name;
	}

	public void setjTextField_Name(JTextField jTextField_Name) {
		this.jTextField_Name = jTextField_Name;
	}

	public JSpinner getjSpinner_Age() {
		return jSpinner_Age;
	}

	public void setjSpinner_Age(JSpinner jSpinner_Age) {
		this.jSpinner_Age = jSpinner_Age;
	}
	
	void jButton_Add_actionPerformed(ActionEvent e) {
		
		String id	= jTextField_Id.getText();
		int age		= (Integer) jSpinner_Age.getValue();
		String name = jTextField_Name.getText();
		
//		Person p 	= new Person(id, age, name);
//		
//		try {
//			personDao.add(p);
//			
//			initData();
//		} catch (Exception e1) {
//			LOGGER.error(e1.getMessage(), e1);
//		}
//		
	}
	
	void jButton_Change_actionPerformed(ActionEvent e) {
		String id 	= jTextField_Id.getText();
		int age 	= (Integer)jSpinner_Age.getValue();
		String name = jTextField_Name.getText();
		
//		Person p = new Person(id, age, name);
//		
//		try {
//			personDao.change(p);
//			
//			initData();
//		} 
//		catch (Exception e1) {
//			LOGGER.error(e1.getMessage(), e1);
//		}
	}
	
	void jButton_Reload_actionPerformed(ActionEvent e) {
		initData();	
	}
}

class Day08_TestPanel_jButton_Add_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public Day08_TestPanel_jButton_Add_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Add_actionPerformed(e);
		
	}
}

class Day08_TestPanel_jButton_Change_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public Day08_TestPanel_jButton_Change_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Change_actionPerformed(e);
		
	}
}

class Day08_TestPanel_jButton_Reload_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public Day08_TestPanel_jButton_Reload_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Reload_actionPerformed(e);
	}
}