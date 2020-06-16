package com.barunsw.imj.day09.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.barunsw.imj.common.Person;
import com.barunsw.imj.day05.PersonDao;
import com.barunsw.imj.day05.SqlSessionManager;

public class MybatisAddressBookImpl implements AddressBookInterface {
	private static Logger LOGGER = LogManager.getLogger(PersonDao.class);

	@Override
	public List<Person> getList(Person p) {
		List<Person> personList = null;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			personList = sqlSession.selectList("com.barunsw.imj.day09.controller.AddressBookInterface.selectList", p);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return personList;
	}

	@Override
	public int addPerson(Person p) throws Exception {
		int result = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day09.controller.AddressBookInterface.selectList", p);
			if ( personList != null && !personList.isEmpty() ) {
				throw new Exception("중복된 Person 정보 존재");
			}
			
			result = sqlSession.insert("com.barunsw.imj.day09.controller.AddressBookInterface.insert", p);
			sqlSession.commit();
			 
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			
		}
		
		return result;
	}

	@Override
	public int changePerson(Person p) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.update("com.barunsw.imj.day09.controller.AddressBookInterface.update", p);
			
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day09.controller.AddressBookInterface.selectList", p);
			if ( personList.isEmpty() ) {
				throw new Exception("Person 정보 존재하지 않음");
			}
			LOGGER.debug("id가 {}인 사람이 변경되었습니다.", p.getId());
			sqlSession.commit();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	
		return row;

	}

	@Override
	public int deletePerson(Person p) throws Exception {
		int row = 0;
		
		try (SqlSession sqlSession = SqlSessionManager.getSqlSessionFactory().openSession()) {
			row = sqlSession.delete("com.barunsw.imj.day09.controller.AddressBookInterface.delete", p.getId());
			
			
			// 동일한 ID 존재하는지 체크
			List<Person> personList = sqlSession.selectList("com.barunsw.imj.day09.controller.AddressBookInterface.selectList", p);
			
			if ( personList == null ) {
				throw new Exception("Person 정보 존재하지 않음");
			}
			
			LOGGER.debug("id가 {}인 사람이 삭제되었습니다.", p.getId());
			sqlSession.commit();
			
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
		return row;
	}

}
