package com.barunsw.day10;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClockPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(ClockPanel.class);
	
	private Font font = new Font(Font.DIALOG_INPUT, Font.PLAIN, 30);
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private ClockPanel_TimeThread t;
	
	private String currentTime;
	
	public ClockPanel() {
		try {
			initComponent();
			initThread();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setLayout(null);
//		this.setBackground(Color.red);
	}
	
	private void initThread() {
		t = new ClockPanel_TimeThread(this);
		t.start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		LOGGER.debug("paintComponent");
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (currentTime == null)
			return;
		
		// FontMetrics를 구하기 위해서는 g2d를 가져야 한다.
		Graphics2D g2d = (Graphics2D)g;
		g2d.setFont(font);

		FontMetrics fm = g2d.getFontMetrics();

		// currentTime 문자열의 width를 구한다.
		int strWidth = fm.stringWidth(currentTime);

		// 중앙 위치를 찾는다.
		int startX = (this.getWidth() - strWidth) / 2;
		int startY = (this.getHeight() - fm.getHeight()) / 2;		

		// 문자열을 그린다.
		g.setColor(Color.black);
		g.drawString(currentTime, startX, startY);
	}
	
	public String getCurrentTime() {
		Calendar cal = Calendar.getInstance();

		// 날짜 가공
		// cal.add(Calendar.DATE, -1);
		// cal.set(Calendar.HOUR, 0);
		
		return dateFormat.format(cal.getTime());
	}
	
	public void reloadTime() {
		currentTime = getCurrentTime();
		repaint();
	}
}

class ClockPanel_TimeThread extends Thread {
	private static Logger LOGGER = LogManager.getLogger(ClockPanel_TimeThread.class);
	
	private ClockPanel adaptee;
	
	public ClockPanel_TimeThread(ClockPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
			
			adaptee.reloadTime();
		}
	}
}