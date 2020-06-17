package com.barunsw.imj.day10;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnalogClockPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(AnalogClockPanel.class);
	
	private final String TYPE_X = "X";
	private final String TYPE_Y = "Y";
	private final int CLOCK_FONT_SIZE = 25;
	
	private AnalogClockPanel_TimeThread analogClockPanelThread;
	
	private int centerX = 0;
	private int centerY = 0;
	
	
	private int hour	= 0;
	private int min		= 0;
	private int sec 	= 0;
	
	public AnalogClockPanel() {
		try {
			initComponent();
			initThread();
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		this.setLayout(null);
	}
	
	private void initThread() {
		analogClockPanelThread = new AnalogClockPanel_TimeThread(this);
		analogClockPanelThread.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getWidth());
		
		if ( getCurrentTime() == null ) {
			return;
		}

		centerX = getWidth() / 2;
		centerY = getHeight() / 2;
		
		hour	= getCurrentTime().get(Calendar.HOUR);
		min		= getCurrentTime().get(Calendar.MINUTE);
		sec 	= getCurrentTime().get(Calendar.SECOND);
		
        int r = 200;
		// 시간
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, CLOCK_FONT_SIZE));
        for ( int i = 1; i <= 12; i++ ) {
            int strX = (int) (centerX + r * Math.cos(Math.PI/2 - i*(Math.PI/6)));
            int strY = (int) (centerY - r * Math.sin(Math.PI/2 - i*(Math.PI/6)));
            
            g.drawString(Integer.toString(i), strX, strY);
        }
        
        // 초점
        int sx = makeXY(TYPE_X, (r-30), (sec * Math.PI/30));
        int sy = makeXY(TYPE_Y, (r-30), (sec * Math.PI/30));
        
        g.setColor(Color.RED);
        g.drawLine(centerX, centerY, sx, sy);
        
        // 분침
        int mx = makeXY(TYPE_X, (r-50), (min * Math.PI/30));
        int my = makeXY(TYPE_Y, (r-50), (min * Math.PI/30));
        
        g.setColor(Color.GRAY);
        g.drawLine(centerX, centerY, mx, my);
        
        // 시침
        int hx = makeXY(TYPE_X, (r-100), ((hour*60+min)/10)*(Math.PI/36));
        int hy = makeXY(TYPE_Y, (r-100), ((hour*60+min)/10)*(Math.PI/36));
        
        g.setColor(Color.BLACK);
        g.drawLine(centerX, centerY, hx, hy);
        
	}
	
	private int makeXY(String type, int r, double value) {
		int result = 0;
		if ( TYPE_X.equals(type) ) {
			result = centerX + (int) (r * Math.cos(Math.PI/2 - value));	
		}
		else {
			result = centerY - (int) (r * Math.sin(Math.PI/2 - value));
		}
		return result;
		
	}
	
	public Calendar getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		
		return cal;
	}

	public void reloadTime() {
		Calendar cal = getCurrentTime();
		repaint();
	}

}

class AnalogClockPanel_TimeThread extends Thread {
	private static Logger LOGGER = LogManager.getLogger(AnalogClockPanel_TimeThread.class);
	
	private AnalogClockPanel adaptee;
	
	public AnalogClockPanel_TimeThread(AnalogClockPanel adaptee) {
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
