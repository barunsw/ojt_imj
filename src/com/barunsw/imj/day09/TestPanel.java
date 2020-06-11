package com.barunsw.imj.day09;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;
import com.barunsw.imj.common.constants.Gender;
import com.barunsw.imj.day09.controller.AddressBookInterface;
import com.barunsw.imj.day09.controller.MybatisAddressBookImpl;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	// Table 셀 인덱스 정의
	private int idx 							= 0;
	
	private final int TABLE_CELL_INDEX_ID 		= idx++;
	private final int TABLE_CELL_INDEX_NAME 	= idx++;
	private final int TABLE_CELL_INDEX_AGE 		= idx++;
	private final int TABLE_CELL_INDEX_GENDER 	= idx++;
	private final int TABLE_CELL_INDEX_PHONE 	= idx++;
	private final int TABLE_CELL_INDEX_ADDRESS 	= idx++;
	
	private JLabel jLabel_Id 				= new JLabel("Id");
	private JTextField jTextField_Id 		= new JTextField();
	
	private JLabel jLabel_Name 				= new JLabel("이름");
	private JTextField jTextField_Name 		= new JTextField();
	
	private JLabel jLabel_Age 				= new JLabel("나이");
	private JSpinner jSpinner_Age			= new JSpinner();
	
	private JLabel jLabel_Gender 			= new JLabel("성별");
	private ButtonGroup buttonGroup_Gender 	= new ButtonGroup();
	private JRadioButton jRadioButton_Man 	= new JRadioButton("남자");
	private JRadioButton jRadioButton_Woman = new JRadioButton("여자");
	
	private JLabel jLabel_Phone 			= new JLabel("전화번호");
	private JTextField jTextField_Phone 	= new JTextField();
	
	private JLabel jLabel_Address 			= new JLabel("주소");
	private JTextField jTextField_Address 	= new JTextField();
	
	public JButton jButton_Add 				= new JButton("추가");
	private JButton jButton_Change 			= new JButton("변경");
	private JButton jButton_Delete			= new JButton("삭제");
	private JButton jButton_Reload 			= new JButton("재조회");
	
	private JScrollPane jScrollPane_Word 	= new JScrollPane();
	private JTree jTree_Word 				= new JTree();
	
	private DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("ROOT");
	
	private DefaultTreeModel treeModel;
	private CommonTableModel tableModel;

	private JPanel jPanel_Command			= new JPanel();

	private JScrollPane jScrollPane_List 	= new JScrollPane();
	private JTable jTable_List			 	= new JTable();
	
	private GridBagLayout gridBagLayout 	= new GridBagLayout();
	
	private Person person = new Person();
	
	private AddressBookInterface addressBookInterface;
	
	public TestPanel() {
		try {
//			addressBookInterface = new FileAddressBookImpl();
			addressBookInterface = new MybatisAddressBookImpl();
//			addressBookInterface = new JdbcAddressBookImpl();
					
			initComponent();
			initTree();
			initTable();
			
			initData(null);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {		
		this.setLayout(gridBagLayout);
		
		jPanel_Command.setLayout(gridBagLayout);
		
//		jLabel_Id.setPreferredSize(new Dimension(100, 22));
//		jLabel_Name.setPreferredSize(new Dimension(100, 22));
//		jLabel_Age.setPreferredSize(new Dimension(100, 22));
		
		this.add(jLabel_Id, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Id, new GridBagConstraints(1, 0, 2, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		this.add(jLabel_Name, new GridBagConstraints(0, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Name, new GridBagConstraints(1, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));

		this.add(jLabel_Age, new GridBagConstraints(2, 1, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 0, 5, 5),
				0, 0));

		this.add(jSpinner_Age, new GridBagConstraints(3, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Gender, new GridBagConstraints(4, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		jRadioButton_Woman.setSelected(true);
		
		// 라디오버튼 그룹화
		buttonGroup_Gender.add(jRadioButton_Man);
		buttonGroup_Gender.add(jRadioButton_Woman);
		
		this.add(jRadioButton_Man, new GridBagConstraints(5, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		this.add(jRadioButton_Woman, new GridBagConstraints(6, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
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
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 5, 5, 0), 
				0, 0));

		jPanel_Command.add(jButton_Add, new GridBagConstraints(0, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 0),
				0, 0));
		
		jPanel_Command.add(jButton_Change, new GridBagConstraints(1, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 0),
				0, 0));
		
		jPanel_Command.add(jButton_Delete, new GridBagConstraints(2, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 0),
				0, 0));
		
		jPanel_Command.add(jButton_Reload, new GridBagConstraints(3, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		// tree
		jScrollPane_Word.setMinimumSize(new Dimension(50, 0));
		jScrollPane_Word.setPreferredSize(new Dimension(200, 0));
		
		jScrollPane_Word.getViewport().add(jTree_Word);
		
		this.add(jScrollPane_Word, new GridBagConstraints(0, 5, 1, 1, 
				1.0, 1.0, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 5, 5, 5),
				0, 0));
		// table
		jScrollPane_List.getViewport().add(jTable_List);
		
		this.add(jScrollPane_List, new GridBagConstraints(1, 5, 6, 1, 
				1.0, 1.0, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
		jButton_Add.addActionListener(new TestPanel_jButton_Add_ActionListener(this));
		jButton_Change.addActionListener(new TestPanel_jButton_Change_ActionListener(this));
		jButton_Delete.addActionListener(new TestPanel_jButton_Delete_ActionListener(this));
		jButton_Reload.addActionListener(new TestPanel_jButton_Reload_ActionListener(this));
	}
	
	private void initTree() {
		treeModel = new DefaultTreeModel(rootNode);
		jTree_Word.setModel(treeModel);
		
		// root 노드는 안 보여준다.
		jTree_Word.setRootVisible(false);

		DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("ㄱ");
		DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("ㄴ");
		DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("ㄷ");

		
		rootNode.add(node1);
		rootNode.add(node2);
		rootNode.add(node3);
		
		treeModel.reload();
		// tree 선택 이벤트
		jTree_Word.addTreeSelectionListener(new TestPanel_jTree_Selection_TreeSelectionListener(this));
		
		
	}
	
	private void initTable() {
		Vector<String> columnData = new Vector<>();
		columnData.add("ID");
		columnData.add("이름");
		columnData.add("나이");
		columnData.add("성별");
		columnData.add("전화번호");
		columnData.add("주소");
		
		tableModel = new CommonTableModel(columnData);
		
		jTable_List.setModel(tableModel);
		
		// row의 높이 지정
		jTable_List.setRowHeight(22);
		
		// 가운데 정렬 기능을 포함한 DefaultTableCellRenderer
		DefaultTableCellRenderer centerTableCellRenderer = new DefaultTableCellRenderer();
		centerTableCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				
		// 컬럼별 width 지정
		int columnCount = tableModel.getColumnCount();
		for ( int i = 0; i < columnCount; i++ ) {
			TableColumn tableColumn = jTable_List.getColumnModel().getColumn(i);
			
			JTableHeader header = jTable_List.getTableHeader();
			// 헤더 정렬
			((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
			
			// 데이터 Cell 관련 설정
			if ( i == TABLE_CELL_INDEX_ID ) {
				tableColumn.setPreferredWidth(100);
			} else if ( i == TABLE_CELL_INDEX_NAME ) {
				tableColumn.setPreferredWidth(100);
			} else if ( i == TABLE_CELL_INDEX_AGE ) {
				tableColumn.setPreferredWidth(50);
			} else if ( i == TABLE_CELL_INDEX_GENDER ) {
				tableColumn.setPreferredWidth(10);
			} else if ( i == TABLE_CELL_INDEX_PHONE ) {
				tableColumn.setPreferredWidth(200);
			} else if ( i == TABLE_CELL_INDEX_ADDRESS ) {
				tableColumn.setPreferredWidth(200);
			}
		}
		
		// table row 선택
		jTable_List.addMouseListener(new TestPanel_jTable_MouseListener(this));
	}
	
	public void initData(String filterWord) {
		List<Person> personList = addressBookInterface.getList(new Person());
		
		Vector tableData = new Vector();
		
		for ( Person onePerson : personList ) {
			Vector oneData = new Vector();
			
			oneData.add(onePerson.getId());
			oneData.add(onePerson.getName());
			oneData.add(onePerson.getAge());
			oneData.add(onePerson.getGender());
			oneData.add(onePerson.getPhone());
			oneData.add(onePerson.getAddress());
			
			tableData.add(oneData);
		}
		
		tableModel.setData(tableData);
		tableModel.fireTableDataChanged();
		
	}
//	void personInfo(Person person) {
//		String id	= jTextField_Id.getText();
//		String name = jTextField_Name.getText();
//		int age		= (Integer) jSpinner_Age.getValue();
//		Gender gender = Gender.M;
//		if( jRadioButton_Man.isSelected() ) {
//			gender = Gender.M;
//		} 
//		else if( jRadioButton_Man.isSelected() ) {
//			gender = Gender.W;
//		}
//		String phone = jTextField_Phone.getText();
//		String address = jTextField_Address.getText();
//		
//		person = new Person(id, name, age, gender, phone, address);
//		
//	}
	
	void jButton_Add_actionPerformed(ActionEvent e) {
		
//		personInfo(person);
		String id	= jTextField_Id.getText();
		String name = jTextField_Name.getText();
		int age		= (Integer) jSpinner_Age.getValue();
		Gender gender = Gender.M;
		if( jRadioButton_Man.isSelected() ) {
			gender = Gender.M;
		} 
		else if( jRadioButton_Woman.isSelected() ) {
			gender = Gender.W;
		}
		String phone = jTextField_Phone.getText();
		String address = jTextField_Address.getText();
		
		person = new Person(id, name, age, gender, phone, address);
		
		try {
			addressBookInterface.addPerson(person);
			
			initData(null);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
		
	}
	
	void jButton_Change_actionPerformed(ActionEvent e) {

		String id	= jTextField_Id.getText();
		String name = jTextField_Name.getText();
		int age		= (Integer) jSpinner_Age.getValue();
		Gender gender = Gender.M;
		if( jRadioButton_Man.isSelected() ) {
			gender = Gender.M;
		} 
		else if( jRadioButton_Woman.isSelected() ) {
			gender = Gender.W;
		}
		String phone = jTextField_Phone.getText();
		String address = jTextField_Address.getText();
		
		person = new Person(id, name, age, gender, phone, address);
		
		try {
			addressBookInterface.changePerson(person);
			
			initData(null);
		} 
		catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
	}
	
	void jButton_Delete_actionPerformed(ActionEvent e) {
		
		String id	= jTextField_Id.getText();
		String name = jTextField_Name.getText();
		int age		= (Integer) jSpinner_Age.getValue();
		Gender gender = Gender.M;
		if( jRadioButton_Man.isSelected() ) {
			gender = Gender.M;
		} 
		else if( jRadioButton_Woman.isSelected() ) {
			gender = Gender.W;
		}
		String phone = jTextField_Phone.getText();
		String address = jTextField_Address.getText();
		
		person = new Person(id, name, age, gender, phone, address);
		
		try {
			addressBookInterface.deletePerson(person);
			
			initData(null);
		} 
		catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
	}
	void jButton_Reload_actionPerformed(ActionEvent e) {
		initData(null);	
	}
	void jTree_Selection_valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node; 
		node = (DefaultMutableTreeNode) jTree_Word.getLastSelectedPathComponent();
		
		if(node == null) return;
		
		String nodename = (String) node.getUserObject();
	}

	public void jTable_Selection_mouseClicked(MouseEvent e) {
		int row = jTable_List.getSelectedRow();
		jTextField_Id.setText((String) jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_ID ));
		jTextField_Name.setText((String) jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_NAME ));
		LOGGER.debug(jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_GENDER));
		if(jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_GENDER).equals(Gender.M)) {
			jRadioButton_Man.isSelected();
		}
		else if(jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_GENDER).equals(Gender.W)) {
			jRadioButton_Woman.isSelected();
		}
		jTextField_Phone.setText((String) jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_PHONE ));
		jTextField_Address.setText((String) jTable_List.getModel().getValueAt(row, TABLE_CELL_INDEX_ADDRESS ));
}

class TestPanel_jButton_Add_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Add_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Add_actionPerformed(e);
		
	}
}

class TestPanel_jButton_Change_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Change_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Change_actionPerformed(e);
		
	}
}

class TestPanel_jButton_Delete_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Delete_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Delete_actionPerformed(e);
	}
}

class TestPanel_jButton_Reload_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_Reload_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Reload_actionPerformed(e);
	}
}

class TestPanel_jTree_Selection_TreeSelectionListener implements TreeSelectionListener {
	
	private TestPanel adaptee;
	
	public TestPanel_jTree_Selection_TreeSelectionListener (TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		adaptee.jTree_Selection_valueChanged(e);
	}


}

class TestPanel_jTable_MouseListener implements MouseListener {
	private TestPanel adaptee;
	
	public TestPanel_jTable_MouseListener (TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		adaptee.jTable_Selection_mouseClicked(e);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	
}

