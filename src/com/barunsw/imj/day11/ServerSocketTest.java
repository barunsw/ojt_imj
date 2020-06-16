package com.barunsw.imj.day11;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerSocketTest {
	private static Logger LOGGER = LogManager.getLogger(ServerSocketTest.class);
	
	public static void main(String[] args) {
		LOGGER.debug("+++ server start");
		try (ServerSocket s = new ServerSocket(SocketConstants.TCP_PORT);) {
			LOGGER.debug("+++ server listen");
			while (true) {
				Socket clientSocket = s.accept();
				LOGGER.debug(String.format("+++ server accept(%s:%s)", clientSocket.getRemoteSocketAddress(), clientSocket.getPort()));
				
				ClientSocketHandler clientsocketHandler = new ClientSocketHandler(clientSocket);
				clientsocketHandler.start();
			}
		}
		catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
}
