package com.barunsw.imj.day09.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;
import com.barunsw.imj.common.constants.Gender;

public class FileAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(FileAddressBookImpl.class);

	private static final String ADDRESS_BOOK_FILE = "data/day09/imj/address_book.dat";
			
	private AddressBookInterface addressBookInterface;
	private Map<String, Person> personMap = new HashMap<>();
	private Person p = new Person();
	
	@Override
	public List<Person> getList(Person p) {
		List<Person> personList = new ArrayList<>();
		
		try (BufferedReader reader = new BufferedReader(new FileReader(ADDRESS_BOOK_FILE))) {
			String readLine = null;
			
			while ( (readLine = reader.readLine()) != null ) {
				if ( readLine.trim().length() > 0 ) {
					String[] personData = readLine.split(",");
					if ( personData.length >= 6 ) {
						String id 			= personData[0];
						String name 		= personData[1];
						int age 			= Integer.parseInt(personData[2]);
						String genderString = personData[3];
						Gender gender		= Gender.W;
						
						if(genderString.equals("남자")) {
							gender = Gender.M;
						} 
						
						String phone 		= personData[4];
						String address 		= personData[5];
						
						p = new Person(id, name, age, gender, phone, address);
						
						personMap.put(id, p);
						personList.add(p);
					}
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		return personList;
	}


	@Override
	public int addPerson(Person p) throws Exception {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE, true))) {
			List personDate = new ArrayList();
			
			if ( !personMap.isEmpty() ) {
				for ( String nKey : personMap.keySet() ) {
					if( p.getId().equals(nKey) ) {
						throw new Exception("중복된 아이디 존재");
					} 
				}
			}
			
			personDate.add(p.getId());
			personDate.add(p.getName());
			personDate.add(p.getAge());
			personDate.add(p.getGender());
			personDate.add(p.getPhone());
			personDate.add(p.getAddress());

			personMap.put(p.getId(), p);

			String onePerson = StringUtils.join(personDate, ",");

			writer.write(onePerson);
			writer.newLine();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return 0;
	}

	@Override
	public int changePerson(Person p) throws Exception {
		List<Map<String, Person>> personList = new ArrayList<>();
		
		List personDate = new ArrayList();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE))) {
		
			LOGGER.debug(personMap);
			for ( String nKey : personMap.keySet() ) {
				LOGGER.debug(p + " , " + nKey);
				
				if ( p.getId().equals(nKey) ) {
					
					personMap.put(p.getId(), p);

					break;
				} 
			}
			
			for ( String nKey : personMap.keySet() ) {
				personDate.add(p.getId());
				personDate.add(p.getName());
				personDate.add(p.getAge());
				personDate.add(p.getGender());
				personDate.add(p.getPhone());
				personDate.add(p.getAddress());
				
				personMap.put(p.getId(), p);
				
				String onePerson = StringUtils.join(personDate, ",");
				
				writer.write(onePerson);
				writer.newLine();
				
			}
		}
		
		return 0;
	}

	@Override
	public int deletePerson(Person p) throws Exception {
		
		List<Map<String, Person>> personList = new ArrayList<>();
		
		List personDate = new ArrayList();
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE))) {

			LOGGER.debug(p.getId());
			LOGGER.debug(personMap.keySet());
			
			for ( String nKey : personMap.keySet() ) {
				
				if ( p.getId().equals(nKey) ) {
					personMap.remove(p.getId());
					break;
				}
			}
			for ( String nKey : personMap.keySet() ) {
				personDate.add(p.getId());
				personDate.add(p.getName());
				personDate.add(p.getAge());
				personDate.add(p.getGender());
				personDate.add(p.getPhone());
				personDate.add(p.getAddress());

				personMap.put(p.getId(), p);

				String onePerson = StringUtils.join(personDate, ",");

				writer.write(onePerson);
				writer.newLine();

			}
		}
		return 0;
	}

}

