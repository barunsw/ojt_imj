package com.barunsw.common;

import java.io.Serializable;

import com.barunsw.common.constants.Gender;

public class Person implements Serializable {
	private String id;
	private int age;
	private String name;
	private Gender gender;
	
	public Person() {
		
	}
	
	public Person(String id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Person) {
			Person anotherPerson = (Person)o;
			
			if (this.age == anotherPerson.getAge() &&
					this.name.equals(anotherPerson.getName())) {
				return true;
			}
		}
		
		return false;
	}

	/*
	@Override
	public int hashCode() {
		//return age + name.hashCode();
		return id.hashCode();
	}
	*/
	
	@Override
	public String toString() {
		return String.format("[%d]%s", age, name);
	}
}
