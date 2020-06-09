package com.barunsw.imj.day07;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.day06.TestPanel;

public class TestButtonPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);
	
	private JButton jButton_Add 	= new JButton("추가");
	private JButton jButton_Change 	= new JButton("변경");
	
	private JPanel parentPanel;
	
	public TestButtonPanel() {}
	
	public TestButtonPanel(JPanel parentPanel) {
		this.parentPanel = parentPanel;
		
		try {
			initComponent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		this.setLayout(new GridBagLayout());
		
		this.add(jButton_Add, new GridBagConstraints(0, 0, 1, 1, 
				1.0, 0.0, 
				GridBagConstraints.EAST, GridBagConstraints.VERTICAL, 
				new Insets(0, 5, 5, 5),
				0, 0));
		
		this.add(jButton_Change, new GridBagConstraints(1, 0, 1, 1, 
				0.0, 0.0, 
				GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, 
				new Insets(0, 0, 5, 5),
				0, 0));
		
		jButton_Add.addActionListener(new TestPanel_jButton_Add_ActionListener(this));
	}
	
	void jButton_Add_actionPerformed(ActionEvent e) {
		LOGGER.debug("추가 버튼 실행");
		LOGGER.debug("");
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
