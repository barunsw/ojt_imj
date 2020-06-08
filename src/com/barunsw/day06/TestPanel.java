package com.barunsw.day06;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);

	private JLabel jLabel_Name = new JLabel("이름:");
	private JTextField jTextField_Name = new JTextField();
	private JComboBox jComboBox_Type = new JComboBox();
	private JButton jButton_OK = new JButton("확인");
	private JCheckBox jCheckBox_Man = new JCheckBox("남자");
	private JRadioButton jRadioButton_Man = new JRadioButton("남자");
	private JRadioButton jRadioButton_Woman = new JRadioButton("여자");
	private ButtonGroup buttonGroup_Gender = new ButtonGroup();
	
	private JScrollPane jScrollPane_Chat = new JScrollPane();
	private JTextArea jTextArea_Chat = new JTextArea();
	
	public TestPanel() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		jTextField_Name.setPreferredSize(new Dimension(100, 22));
		jComboBox_Type.setPreferredSize(new Dimension(100, 22));
		
		jComboBox_Type.addItem("AAA");
		jComboBox_Type.addItem("BBB");
		
		Person p1 = new Person("12345", 20, "홍길동");
		
		jComboBox_Type.addItem(p1);
		
		//jTextArea_Chat.setPreferredSize(new Dimension(100, 44));
		jScrollPane_Chat.setPreferredSize(new Dimension(100, 44));
		
		this.add(jLabel_Name);
		this.add(jTextField_Name);
		this.add(jComboBox_Type);
		this.add(jButton_OK);
		this.add(jCheckBox_Man);
		this.add(jRadioButton_Man);
		this.add(jRadioButton_Woman);
		
		// 주의!!!
		jScrollPane_Chat.getViewport().add(jTextArea_Chat);
		
		this.add(jScrollPane_Chat);
		
		jRadioButton_Woman.setSelected(true);
		
		// 라디오버튼을 그룹으로 묶는다.
		buttonGroup_Gender.add(jRadioButton_Man);
		buttonGroup_Gender.add(jRadioButton_Woman);
		
//		jButton_OK.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				LOGGER.debug("OK 버튼 실행");
//			}
//		});
		
		jComboBox_Type.addItemListener(new TestPanel_jComboBox_Type_ItemListener(this));
		jButton_OK.addActionListener(new TestPanel_jButton_OK_ActionListener(this));
		jCheckBox_Man.addItemListener(new TestPanel_jCheckBox_Man_ItemListener(this));
	}
	
	void jComboBox_Type_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			LOGGER.debug("Type :" + jComboBox_Type.getSelectedItem());
			
			Object o = jComboBox_Type.getSelectedItem();
			if (o instanceof Person) {
				Person p = (Person)o;
			}
		}
	}
	
	void jButton_OK_actionPerformed(ActionEvent e) {
		LOGGER.debug("OK 버튼 실행");
	}
	
	void jCheckBox_Man_itemStateChanged(ItemEvent e) {
		LOGGER.debug("체크 박스:" + jCheckBox_Man.isSelected());
	}
}

class TestPanel_jComboBox_Type_ItemListener implements ItemListener {
	private TestPanel adaptee;
	
	public TestPanel_jComboBox_Type_ItemListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.jComboBox_Type_itemStateChanged(e);
	}	
}

class TestPanel_jButton_OK_ActionListener implements ActionListener {
	private TestPanel adaptee;
	
	public TestPanel_jButton_OK_ActionListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_OK_actionPerformed(e);
	}
}

class TestPanel_jCheckBox_Man_ItemListener implements ItemListener {
	private TestPanel adaptee;
	
	public TestPanel_jCheckBox_Man_ItemListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		adaptee.jCheckBox_Man_itemStateChanged(e);
	}	
}
