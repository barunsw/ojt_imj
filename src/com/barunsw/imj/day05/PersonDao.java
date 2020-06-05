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
	public List<Person> getIdList(String id) throws Exception {
			
		List<Person> personIdList = null;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			personIdList = sqlSession.selectList("com.barunsw.imj.day05.PersonDao.selectIdList", id);
			
			// id 유효성 검사
			if( personIdList.isEmpty() ) {
				throw new Exception(String.format("존재하지 않는 ID(%s). 0줄이 검색됨", id));
			} 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personIdList;
	}
	
	public int add(Person person) throws Exception {
		int result = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			result = sqlSession.insert("com.barunsw.imj.day05.PersonDao.insert", person);
			sqlSession.commit();
			 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			
			if( result == 0 ) {
				throw new Exception("동일한 id 존재");
			}
		}
		
		return result;
	}
	
	public int change(Person person) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.delete("com.barunsw.imj.day05.PersonDao.update", person);
			
			// id 유효성 검사
			if( row == 0 ) {
				throw new Exception(String.format("존재하지 않는 ID(%s). 0줄이 변경됨", person.getId()));
			} else {
				LOGGER.debug("id가 {}인 사람이 변경되었습니다.", person.getId());
				sqlSession.commit();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	
		return row;
	}
	
	public int delete(String id) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.delete("com.barunsw.imj.day05.PersonDao.delete", id);
			
			LOGGER.debug("id가 {}인 사람이 삭제되었습니다.", id);
			sqlSession.commit();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			// id 유효성 검사
			if( row == 0 ) {
				throw new Exception(String.format("존재하지 않는 ID(%s). 0줄이 삭제됨", id));
			}
		}
		
		return row;
	}
	
	
}
