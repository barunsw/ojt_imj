package com.barunsw.imj.day11;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChattingFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(ChattingFrame.class);
	
	private ChattingPanel chattingPanel = new ChattingPanel();
	
	public ChattingFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setContentPane(chattingPanel);
		this.setTitle("Chatting Program");
		
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
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage(), e);
		}
		new ChattingFrame();
	}
}
