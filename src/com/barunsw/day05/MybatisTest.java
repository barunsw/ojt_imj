package com.barunsw.day05;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;

public class MybatisTest {
	private static Logger LOGGER = LogManager.getLogger(MybatisTest.class);
	
	public static void main(String[] args) {
		Person p1 = new Person("12347", 26, "안중근");
		
		PersonDao personDao = new PersonDao();
		
		try {
			personDao.add(p1);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		List<Person> personList = personDao.getList(new Person());
		for (Person onePerson : personList) {
			LOGGER.debug(onePerson);
		}
	}
}
