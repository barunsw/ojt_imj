package com.barunsw.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileTest {
	private static Logger LOGGER = LogManager.getLogger(FileTest.class);
	
	public static void main(String[] args) {
		File file = new File("data/day03");
		LOGGER.debug("file.exist:" + file.exists());
		
		// 디렉토리가 존재하는지 보고 없으면 생성
		if (!file.exists()) {
			try {
				Files.createDirectory(file.toPath());
			}
			catch (Exception ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		}
		
		// 디렉토리인지 파일인지 여부 확인
		LOGGER.debug("file.isDirectory():" + file.isDirectory());
		LOGGER.debug("file.isFile():" + file.isFile());
		
		File[] fileList = file.listFiles();
		for (File oneFile : fileList) {
			LOGGER.debug("oneFile:" + oneFile);
			LOGGER.debug("oneFile.isDirectory():" + oneFile.isDirectory());
			LOGGER.debug("oneFile.isFile():" + oneFile.isFile());
		}
		
		// inputStream/outputStream(byte 단위)을 이용한 읽고, 쓰기
		InputStream inputStream = null;
		byte[] buf = new byte[1024];
		try {
			inputStream = new FileInputStream("data/day03/test.dat");
			int readNum = inputStream.read(buf);
			
			LOGGER.debug("readNum:" + readNum);
			LOGGER.debug("buf:" + new String(buf));
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
			}
		}

		// 위와 동일. close 코드 줄임
		byte[] buf2 = new byte[10];
		try (InputStream inputStream2 = new FileInputStream("data/day03/korean.dat");) {
			int readNum = inputStream2.read(buf2);
			
			LOGGER.debug("readNum:" + readNum);
			LOGGER.debug("buf2:" + new String(buf2));
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// reader/writer(char단위)를 이용한  일고, 쓰기
		char[] cbuf = new char[4];
		try (Reader reader = new FileReader("data/day03/korean.dat")) {
			while (true) {
				int readNum = reader.read(cbuf);
				if (readNum < 0) {
					break;
				}
				
				LOGGER.debug("readNum:" + readNum);
				LOGGER.debug("cbuf:" + new String(cbuf));
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		try (BufferedReader reader = new BufferedReader(new FileReader("data/day03/korean.dat"))) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug("readLine:" + readLine);
				String[] words = readLine.split("\\s+");
				//String[] words = readLine.split(" ");
				for (String oneWord : words) {
					LOGGER.debug("oneWord:" + oneWord);
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		

		try (OutputStream outputStream = new FileOutputStream("data/day03/test02.dat", true)) {
			//String aaa = "abcdefg";
			String aaa = "동해물과 백두산이";
			
			outputStream.write(aaa.getBytes());
			//outputStream.write(aaa.getBytes(), 0, 10);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/day03/test03.dat"))) {
			String aaa = "동해물과 백두산이";
			writer.write(aaa);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}
