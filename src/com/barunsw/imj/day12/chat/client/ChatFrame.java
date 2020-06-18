package com.barunsw.imj.day12.chat.client;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.UIManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChatFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(ChatFrame.class);
	
	private static final int FRAME_WIDTH 	= 600;
	private static final int FRAME_HEIGHT 	= 480;
	
	private ChatPanel chatPanel = new ChatPanel();
	
	public ChatFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		int x = (screenSize.width - FRAME_WIDTH) / 2;
		int y = (screenSize.height - FRAME_HEIGHT) / 2;
		
		this.setBounds(x, y, FRAME_WIDTH, FRAME_HEIGHT);
		this.setContentPane(chatPanel);
		this.setTitle("Chatting Program");
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
		new ChatFrame();
	}
}
