package com.barunsw.day05;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.common.Person;

public class PersonDao {
	private static Logger LOGGER = LogManager.getLogger(PersonDao.class);
	
	public List<Person> getList(Person p) {
		List<Person> personList = null;
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			personList = sqlSession.selectList("com.barunsw.day05.PersonDao.selectList", p);
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}
		
		return personList;
	}
	
	public int add(Person person) throws Exception {
		// ID로 이미 있는지 조회를 하여 확인
		// 존재하면 throw new Exception(String.format("이미 존재하는 ID(%s)", person.getId()))
		
		int result = 0;
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			result = sqlSession.insert("com.barunsw.day05.PersonDao.insert", person);
			
			sqlSession.commit();
		}
		catch (Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
		}

		return result;
	}
	
	public int change(Person person) throws Exception {
		// ID로 이미 있는지 조회를 하여 확인
		// 존재하지 않으면 throw new Exception(String.format("존재하지 않는 ID(%s)", person.getId()))
		
		return 0;
	}
	
	public int delete(Person person) throws Exception {
		// ID로 이미 있는지 조회를 하여 확인
		// 존재하지 않으면 throw new Exception(String.format("존재하지 않는 ID(%s)", person.getId()))
		return 0;
	}
}
