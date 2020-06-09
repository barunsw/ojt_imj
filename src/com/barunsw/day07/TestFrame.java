package com.barunsw.day07;

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
//		this.setLocation(frameX, frameY);
//		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		// 프레임 표시
		this.setVisible(true);
/*		
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowOpened");
			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowClosing");
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowClosed");
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowIconified");
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowDeiconified");
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowActivated");
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				LOGGER.debug("windowDeactivated");
				
			}
		});
*/
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
