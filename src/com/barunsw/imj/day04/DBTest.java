package com.barunsw.imj.day04;

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

public class DBTest {
	private static Logger LOGGER = LogManager.getLogger(DBTest.class);
	
	private final String DB_CLASS		= "com.mysql.jdbc.Driver";
	private final String DB_URL 		= "jdbc:mysql://localhost:3306/OJT?useUnicode=true&characterEncoding=UTF-8";
	private final String DB_USER 		= "root";
	private final String DB_PASSWORD 	= "jung2564";
	
	public DBTest() {
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
	
	public List<Person> getPersonList() {
		List<Person> personList = new ArrayList<>();
		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {
			ResultSet resultSet = stmt.executeQuery("SELECT * FROM DB_PERSON");
			while (resultSet.next()) {
				String id	= resultSet.getString(1);
				int age		= resultSet.getInt(2);
				String name = resultSet.getString(3);
				
				Person p = new Person(id, age, name);
				
				personList.add(p);
				
				LOGGER.debug(p);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personList;
	}
	
	public void addPerson(Person p) {
/*		
		try (Connection conn = getConnection();
				Statement stmt = conn.createStatement()) {
			// insert 방법1
//			int rowNum = stmt.executeUpdate("INSERT INTO DB_PERSON VALUES ("+ 
//					"'" + p.getId() + "'," + p.getAge() + ",'" + p.getName() + "'");
			
			// insert 방법2
			int rowNum = stmt.executeUpdate(String.format("INSERT INTO DB_PERSON VALUES ('%s', %s, '%s')", 
					p.getId(), p.getAge(), p.getName()));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}

*/		
		// insert 방법3
		try (Connection conn = getConnection();
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DB_PERSON VALUES (?, ?, ?)")) {
				
			pstmt.setString(1, p.getId());
			pstmt.setInt(2, p.getAge());
			pstmt.setString(3, p.getName());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		
	}
	
	public static void main(String[] args) throws Exception {
		String a = new String("aaaa");
/*		
		// 클래스 객체 동적 생성
		Class c = Class.forName("java.lang.String");
		
		Class[] paramTypes = new Class[1];
		paramTypes[0] = String.class;
				
		Constructor cons = c.getConstructor(paramTypes);
		
		String a1 = (String)cons.newInstance("aaaa");
		
		LOGGER.debug("a : " + a);
		LOGGER.debug("a1: " + a1);
		
		Class.forName("com.barunsw.imj.common.Person");
*/		
		
		DBTest test = new DBTest();
		
		Person p1 = new Person("11111", 30, "홍길동");
		test.addPerson(p1);
		
		Person p2 = new Person("22222", 20, "유관순");
		test.addPerson(p2);
		
		List<Person> personList = test.getPersonList();
		for ( Person onePerson : personList ) {
			LOGGER.debug("onePerson: " + onePerson);
		}
	}
}
