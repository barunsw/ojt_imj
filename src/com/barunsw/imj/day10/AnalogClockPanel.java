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
	
	private AnalogClockPanel_TimeThread t;
	
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
		t = new AnalogClockPanel_TimeThread(this);
		t.start();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getWidth());
		
		if ( getCurrentTime() == null ) {
			return;
		}

		hour	= getCurrentTime().get(Calendar.HOUR);
		min		= getCurrentTime().get(Calendar.MINUTE);
		sec 	= getCurrentTime().get(Calendar.SECOND);
		
		if ( sec == 60 ) {
			sec = 0;
			min ++;
		}
		if( min == 60 ) {
			min = 0;
			hour ++;
		}
		if( min == 60 && hour == 12 ) {
			hour = 0;
		}
        
        int r = 200;
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        
		// 시간
        g.setColor(Color.BLACK);
        g.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 25));
        for ( int i = 1; i <= 12; i++ ) {
            int strX = (int) (centerX + r * Math.cos(Math.PI/2 - i*(Math.PI/6)));
            int strY = (int) (centerY - r * Math.sin(Math.PI/2 - i*(Math.PI/6)));
            
            g.drawString(Integer.toString(i), strX, strY);
        }
        
        // 초점
        int sx = centerX + (int) ((r-30) * Math.cos(Math.PI/2 - sec * Math.PI/30));
        int sy = centerY - (int) ((r-30) * Math.sin(Math.PI/2 - sec * Math.PI/30));
        
        g.setColor(Color.RED);
        g.drawLine(centerX, centerY, sx, sy);
        
        // 분침
        int mx = centerX + (int) ((r-50) * Math.cos(Math.PI/2 - min * Math.PI/30));
        int my = centerY - (int) ((r-50) * Math.sin(Math.PI/2 - min * Math.PI/30));
        
        g.setColor(Color.GRAY);
        g.drawLine(centerX, centerY, mx, my);
        
        // 시침
        int hx = centerX + (int) ((r-100) * Math.cos(Math.PI/2 - ((hour*60+min)/10)*(Math.PI/36)));
        int hy = centerY - (int) ((r-100) * Math.sin(Math.PI/2 - ((hour*60+min)/10)*(Math.PI/36)));
        
        g.setColor(Color.BLACK);
        g.drawLine(centerX, centerY, hx, hy);
        
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
