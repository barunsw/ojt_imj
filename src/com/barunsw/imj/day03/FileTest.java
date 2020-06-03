package com.barunsw.imj.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileTest {
	private static Logger LOGGER = LogManager.getLogger(FileTest.class);
	
	public static void main(String[] args) {
		// new File("파일 상대경로 작성")
		File file  = new File("data/day03/imj");
		
		// 파일 존재 확인
		LOGGER.debug("file.exist: " +  file.exists());
		
		// 디렉토리가 존재하지 않을 경우 파일 생성
		if (!file.exists()) {
			try {
				Files.createDirectory(file.toPath());
			}
			catch (IOException ex) {
				LOGGER.error(ex.getMessage(), ex);
			}
		}
		
		// 디렉토리인지 파일인지 여부 확인
		LOGGER.debug("file.isDirectory():" + file.isDirectory());
		LOGGER.debug("file.isFile():" + file.isFile());
		
		File[] fileList = file.listFiles();
		for ( File oneFile : fileList ) {
			LOGGER.debug("oneFile: " + oneFile);
			LOGGER.debug("oneFile.isDirectory(): " + oneFile.isDirectory());
			LOGGER.debug("oneFile.isFile(): " + oneFile.isFile());
		}
		
		LOGGER.debug(fileList);
		
		// InputStream/OutputStream (byte 단위)
		InputStream inputStream = null;
		
		byte[] buf = new byte[1024];
		try {
			inputStream = new FileInputStream("data/day03/imj/test.dat");
			int readNum = inputStream.read(buf);
			
			LOGGER.debug("readNum:" + readNum);
			LOGGER.debug("buf:" + new String(buf));
			// byte 값이 반환되므로 String으로 변환해서 출력
			LOGGER.debug("buf:" + new String(buf));
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		finally {
			if ( inputStream != null ) {
				try {
					inputStream.close();
				}
				catch (Exception ex) {
					LOGGER.error(ex.getMessage(), ex);
				}
			}
		}
		
		// InputStream (try문 안에 inputStream 생성하여 close 생략)
		byte[] buf2 = new byte[10];
		try (InputStream inputStream2 = new FileInputStream("data/day03/imj/korean.dat"); ) {
			int readNum = inputStream2.read(buf2);
			
			LOGGER.debug("readNum:" + readNum);
			LOGGER.debug("buf2:" + buf2);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// Reader/Writer (char 단위)
		char[] cbuf = new char[4];
		try (Reader reader = new FileReader("data/day03/imj/korean.dat")) {
			while (true) {
				int readNum = reader.read(cbuf);
				if( readNum < 0 ) {
					break;
				}
				
				LOGGER.debug("readNum:" + readNum);
				LOGGER.debug("cbuf:" + new String(cbuf));
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// BufferedReader
		try (BufferedReader reader = new BufferedReader(new FileReader("data/day03/imj/korean.dat"))) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				LOGGER.debug("readLine: " + readLine);
				String[] words = readLine.split("\\s+");
				
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// OutputStream - 파일이 없을 경우 파일 생성 후 write
		try (OutputStream outputStream = new FileOutputStream("data/day03/imj/test02.dat", true)) {
			//String aaa = "abcdefg";
			String aaa = "동해물과 백두산이";
			
			// str.getBytes(): String을 byte로 변환
			outputStream.write(aaa.getBytes());
			//outputStream.write(aaa.getBytes(), 0, 10);
			
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// BufferedWriter
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("data/day03/imj/test03.dat", true))) {
			String aaa = "동해물과 백두산이";
			writer.write(aaa);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
}

















