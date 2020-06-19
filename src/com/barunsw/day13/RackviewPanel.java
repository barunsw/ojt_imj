package com.barunsw.day13;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RackviewPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(RackviewPanel.class);

	private Image backgroundImage = null;
	
	public RackviewPanel() {
		try {
			initImage();
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initImage() {
		ImageIcon backgroundIcon = new ImageIcon("images/tamms/background.png2");
		backgroundImage = backgroundIcon.getImage();
		
		LOGGER.debug("backgroundImage:" + backgroundImage);
	}
	
	private void initComponent() {
		this.setLayout(null);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.drawImage(backgroundImage, 0, 0, 300, 300, null);
	}
}
