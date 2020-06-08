package com.barunsw.imj.day06;

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
	private static final int FRAME_HEIGHT = 640;
	
	private TestPanel jPanel_Test = new TestPanel();
	
	public TestFrame() {
		try {
			initComponent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void initComponent() {
		this.setContentPane(jPanel_Test);
		
		// 제목 설정
		this.setTitle("테스트 프레임");
		
		// 아이콘 설정
//		this.setIconImage(image);
		
		// 화면 해상도 설정
		Dimension scrDim = Toolkit.getDefaultToolkit().getScreenSize();
		
		LOGGER.debug("scrDim.width: " + scrDim.width);
		LOGGER.debug("scrDim.height: " + scrDim.height);
		
		// 프레임 위치 가운데 정렬
		int frameX = (scrDim.width - FRAME_WIDTH) / 2;
		int frameY = (scrDim.height - FRAME_HEIGHT) / 2;
		
		// 프레임 위치, 크기 설정
		this.setBounds(frameX, frameY, FRAME_WIDTH, FRAME_HEIGHT);
//		this.setBounds(new Rectangle(frameX, frameY, FRAME_WIDTH, FRAME_HEIGHT));

		
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
