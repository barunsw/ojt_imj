package com.barunsw.day03;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;

public class AddressBookApp {
	private static Logger LOGGER = LogManager.getLogger(AddressBookApp.class);
	
	private static final String ADDRESS_BOOK_FILE = "data/day03/address_book.dat"; 
	
	public AddressBookApp() {
		
	}
	
	public void add(Person person) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
			String onePerson = String.format("%s,%s,%s", person.getId(), person.getAge(), person.getName());
			
			writer.write(onePerson);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
//		//String onePerson = String.format("%s,%s,%s", person.getId(), person.getAge(), person.getName());
//		List personData = new ArrayList();
//		personData.add(person.getId());
//		personData.add(person.getAge());
//		personData.add(person.getName());
//
//		String onePerson = StringUtils.join(personData, ",");
//		
//		writer.write(onePerson);
//		writer.newLine();
//	}
//	catch (Exception ex) {
//		LOGGER.error(ex.getMessage(), ex);
//	}
	
	}
	
	public List<Person> getList() {
		List<Person> personList = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(ADDRESS_BOOK_FILE))) {
			String readLine = null;
			while ((readLine = reader.readLine()) != null) {
				if (readLine.trim().length() > 0) {
					String[] personData = readLine.split(",");
					if (personData.length >= 3) {
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

//		List<Person> personList = app.getList();
//		for (Person onePerson : personList) {
//			LOGGER.debug(onePerson);
//		}
	}
}
