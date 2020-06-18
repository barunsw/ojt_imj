package com.barunsw.imj.day12.chat.client;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.day12.chat.server.ChatInterface;

public class ChatPanel extends JPanel {
	private static Logger LOGGER = LogManager.getLogger(ChatPanel.class);

	private JScrollPane jScrollPane 				= new JScrollPane();
	private JTextArea jTextArea_chat				= new JTextArea();
	
	private GridBagLayout gridBagLayout 			= new GridBagLayout();

	private JPanel jPanel							= new JPanel();
	private JToggleButton jToggleButton_Connect 	= new JToggleButton("접속");
	private JButton jButton_Send 					= new JButton("전송");
	
	private JTextField jTextField_Id 				= new JTextField();
	private JTextField jTextField_Message 			= new JTextField();
	
	private String userId;
	
	private ChatInterface chatIf;
	
	public ChatPanel() {
		try {
			initRmi();
			initComponent();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}

	private void initRmi() throws Exception {
		Registry registry = LocateRegistry.getRegistry();
		LOGGER.debug(LocateRegistry.getRegistry());
		
		Remote remote = registry.lookup("chat");
		
		if ( remote instanceof ChatInterface ) {
			chatIf = (ChatInterface) remote;
		}
	}

	private void initComponent() {
		this.setLayout(gridBagLayout);
		
		jTextArea_chat.setPreferredSize(new Dimension(400, 350));
		jTextField_Id.setPreferredSize(new Dimension(80, 24));
		jTextField_Message.setPreferredSize(new Dimension(250, 24));

		jScrollPane.getViewport().add(jTextArea_chat);

		jTextArea_chat.setFocusable(false);

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
		
		jToggleButton_Connect.addActionListener(new ChatPanel_jToggleButton_Connect_ActionListener(this));
		jButton_Send.addActionListener(new ChatPanel_jButton_Send_ActionListener(this));

	}
	
	public void jToggleButton_Connect_actionPerformed(ActionEvent e) throws Exception {
		userId = jTextField_Id.getText();

		ChatRecvInterface recvIf = new ChatRecvImpl(this);
		if ( jToggleButton_Connect.getText().equals("접속") ) {
			chatIf.login(userId, recvIf);
			
			jTextField_Id.setEditable(false);
			jToggleButton_Connect.setText("종료");
		}
		else if ( jToggleButton_Connect.getText().equals("종료") ) {
			chatIf.logout(userId);
			System.exit(0);
		}
	}

	public void jButton_Send_actionPerformed(ActionEvent e) throws Exception {
		String message = jTextField_Message.getText();
		chatIf.message(userId + ": " + message);
		jTextField_Message.setText(null);
	}

	public void recvMessage(String str) {
		
		jTextArea_chat.append(str + "\n");
		
	}
}

class ChatPanel_jToggleButton_Connect_ActionListener implements ActionListener {
	private static Logger LOGGER = LogManager.getLogger(ChatPanel_jToggleButton_Connect_ActionListener.class);
	private ChatPanel adaptee;
	
	public ChatPanel_jToggleButton_Connect_ActionListener(ChatPanel adaptee) {
		this.adaptee = adaptee;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			adaptee.jToggleButton_Connect_actionPerformed(e) ;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}

class ChatPanel_jButton_Send_ActionListener implements ActionListener {
	private static Logger LOGGER = LogManager.getLogger(ChatPanel_jButton_Send_ActionListener.class);
	private ChatPanel adaptee;
	
	public ChatPanel_jButton_Send_ActionListener(ChatPanel adaptee) {
		this.adaptee = adaptee;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			adaptee.jButton_Send_actionPerformed(e);
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
