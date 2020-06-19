package com.barunsw.imj.day14.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.day10.ClockFrame;
import com.barunsw.day13.RackviewFrame;
import com.barunsw.day13.RackviewPanel;

public class CurrentAlarmFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(ClockFrame.class);
	
	private CurrentAlarmPanel currentAlarmPanel = new CurrentAlarmPanel();
	
	public CurrentAlarmFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setContentPane(currentAlarmPanel);
		
		this.setBounds(0, 0, 600, 480);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		try {
			// Look and Feel UIManager를 통해 ui 쉽게 변경 가능
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		new CurrentAlarmFrame();
	}
}