package com.barunsw.imj.day09.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;
import com.barunsw.imj.common.constants.Gender;

public class FileAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(FileAddressBookImpl.class);

	private static final String ADDRESS_BOOK_FILE = "data/day09/imj/address_book.dat";
			
	private Map<String, Person> personMap = new HashMap<>();
	
	public FileAddressBookImpl() {
		try {
			readFile();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	/**
	 * 파일의 내용을 읽어서 personMap에 담는다.
	 */
	private void readFile() {
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
						
						if (genderString.equals("남자")) {
							gender = Gender.M;
						} 
						
						String phone 		= personData[4];
						String address 		= personData[5];
						
						Person p = new Person(id, name, age, gender, phone, address);
						
						personMap.put(id, p);
					}
				}
			}
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
	}
	
	/**
	 * personMap에 전체 내용을 파일에 Write한다.
	 */
	private void writeFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(ADDRESS_BOOK_FILE))) {
			Iterator<String> keyIter = personMap.keySet().iterator();
			
			while (keyIter.hasNext()) {
				String oneId = keyIter.next();
				
				Person p = personMap.get(oneId);
				
				List personList = new ArrayList();
				
				personList.add(p.getId());
				personList.add(p.getName());
				personList.add(p.getAge());
				personList.add(p.getGender());
				personList.add(p.getPhone());
				personList.add(p.getAddress());
	
				String onePerson = StringUtils.join(personList, ",");
	
				writer.write(onePerson);
				writer.newLine();
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
		
		// 파일에 write한다.
		writeFile();

		return 0;
	}

	@Override
	public int changePerson(Person p) throws Exception {
		// personMap의 내용을 변경한다.
		for ( String nKey : personMap.keySet() ) {
			LOGGER.debug(p + " , " + nKey);
			
			if ( p.getId().equals(nKey) ) {
				personMap.put(p.getId(), p);
				break;
			} 
		}
		
		// 파일에 write한다.
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

		// 파일에 write한다.
		writeFile();

		return 0;
	}

}

