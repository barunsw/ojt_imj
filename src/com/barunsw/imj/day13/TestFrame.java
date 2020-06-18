package com.barunsw.imj.day13;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(TestFrame.class);
	
	public static final int WIDTH 	= 870;
	public static final int HEIGHT 	= 635;
	
	private TestPanel testPanel = new TestPanel();
	
	public TestFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() throws Exception {
		this.setTitle("Rack View");
		// 사용자가이 프레임에서 "닫기"를 시작할 때 기본적으로 수행되는 작업을 설정
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		this.setContentPane(testPanel);
		
		// 윈도우 이벤트
		this.addWindowListener(new TestFrame_this_WindowAdapter(this));
	}
	
	void windowClosing(WindowEvent e) {
		LOGGER.debug("종료 버튼이 선택되었습니다.");
		
		int result = JOptionPane.showConfirmDialog(TestFrame.this, 
				"정말로 종료하시겠습니까?", "EXIT", JOptionPane.OK_CANCEL_OPTION);
		
		if ( result == JOptionPane.OK_OPTION ) {
			System.exit(0);
		}
	}
	
}

class TestFrame_this_WindowAdapter extends WindowAdapter {
	private TestFrame adaptee;
	
	public TestFrame_this_WindowAdapter(TestFrame adaptee) {
		this.adaptee = adaptee;
	}
	@Override
	public void windowClosing(WindowEvent e) {
		adaptee.windowClosing(e);
	}
}