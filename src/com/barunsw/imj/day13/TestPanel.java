package com.barunsw.imj.day13;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.BoardVo;
import com.barunsw.imj.common.constants.BoardType;

public class TestPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(TestPanel.class);

	public static final int TEXT_WIDTH		= 30;
	public static final int TEXT_HEIGHT		= 18;
	
	public final int BOARD_START_X			= 27;
	public final int BOARD_START_Y_TOP		= 26;
	public final int BOARD_START_Y_BOTTOM 	= 307;
	
	public TestPanel() {
		try {
			initComponent();
			initData();
		}
		catch ( Exception e ) {
			LOGGER.debug(e.getMessage(), e);
		}
	}

	private void initComponent() {
		this.setLayout(null);
	}

	/**
	 *  Board 배치위치 설정
	 */
	private void initData() {
		// 연동에 의해 board 정보 조회
		List<BoardVo> boardList = getBoardData();
		
		for (BoardVo oneBoardVo : boardList) {
			int boardId = oneBoardVo.getBoardId();
			
			BoardPanel boardPanel = new BoardPanel(oneBoardVo);

			this.add(boardPanel, null);
			
//			LOGGER.debug(String.format("+++ TestPanel에 boardPanel(%s, %s) 추가",
//					boardPanel.getBoardWidth(), boardPanel.getBoardHeight()));
//			boardPanel.repaint();
			
//			public final int BOARD_START_X			= 27;
//			public final int BOARD_START_Y_TOP		= 26;
//			public final int BOARD_START_Y_BOTTOM 	= 307;
			
			
			if ( boardId < 20 ) {
				boardPanel.setBounds(BOARD_START_X + boardId * boardPanel.BOARD_WIDTH,
						BOARD_START_Y_TOP,
						boardPanel.getBoardWidth(),
						boardPanel.getBoardHeight());
			}
			else {
				boardPanel.setBounds(BOARD_START_X + ((boardId % 20) + 2) * boardPanel.BOARD_WIDTH,
						BOARD_START_Y_BOTTOM,
						boardPanel.getBoardWidth(),
						boardPanel.getBoardHeight());
			}
			LOGGER.debug("--- TestPanel에 {} boardPanel 추가", boardId);
		}
		
		repaint();
	}

	private List<BoardVo> getBoardData() {
		List<BoardVo> boardList = new ArrayList<>();
		
		/** 
		 * Severity 설정  for문
		 */
		for ( int i = 0; i < 37; i++ ) {
			BoardVo boardVo = new BoardVo();
			
			// 0, 1번 MPU 2 ~ 17번, 20 ~ 35번 SALC, 18번 36번 SRGU
			if ( i < 2 ) {
				boardVo.setBoardType(BoardType.MPU);
				boardVo.setBoardName("MPU" + i);
				boardVo.setSeverity((int) (Math.random()*4));
				boardVo.setBoardId(i);
			}
			else if ( i == 18 || i == 36 ) {
				boardVo.setBoardType(BoardType.SRGU);
				boardVo.setBoardName("SRGU" + i);
				boardVo.setSeverity((int) (Math.random()*4));
				boardVo.setBoardId(i);
			}
			else {
				boardVo.setBoardType(BoardType.SALC);
				boardVo.setBoardName("SALC" + i);
				boardVo.setSeverity((int) (Math.random()*4));
				boardVo.setBoardId(i);
			}
			boardList.add(boardVo);
		}
		
		return boardList;
	}

	@Override
	protected void paintComponent(Graphics g) {
		LOGGER.debug("paint_Background_Component");
		
		g.drawImage(ImageFactory.backgroundImageIcon.getImage(),
				0, 0, this);
	}
}
