package com.barunsw.day11;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientSocketTest {
	private static Logger LOGGER = LogManager.getLogger(ClientSocketTest.class);
	
	public static void main(String[] args) {
		// localhost = 127.0.0.1
		try (Socket s = new Socket("localhost", SocketConstants.TCP_PORT);) {
			Thread sendThread = new Thread(new Runnable() {
				@Override
				public void run() {
					// 보내는 행위
					try (OutputStream outputStream = s.getOutputStream();) {
						outputStream.write("LOGIN:철수\n".getBytes());
						outputStream.flush();
						
						for (int i = 0; i < 10; i++) {
							String message = "MSG:안녕하세요\n";
							outputStream.write(message.getBytes());
							outputStream.flush();
							LOGGER.debug("send!");
							
							try {
								Thread.sleep(1000);
							}
							catch (Exception ex) {
								LOGGER.error(ex.getMessage(), ex);
							}
						}
						
						outputStream.write("LOGOUT:철수\n".getBytes());
						outputStream.flush();
					}
					catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			});
			sendThread.start();
			
			Thread recvThread = new Thread(new Runnable() {
				@Override
				public void run() {
					// 받는 행위
					try (BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));) {
						String readLine = null;
						while ((readLine = reader.readLine()) != null) {
							LOGGER.debug(String.format("recv:%s", readLine));
						}
					}
					catch (Exception ex) {
						LOGGER.error(ex.getMessage(), ex);
					}
				}
			});
			recvThread.start();
			
			while (true) {
				try {
					Thread.sleep(1000);
				}
				catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}		
	}
}
