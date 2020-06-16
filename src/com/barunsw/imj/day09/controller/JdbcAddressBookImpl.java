package com.barunsw.imj.day09.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class JdbcAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(JdbcAddressBookImpl.class);
	
	private final String DB_CLASS		= "com.mysql.jdbc.Driver";
	private final String DB_URL 		= "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER 		= "root";
	private final String DB_PASSWORD 	= "jung2564";
	
	public JdbcAddressBookImpl() {
		try {
			init();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	private void init() throws Exception {
		Class.forName(DB_CLASS);
	}
	
	private Connection getConnection() throws Exception {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	}
	
	@Override
	public List<Person> getList(Person p) {
		List<Person> personList = new ArrayList<>();
		
		try (Connection conn = getConnection(); 
				Statement stmt = conn.createStatement()) {
			
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM TB_PERSON");

			while ( resultSet.next() ) {
				String id 		= resultSet.getString(1);
				String name 	= resultSet.getString(2);
				int age 		= resultSet.getInt(3);
				String gender 	= resultSet.getString(4);
				String Phone 	= resultSet.getString(5);
				String address	= resultSet.getString(6);

			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personList;
	}

	@Override
	public int addPerson(Person p) throws Exception {
		int row = 0;
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO TB_PERSON VALUES (?, ?, ?, ?, ?, ?)")) {
				
			pstmt.setString(1, p.getId());
			pstmt.setString(2, p.getName());
			pstmt.setInt(3, p.getAge());
			pstmt.setString(4, p.getGender().toString());
			pstmt.setString(5, p.getPhone());
			pstmt.setString(6, p.getAddress());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return row;
	}

	@Override
	public int changePerson(Person p) throws Exception {
		int row = 0;
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE TB_PERSON SET NAME=(?), AGE=(?), GENDER=(?), PHONE=(?), ADDRESS=(?) WHERE ID=(?)")) {
				
			pstmt.setString(1, p.getName());
			pstmt.setInt(2, p.getAge());
			pstmt.setString(3, p.getGender().toString());
			pstmt.setString(4, p.getPhone());
			pstmt.setString(5, p.getAddress());
			pstmt.setString(6, p.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return row;
	}

	@Override
	public int deletePerson(Person p) throws Exception {
		int row = 0;
		
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM TB_PERSON WHERE ID=(?)")) {
				
			pstmt.setString(1, p.getId());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		return row;
	}
}

