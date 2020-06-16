package com.barunsw.day11;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerSocketTest {
	private static Logger LOGGER = LogManager.getLogger(ServerSocketTest.class);
	
	public static void main(String[] args) {
		LOGGER.debug("+++ server start");
		try (ServerSocket s = new ServerSocket(SocketConstants.TCP_PORT);) {
			while (true) {
				LOGGER.debug("+++ server listen");
				Socket clientSocket = s.accept();
				LOGGER.debug(String.format("+++ server accept(%s:%s)", clientSocket.getRemoteSocketAddress(), clientSocket.getPort()));

				// stream을 통한 통신. 한글 송수신에 문제가 생긴다.
//				byte[] readBuf = new byte[10]; 
//				try (InputStream inputStream = clientSocket.getInputStream();
//						) {
//					int readNum = inputStream.read(readBuf);
//					String readStr = new String(readBuf, 0, readNum);
//					LOGGER.debug("read:" + readStr);
//				}
				
				// inputStreamReader에 의한 한글 송수신
//				try (Reader reader = new InputStreamReader(clientSocket.getInputStream())) {
//					char[] readBuf = new char[10];
//					int readNum = reader.read(readBuf);
//					String readStr = new String(readBuf, 0, readNum);
//					LOGGER.debug("read:" + readStr);
//				}
				
				// BufferedReader를 이용한 송수신
//				try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
//					String readLine = reader.readLine();
//					LOGGER.debug("read:" + readLine);
//				}
				
				// 클라이언트 처리 스레드 생성 후 start
				ClientSocketHandler clientSocketHandler = new ClientSocketHandler(clientSocket);
				clientSocketHandler.start();
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
