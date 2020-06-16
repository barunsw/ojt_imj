package com.barunsw.day11;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientSocketHandler extends Thread {
	private static Logger LOGGER = LogManager.getLogger(ClientSocketHandler.class);
	
	private static Map<ClientSocketHandler, String> clientRepo = new HashMap<>();
	
	private static final String MSG_TYPE_LOGIN 	= "LOGIN";
	private static final String MSG_TYPE_LOGOUT = "LOGOUT";
	private static final String MSG_TYPE_MSG 	= "MSG";
	
	private Socket socket;
	
	private BufferedWriter writer;
	
	public ClientSocketHandler(Socket socket) throws Exception {
		this.socket = socket;
		this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	
	@Override
	public void run() {
		// 메시지 수신
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug(String.format("read(%s):%s", socket.getRemoteSocketAddress(), readLine));
				
				// \n을 없앤 메시지 처리
				handleMessage(readLine.trim());
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void handleMessage(String message) {
		LOGGER.debug("handleMessage:[" + message + "]");
		
		if (message.toUpperCase().startsWith(MSG_TYPE_LOGIN)) {
			LOGGER.debug(MSG_TYPE_LOGIN);
			
			String userId = message.split(":")[1].trim();
			
			clientRepo.put(this, userId);
			
			for (ClientSocketHandler oneClient : clientRepo.keySet()) {
				oneClient.sendMessage(userId + "님이 들어왔습니다.");
			}
		}
		else if (message.toUpperCase().startsWith(MSG_TYPE_LOGOUT)) {
			LOGGER.debug(MSG_TYPE_LOGOUT);

			String userId = message.split(":")[1].trim();
			
			clientRepo.remove(this);
			
			for (ClientSocketHandler oneClient : clientRepo.keySet()) {
				oneClient.sendMessage(userId + "님이 나갔습니다.");
			}
		}
		else if (message.toUpperCase().startsWith(MSG_TYPE_MSG)) {
			LOGGER.debug(MSG_TYPE_MSG);

			String msg = message.split(":")[1].trim();
			
			for (ClientSocketHandler oneClient : clientRepo.keySet()) {
				oneClient.sendMessage(clientRepo.get(oneClient) + ":" + msg);
			}
		}
	}
	
	/**
	 * write를 통해 메시지를 상대편 socket으로 전송한다.
	 * @param msg
	 */
	public void sendMessage(String msg) {
		try {
			if (writer != null) {
				writer.write(msg + "\n");
				writer.flush();
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
