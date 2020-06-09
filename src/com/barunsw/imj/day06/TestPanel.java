package com.barunsw.imj.day06;

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

import com.barunsw.imj.common.Person;
import com.barunsw.imj.day05.PersonDao;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	private JLabel jLabel_Name = new JLabel("이름: ");
	private JTextField jTextField_Name = new JTextField();
	private JComboBox jCombobox_Type = new JComboBox();
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
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		jTextField_Name.setPreferredSize(new Dimension(100, 22));
		jCombobox_Type.setPreferredSize(new Dimension(100, 22));
		
		jCombobox_Type.addItem("AAA");
		jCombobox_Type.addItem("BBB");
		
		Person p1 = new Person("55", 30, "홍길동");
		
		jCombobox_Type.addItem(p1);
		
		// area에 scrollpane을 사용할 경우 scrollpane에 사이즈 지정
//		jTextArea_Chat.setPreferredSize(new Dimension(100, 44));
		jScrollPane_Chat.setPreferredSize(new Dimension(100, 44));
		
		this.add(jLabel_Name);
		this.add(jTextField_Name);
		this.add(jCombobox_Type);
		this.add(jButton_OK);
		this.add(jCheckBox_Man);
		this.add(jRadioButton_Man);
		this.add(jRadioButton_Woman);
		
		// getViewport 필수!!
		jScrollPane_Chat.getViewport().add(jTextArea_Chat);
//		jScrollPane_Chat.add(jTextArea_Chat);
		
		this.add(jScrollPane_Chat);
		
		jRadioButton_Woman.setSelected(true);
		
		// 라디오버튼 그룹화
		buttonGroup_Gender.add(jRadioButton_Man);
		buttonGroup_Gender.add(jRadioButton_Woman);
		
		jCombobox_Type.addItemListener(new TestPanel_jCombobox_Type_ItemListener(this));
	}

	void jComboBox_Type_itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			LOGGER.debug("Type: " + jCombobox_Type.getSelectedItem());

			Object o = jCombobox_Type.getSelectedItem();
			if (o instanceof Person) {
				Person p = (Person) o;
				LOGGER.debug(p.getId());
			}
		}
	}

	void jButton_OK_actionPerformed(ActionEvent e) {
		LOGGER.debug("Ok 버튼 실행");
	}
	
	void jCheckBox_Man_itemStateChanged(ItemEvent e) {
		LOGGER.debug("체크박스: " + jCheckBox_Man.isSelected());
	}
}


// Listener를 불러올 클래스 생성. 같은 패키지에서 사용 가능하도록 default class 생성
class TestPanel_jCombobox_Type_ItemListener implements ItemListener {
	private TestPanel adaptee;
	
	public TestPanel_jCombobox_Type_ItemListener(TestPanel adaptee) {
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

class TestPanel_jcheckBox_Man_ItemListener implements ItemListener {
	private TestPanel adaptee;
	
	public TestPanel_jcheckBox_Man_ItemListener(TestPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		adaptee.jCheckBox_Man_itemStateChanged(e);
	}
}
