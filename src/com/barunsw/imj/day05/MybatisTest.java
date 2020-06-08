package com.barunsw.imj.day05;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class MybatisTest {
	private static Logger LOGGER = LogManager.getLogger(MybatisTest.class);
	
	public static void main(String[] args) throws Exception {
		PersonDao personDao = new PersonDao();
		
		// INSERT
		Person p1 = new Person("11113", 11, "변경전");
		
		
		try {
			personDao.add(p1);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		// SELECT ALL
		List<Person> personList = personDao.getList(new Person());
		for (Person onePerson : personList) {
			LOGGER.debug(onePerson);
		}
		
		
		// SELECT ID
		String id = "12345";
		
		List<Person> personIdList = personDao.getList(new Person(id));
		
		for (Person onePerson : personIdList) {
			LOGGER.debug(onePerson);
		}
		
		// UPDATE
		Person p2 = new Person("11112", 222, "변경후");
		personDao.change(p2);
		
		
		// DELETE
		id = "111112";
		
		personDao.delete(new Person(id));

	}
}
