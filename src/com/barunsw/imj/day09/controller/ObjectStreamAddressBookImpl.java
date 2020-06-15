package com.barunsw.imj.day09.controller;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class ObjectStreamAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(ObjectStreamAddressBookImpl.class);
	
	private static final String ADDRESS_BOOK_FILE = "data/day09/imj/address_book.dat";
	
	private Set<Person> personSet = new HashSet<>();
	private Map<String, Person> personMap = new HashMap<>();
	
	public ObjectStreamAddressBookImpl() {
		try {
			readFile();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void readFile() {
		try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(ADDRESS_BOOK_FILE))) {
			Object o = null;
			
			while ((o = inputStream.readObject()) != null) {
				if (o instanceof Person) {
					personMap.put(((Person) o).getId(), (Person) o);
				}
			}
		}
		catch (EOFException eof) {
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	private void writeFile() {
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
	
	@Override
	public List<Person> getList(Person p) {
		List<Person> personList = new ArrayList<>(personMap.values());
		
		return personList;
	}

	@Override
	public int addPerson(Person p) throws Exception {
		// personMap 중복 체크 후, 담는다.
		if ( !personMap.isEmpty() ) {
			for ( String nKey : personMap.keySet() ) {
				if ( p.getId().equals(nKey) ) {
					throw new Exception("중복된 아이디 존재");
				}
			}
		}

		personMap.put(p.getId(), p);
		
		writeFile();
		
		return 0;
	}

	@Override
	public int changePerson(Person p) throws Exception {
		for ( String nKey : personMap.keySet() ) {
			LOGGER.debug(p + " , " + nKey);
			
			if ( p.getId().equals(nKey) ) {
				personMap.put(p.getId(), p);
				break;
			} 
		}
		
		writeFile();
		
		return 0;
	}

	@Override
	public int deletePerson(Person p) throws Exception {
		// personMap의 내용을 삭제한다.
		for ( String nKey : personMap.keySet() ) {
			if ( p.getId().equals(nKey) ) {
				personMap.remove(p.getId());
				break;
			}
		}

		writeFile();

		return 0;
	}

}
