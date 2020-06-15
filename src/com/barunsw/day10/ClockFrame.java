package com.barunsw.day10;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClockFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(ClockFrame.class);
	
	private ClockPanel clockPanel = new ClockPanel();
	
	public ClockFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setContentPane(clockPanel);
		
		this.setBounds(0, 0, 600, 480);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new ClockFrame();
	}
}
