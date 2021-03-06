package com.barunsw.imj.day05;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;

public class PersonDao extends Exception {
	private static Logger LOGGER = LogManager.getLogger(PersonDao.class);
	
	// person 리스트 반환
	public List<Person> getList(Person person) {
		List<Person> personList = null;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			personList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personList;
	}
	
	// person id검색 반환
	public List<Person> getIdList(Person person) throws Exception {
			
		List<Person> personIdList = null;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			personIdList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person.getId());
			
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person);
			if ( personList.isEmpty() ) {
				throw new Exception("Person 정보 존재하지않음");
			}
 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personIdList;
	}
	
	public int add(Person person) throws Exception {
		int result = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person);
			if ( personList != null && !personList.isEmpty() ) {
				throw new Exception("중복된 Person 정보 존재");
			}
			
			result = sqlSession.insert("com.barunsw.imj.day05.PersonDao.insert", person);
			sqlSession.commit();
			 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			
		}
		
		return result;
	}
	
	public int change(Person person) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.update("com.barunsw.imj.day05.PersonDao.update", person);
			
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person);
			if ( personList.isEmpty() ) {
				throw new Exception("Person 정보 존재하지 않음");
			}
			LOGGER.debug("id가 {}인 사람이 변경되었습니다.", person.getId());
			sqlSession.commit();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	
		return row;
	}
	
	public int delete(Person person) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.delete("com.barunsw.imj.day05.PersonDao.delete", person.getId());
			
			
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectList", person);
			if (personList == null) {
				throw new Exception("Person 정보 존재하지 않음");
			}
			
			LOGGER.debug("id가 {}인 사람이 삭제되었습니다.", person.getId());
			sqlSession.commit();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return row;
	}
	
	
}
