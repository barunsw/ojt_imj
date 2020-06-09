package com.barunsw.imj.day07;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	
	private static final String PANEL_1 = "red";
	private static final String PANEL_2 = "blue";
	private static final String PANEL_3 = "green";

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

	private CardLayout cardLayout = new CardLayout();
	
	private JPanel jPanel_Red 		= new JPanel();
	private JPanel jPanel_Blue 		= new JPanel();
	private JPanel jPanel_Green 	= new JPanel();
	private JPanel jPanel_Yellow 	= new JPanel();
	private JPanel jPanel_Cyan 		= new JPanel();
	
	private TestButtonPanel Panal_Button 	= new TestButtonPanel(this);

	private JScrollPane jScrollPane_List 	= new JScrollPane();
	private JTable jTable_List 				= new JTable();
	
	public TestPanel() {
		try {
			initComponent();
			initTable();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		/*
		// BorderLayout
		this.setLayout(new BorderLayout());
		
		jLabel_Name.setPreferredSize(new Dimension(100, 22));
		
		this.add(jLabel_Name, BorderLayout.WEST);
		this.add(jTextField_Name);
		*/
		
		/*
		// GridLayout
		this.setLayout(new GridLayout(1, 3));

		this.add(jLabel_Name);
		this.add(jLabel_Name2);
		this.add(jTextField_Name);

		this.add(jLabel_Age);
		this.add(jTextField_Age);
		*/
		
		/*
		// CardLayout
		this.setLayout(cardLayout);
		
		jPanel_Red.setBackground(Color.red);
		jPanel_Blue.setBackground(Color.blue);
		jPanel_Green.setBackground(Color.green);
		
		// jpanel_Red가 나타남
		this.add(PANEL_1, jPanel_Red);
		this.add(PANEL_2, jPanel_Blue);
		this.add(PANEL_3, jPanel_Green);
		
		Thread t = new Thread(new Runnable() {
			private int count;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					count++;
					
					if( count % 3 == 0 ) {
						TestPanel.this.cardLayout.show(TestPanel.this, PANEL_1);
					} else if( count % 3 == 1 ) {
						TestPanel.this.cardLayout.show(TestPanel.this, PANEL_2);
					} else if( count % 3 == 2 ) {
						TestPanel.this.cardLayout.show(TestPanel.this, PANEL_3);
					}
					
					// 1초 sleep
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						LOGGER.error(e.getMessage(), e);
					}
				}
			}
		});
		
		t.start();
		*/
		
		/*
		// nullLayout
		this.setLayout(null);
		
		this.add(jLabel_Name);
		this.add(jTextField_Name);
		
		jLabel_Name.setBounds(10, 50, 100, 22);
		jTextField_Name.setBounds(120, 50, 100, 22);
		*/
		
		// GridBagLayout
		this.setLayout(new GridBagLayout());
//		jPanel_Red.setBackground(Color.red);
//		jPanel_Blue.setBackground(Color.blue);
//		jPanel_Green.setBackground(Color.green);
//		jPanel_Yellow.setBackground(Color.yellow);
//		jPanel_Cyan.setBackground(Color.cyan);
//		
//		jPanel_Blue.setPreferredSize(new Dimension(100, 100));
//		jPanel_Red.setPreferredSize(new Dimension(100, 100));
//		jPanel_Green.setPreferredSize(new Dimension(100, 100));
//		jPanel_Yellow.setPreferredSize(new Dimension(100, 100));
//		
//		this.add(jPanel_Green, new GridBagConstraints(0, 0, 1, 1,
//				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 0, 0),
//				0, 0));
//		
//		this.add(jPanel_Red, new GridBagConstraints(1, 0, 2, 1,
//				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 0, 0),
//				0, 0));
//		
//		this.add(jPanel_Cyan, new GridBagConstraints(1, 1, 1, 1,
//				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 0, 0),
//				0, 0));
//		
//		this.add(jPanel_Yellow, new GridBagConstraints(2, 1, 1, 1,
//				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 0, 0),
//				0, 0));
//		
//		this.add(jPanel_Blue, new GridBagConstraints(3, 1, 1, 1,
//				1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH,
//				new Insets(0, 0, 0, 0),
//				0, 0));
		
		jLabel_Name.setPreferredSize(new Dimension(100, 22));
		jLabel_Age.setPreferredSize(new Dimension(100, 22));
		
		this.add(jLabel_Name, new GridBagConstraints(0, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Name, new GridBagConstraints(1, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		String name = jTextField_Name.toString();
		System.out.println(name);

		this.add(jLabel_Age, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(5, 5, 5, 5),
				0, 0));

		this.add(jSpinner_Age, new GridBagConstraints(3, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Gender, new GridBagConstraints(4, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		jRadioButton_Woman.setSelected(true);
		
		// 라디오버튼 그룹화
		buttonGroup_Gender.add(jRadioButton_Man);
		buttonGroup_Gender.add(jRadioButton_Woman);
		
		this.add(jRadioButton_Man, new GridBagConstraints(5, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jRadioButton_Woman, new GridBagConstraints(6, 0, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(5, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Phone, new GridBagConstraints(0, 1, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Phone, new GridBagConstraints(1, 1, 6, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		this.add(jLabel_Address, new GridBagConstraints(0, 2, 1, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.BOTH,
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jTextField_Address, new GridBagConstraints(1, 2, 6, 1,
				1.0, 0.0,
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		
		// button
		this.add(Panal_Button, new GridBagConstraints(0, 3, 7, 1, 
				1.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, 
				new Insets(0, 0, 0, 0), 
				0, 0));
		
		// table
		jScrollPane_List.getViewport().add(jTable_List);
		
		this.add(jScrollPane_List, new GridBagConstraints(0, 4, 7, 1, 
				1.0, 1.0, 
				GridBagConstraints.CENTER, GridBagConstraints.BOTH, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
	}
	
	private void initTable() {
		DefaultTableModel tableModel = new DefaultTableModel();
		
		Vector<String> columnData = new Vector<>();
		columnData.add("이름");
		columnData.add("나이");
		columnData.add("성별");
		columnData.add("전화번호");
		columnData.add("주소");
		
		tableModel.setColumnIdentifiers(columnData);
		
		jTable_List.setModel(tableModel);
		
	}

}