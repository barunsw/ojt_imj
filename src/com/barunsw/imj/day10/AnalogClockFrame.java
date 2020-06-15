package com.barunsw.imj.day10;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnalogClockFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(AnalogClockFrame.class);
	
	private AnalogClockPanel analogClockPanel = new AnalogClockPanel();
	
	public AnalogClockFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setContentPane(analogClockPanel);
		
		this.setBounds(0, 0, 600, 600);
		this.setVisible(true);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new AnalogClockFrame();
	}
}
