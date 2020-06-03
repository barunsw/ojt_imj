package com.barunsw.imj.day03;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class AddressBookApp2 {
	private static Logger LOGGER = LogManager.getLogger(AddressBookApp2.class);
	
	private static final String ADDRESS_BOOK_FILE = "data/day03/imj/address_book2.dat";
	
	private Set<Person> personSet = new HashSet<>();
	
	public AddressBookApp2() {
		// TODO Auto-generated constructor stub
	}
	
	public void add(Person person) {
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
//			String onePerson = String.format("%s,%s,%s", person.getId(), person.getAge(), person.getName());
//			
//			writer.write(onePerson);
//			writer.newLine();
//		}
//		catch (Exception ex) {
//			LOGGER.error(ex.getMessage(), ex);
//		}
//		
//		// StringUtils.join: format 형식 안쓰고 값 입력
//		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
//			//String onePerson = String.format("%s, %s, %s", person.getId(), person.getAge(), person.getName());
//			List personDate = new ArrayList();
//			
//			personDate.add(person.getId());
//			personDate.add(person.getAge());
//			personDate.add(person.getName());
//			
//			String onePerson = StringUtils.join(personDate, ",");
//			
//			writer.write(onePerson);
//			writer.newLine();
//		}
//		catch (Exception ex) {
//			LOGGER.error(ex.getMessage(), ex);
//		}
		
		personSet.add(person);
		
		try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(ADDRESS_BOOK_FILE))) {
			Iterator<Person> pIter = personSet.iterator();
			while (pIter.hasNext()) {
				outputStream.writeObject(pIter.next());
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	public List<Person> getList() {
		List<Person> personList = new ArrayList<>();
		
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ADDRESS_BOOK_FILE))) {
			Object o = null;
			while ( (o = inputStream.readObject()) != null ) {
				if ( o instanceof Person ) {
					personList.add((Person)o);
				}
			}
		}
		catch (EOFException eof) {
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return personList;
	}

	
	
	public static void main(String[] args) {
		AddressBookApp2 app = new AddressBookApp2();
		
		Person p1 = new Person("12345", 26, "홍길동");
		Person p2 = new Person("12346", 36, "유관순");
		
		app.add(p1);
		app.add(p2);
		
		List<Person> personList = app.getList();
		for (Person onePerson : personList) {
			LOGGER.debug(onePerson);
		}
	}
}
