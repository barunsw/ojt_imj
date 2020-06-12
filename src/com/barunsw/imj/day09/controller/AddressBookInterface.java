package com.barunsw.imj.day09.controller;

import java.util.List;

import com.barunsw.imj.common.Person;

public interface AddressBookInterface {
	// 조회(Retrieve)
	public List<Person> getList(Person p);
	// 생성(Create)
	public int addPerson(Person p) throws Exception ;
	// 변경(Update)
	public int changePerson(Person p) throws Exception;
	// 삭제(Delete)
	public int deletePerson(Person p) throws Exception;
}
