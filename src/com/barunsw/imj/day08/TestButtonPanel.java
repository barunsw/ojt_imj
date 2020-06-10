package com.barunsw.imj.day08;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;
import com.barunsw.imj.day05.PersonDao;

public class TestButtonPanel extends JPanel {
	private static Logger LOGGER 	= LogManager.getLogger(TestPanel.class);
	
	public JButton jButton_Add 		= new JButton("추가");
	private JButton jButton_Change 	= new JButton("변경");
	private JButton jButton_Reload 	= new JButton("재조회");
	
	private PersonDao personDao 	= new PersonDao();
	
	private TestPanel parentPanel;
	private CommonTableModel tableModel;
	
	public TestButtonPanel() {}
	
	public TestButtonPanel(TestPanel parentPanel) {
		this.parentPanel = parentPanel;
		
		try {
			initComponent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	public TestButtonPanel(CommonTableModel tableModel) {
		this.tableModel = tableModel;
		
		try {
			initComponent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		this.setLayout(new GridBagLayout());
		
		this.add(jButton_Add, new GridBagConstraints(0, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jButton_Change, new GridBagConstraints(1, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 5),
				0, 0));
		this.add(jButton_Reload, new GridBagConstraints(2, 0, 1, 1,
				0.0, 0.0,
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL,
				new Insets(0, 0, 5, 5),
				0, 0));
		
		jButton_Add.addActionListener(new TestPanel_jButton_Add_ActionListener(this));
		jButton_Change.addActionListener(new TestPanel_jButton_Change_ActionListener(this));
		jButton_Reload.addActionListener(new TestPanel_jButton_Reload_ActionListener(this));
	}
	
	void jButton_Add_actionPerformed(ActionEvent e) {
		
		String id	= parentPanel.getjTextField_Id().getText();
		int age		= (Integer) parentPanel.getjSpinner_Age().getValue();
		String name = parentPanel.getjTextField_Name().getText();
		
		Person p 	= new Person(id, age, name);
		
		try {
			personDao.add(p);
			jButton_Reload_actionPerformed(e);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
		
	}
	
	void jButton_Change_actionPerformed(ActionEvent e) {
		
		String id 	= parentPanel.getjTextField_Id().getText();
		int age 	= (Integer) parentPanel.getjSpinner_Age().getValue();
		String name = parentPanel.getjTextField_Name().getText();
		
		Person p = new Person(id, age, name);
		
		try {
			personDao.change(p);
			jButton_Reload_actionPerformed(e);
		} catch (Exception e1) {
			LOGGER.error(e1.getMessage(), e1);
		}
	}
	
	void jButton_Reload_actionPerformed(ActionEvent e) {
		parentPanel.initData();
		
	}
	
}

class TestPanel_jButton_Add_ActionListener implements ActionListener {
	private TestButtonPanel adaptee;
	
	public TestPanel_jButton_Add_ActionListener(TestButtonPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Add_actionPerformed(e);
		
	}
}

class TestPanel_jButton_Change_ActionListener implements ActionListener {
	private TestButtonPanel adaptee;
	
	public TestPanel_jButton_Change_ActionListener(TestButtonPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Change_actionPerformed(e);
		
	}
}

class TestPanel_jButton_Reload_ActionListener implements ActionListener {
	private TestButtonPanel adaptee;
	
	public TestPanel_jButton_Reload_ActionListener(TestButtonPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Reload_actionPerformed(e);
	}
}