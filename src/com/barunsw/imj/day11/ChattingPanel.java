package com.barunsw.imj.day11;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChattingPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(ChattingPanel.class);

	private JScrollPane jScrollPane 		= new JScrollPane();
	private JTextArea jTextArea_chat		= new JTextArea();
	
	private GridBagLayout gridBagLayout 	= new GridBagLayout();

	private JPanel jPanel					= new JPanel();
	private JToggleButton jToggleButton_Connect 	= new JToggleButton("접속");
	private JButton jButton_Send 			= new JButton("전송");
	
	private JTextField jTextField_Id 		= new JTextField();
	private JTextField jTextField_Message 	= new JTextField();
	
	private static Map<ChattingPanel, String> clientRepo = new HashMap<>();
	
	private String userId;
	private String message;
	
	private PrintWriter output;
	
	private Socket s;
	private BufferedWriter writer;
	
	private ChattingPanel_SocketHandler socketHandler; 
	
	public ChattingPanel() {
		try {
			initSocket();
			initComponent();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void initSocket() throws Exception {
		this.s = new Socket("localhost", SocketConstants.TCP_PORT); 
		
		writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
		
		socketHandler = new ChattingPanel_SocketHandler(this, s);
		socketHandler.start();
	}
	
	public ChattingPanel(Socket s) throws Exception {
		this.s = new Socket("localhost", SocketConstants.TCP_PORT);
		initComponent();
		this.writer = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
	}

	private void initComponent() {
		try {
			LOGGER.debug("initComponent");
			this.setLayout(gridBagLayout);
			
			jTextArea_chat.setPreferredSize(new Dimension(400, 350));
			jTextField_Id.setPreferredSize(new Dimension(80, 24));
			jTextField_Message.setPreferredSize(new Dimension(250, 24));
			
			jScrollPane.getViewport().add(jTextArea_chat);
			
			jTextArea_chat.setFocusable(false);
//			jTextArea_chat.setEditable(false);
			
			this.add(jScrollPane, new GridBagConstraints(0, 0, 1, 1,
					1.0, 1.0,
					GridBagConstraints.NORTH, GridBagConstraints.BOTH,
					new Insets(5, 5, 5, 5),
					0, 0));
			
			this.add(jPanel, new GridBagConstraints(0, 1, 1, 1,
					1.0, 0.0,
					GridBagConstraints.SOUTH, GridBagConstraints.BOTH,
					new Insets(0, 5, 5, 5),
					0, 0));
			
			jPanel.add(jTextField_Id, new GridBagConstraints(0, 0, 1, 1,
					0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 5, 5, 5),
					0, 0));
			
			jPanel.add(jToggleButton_Connect, new GridBagConstraints(0, 1, 1, 1,
					0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 5, 5),
					0, 0));
			
			jPanel.add(jTextField_Message, new GridBagConstraints(0, 2, 1, 1,
					1.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
					new Insets(0, 0, 5, 5),
					0, 0));
			
			jPanel.add(jButton_Send, new GridBagConstraints(0, 3, 1, 1,
					0.0, 0.0,
					GridBagConstraints.CENTER, GridBagConstraints.NONE,
					new Insets(0, 0, 5, 5),
					0, 0));
			
			jButton_Send.addActionListener(new ChattingPanel_jButton_Send_ActionListener(this));
			jToggleButton_Connect.addActionListener(new ChattingPanel_jToggleButton_Connect_ActionListener(this));
			
			try {
				if(userId != null && message != null ) {
					jTextArea_chat.append(userId + ": " + message + "\n");
					jTextArea_chat.setCaretPosition(jTextArea_chat.getText().length());
				}
			} 
			catch (Exception e) {
				LOGGER.error(e.getMessage(), e);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

	}
	
	public void send(String str) {
//		try (OutputStream os = s.getOutputStream();) {
//			os.write(str.getBytes());
//			os.flush();
//		} 
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	
		try {
			writer.write(str + "\n");
			writer.flush();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public void jToggleButton_Connect_actionPerformed(ActionEvent e) {
		
		userId = jTextField_Id.getText();
		if ( jToggleButton_Connect.getText().equals("접속") ) {
			String userLogin = "LOGIN:" +userId;
			send(userLogin);
			jTextField_Id.setEditable(false);
			jToggleButton_Connect.setText("종료");
		}
		else if ( jToggleButton_Connect.getText().equals("종료") ) {
			String userLogout = "LOGOUT:" +userId;
			send(userLogout);
			System.exit(0);
		}
	
	}
	public void jButton_Send_actionPerformed(ActionEvent e) {
		String message = "MSG:" + jTextField_Message.getText();
		send(message);
//		recvMessage(message);
		jTextField_Message.setText(null);
		
	}
	public void recvMessage(String str) {
		
		jTextArea_chat.append(str + "\n");
		
	}

}

class ChattingPanel_jToggleButton_Connect_ActionListener implements ActionListener {
	private ChattingPanel adaptee;
	
	public ChattingPanel_jToggleButton_Connect_ActionListener(ChattingPanel adaptee) {
		this.adaptee = adaptee;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jToggleButton_Connect_actionPerformed(e);
	}
}

class ChattingPanel_jButton_Send_ActionListener implements ActionListener {
	private ChattingPanel adaptee;
	
	public ChattingPanel_jButton_Send_ActionListener(ChattingPanel adaptee) {
		this.adaptee = adaptee;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		adaptee.jButton_Send_actionPerformed(e);
	}
}

class ChattingPanel_SocketHandler extends Thread {
	private static Logger LOGGER = LogManager.getLogger(ChattingPanel_SocketHandler.class);
	private ChattingPanel adaptee;
	private Socket socket;
	
	public ChattingPanel_SocketHandler(ChattingPanel adaptee, Socket socket) {
		this.adaptee = adaptee;
		this.socket = socket;
	}
	
	@Override
	public void run() {
		// 1. BufferedReader를 생성한다.
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			// 2. BufferedReader를 통해 메시지를 기다린다.(reader에서 readLine할게 있으면)
			String readLine = null;
			// 3. 메시지를 받으면 adaptee.recvMessage를 호출한다.
			while ((readLine = reader.readLine()) != null) {
				adaptee.recvMessage(readLine);
				
			}
		} catch (IOException e) {
			LOGGER.debug(e.getMessage(), e);
		}
	}
}