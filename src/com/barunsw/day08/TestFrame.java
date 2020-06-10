package com.barunsw.day08;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestFrame extends JFrame {
	private static Logger LOGGER = LogManager.getLogger(TestFrame.class);
	
	private static final int FRAME_WIDTH = 640;
	private static final int FRAME_HEIGHT = 480;
	
	private TestPanel jPanel_Test = new TestPanel();
	
	public TestFrame() {
		try {
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initComponent() {
		this.setContentPane(jPanel_Test);
		
		// 제목 변경
		this.setTitle("테스트 프레임");
		//this.setIconImage();
		
		// 화면의 해상도
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		LOGGER.debug("scrDim.width:" + scrDim.width);
		LOGGER.debug("scrDim.height:" + scrDim.height);
		
		// 가운데 오기 위한 좌표 계산
		int frameX = (scrDim.width - FRAME_WIDTH) / 2;
		int frameY = (scrDim.height - FRAME_HEIGHT) / 2;

		LOGGER.debug("frameX:" + frameX);
		LOGGER.debug("frameY:" + frameY);

		// 프레임의 위치, 크기 지정
		this.setBounds(new Rectangle(frameX, frameY, FRAME_WIDTH, FRAME_HEIGHT));
		
		// 프레임 표시
		this.setVisible(true);

		// 닫기 이벤트 처리
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		new TestFrame();
	}
}
