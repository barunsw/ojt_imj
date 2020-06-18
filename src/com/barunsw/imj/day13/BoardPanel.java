package com.barunsw.imj.day13;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.BoardVo;

public class BoardPanel extends JPanel {
	private static final Logger LOGGER = LogManager.getLogger(BoardPanel.class);
	
	public static final int BOARD_WIDTH 	= 40;
	public static final int BOARD_HEIGHT 	= 271;

	public final int SRGU_BOARD_WIDTH = 80;
	public final int MPU_BOARD_HEIGHT = 550;
	
	private BoardVo boardVo;
	
	public BoardPanel(BoardVo boardVo) {
		LOGGER.debug("board 생성");
		this.boardVo = boardVo;
	}
	
	public int getBoardWidth() {
		// Board의 너비를 가져옴
		int width = 0;
		
		switch (boardVo.getBoardType()) {
		case MPU:
		case SALC:
			width = BOARD_WIDTH;
			break;
		case SRGU:
			width = BOARD_WIDTH * 2;
			break;
		}
		
		return width;
	}
	
	public int getBoardHeight() {
		int height = 0;
		
		switch (boardVo.getBoardType()) {
		case MPU:
			height = MPU_BOARD_HEIGHT;
			break;
		case SALC:
		case SRGU:
			height = BOARD_HEIGHT;
			break;
		}
		
		return height;
	}
	
	private Image getBoardImage() {
		// 보드 타입, 등급(Severity)에 따라 이미지를 반환
		// ImageFactory.mpuImageIcon[boardVo.getSeverity()];
		Image retImage = null;
		
		/**
		 * ImageFactory.mpuImageIcon[boardVo.getSeverity()];
		 * 
		 */
		switch (boardVo.getBoardType()) {
		case MPU:
			retImage = ImageFactory.mpuImageIcon[boardVo.getSeverity()].getImage();
			break;
		case SALC:
			retImage = ImageFactory.salcImageIcon[boardVo.getSeverity()].getImage();
			break;
		case SRGU:
			retImage = ImageFactory.srguImageIcon[boardVo.getSeverity()].getImage();
			break;
		}

		return retImage;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		LOGGER.debug("paint_Board_Component");
		
		// ImageFactory에서 이미를 가져옴
		Image drawImage = getBoardImage();
		
		g.drawImage(drawImage, 0, 0, this);

	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
	}
}
