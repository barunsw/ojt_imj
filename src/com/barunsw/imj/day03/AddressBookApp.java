package com.barunsw.imj.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class AddressBookApp {
	private static Logger LOGGER = LogManager.getLogger(AddressBookApp.class);
	
	private static final String ADDRESS_BOOK_FILE = "data/day03/imj/address_book.dat";
	
	public AddressBookApp() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(Person person) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
			String onePerson = String.format("%s,%s,%s", person.getId(), person.getAge(), person.getName());
			
			// write()안에 person(객체)를 못쓰므로 String으로 만들어서 사용
			writer.write(onePerson);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		// StringUtils.join: format 형식 안쓰고 값 입력 (newLine 생기는 것 방지)
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
			//String onePerson = String.format("%s, %s, %s", person.getId(), person.getAge(), person.getName());
			List personDate = new ArrayList();
			
			personDate.add(person.getId());
			personDate.add(person.getAge());
			personDate.add(person.getName());
			
			String onePerson = StringUtils.join(personDate, ",");
			
			writer.write(onePerson);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	// DAO와 비슷
	public List<Person> getList() {
		// List<Person> personList = new ArrayList<>();
		// ArrayList<> 뒤에 비워두면 앞에 있는 <E>와 동일하게 적용
		List<Person> personList = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(ADDRESS_BOOK_FILE))) {
			String readLine = null;
			while ( (readLine = reader.readLine()) != null ) {
				// 마지막에 newLine이 하나 남아있으므로 trim으로 공백 제거 **
				if ( readLine.trim().length() > 0 ) {
					String[] personData = readLine.split(",");
					if ( personData.length >= 3 ) {
						String id = personData[0];
						int age = Integer.parseInt(personData[1]);
						String name = personData[2];
						
						Person p = new Person(id, age, name);
						personList.add(p);
					}
				}
			}
			return personList;
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return null;
	}

	
	
	public static void main(String[] args) {
		AddressBookApp app = new AddressBookApp();
		
		Person p1 = new Person("12345", 26, "홍길동");
		Person p2 = new Person("12346", 36, "유관순");
		
		app.add(p1);
		app.add(p2);
		
		List<Person> personList = app.getList();
		for (Person onePerson : personList) {
			// toString() 호출
			LOGGER.debug(onePerson);
		}
	}
}
