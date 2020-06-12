package com.barunsw.imj.common;

import java.io.Serializable;

import com.barunsw.imj.common.constants.Gender;

public class Person implements Serializable {
	private String id;
	private String name;
	private int age;
	private Gender gender;
	private String phone;
	private String address;
	
	
	public Person() {
		
	}
	
	public Person(String id) {
		this.id = id;
	}
	
	public Person(String id, String name, int age, Gender gender, String phone, String address) {
		this.id			= id;
		this.name 		= name;
		this.age		= age;
		this.gender 	= gender;
		this.phone 		= phone;
		this.address 	= address;
	}
	
	// Getter Setter(Ctrl + Shift + s + r)
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	@Override
//	public boolean equals(Object o) {
//		if (o instanceof Person) {
//			Person anotherPerson = (Person)o;
//			
//			if (this.age == anotherPerson.getAge() &&
//					this.name.equals(anotherPerson.getName())) {
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/*
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return id.hashCode();
	}
	*/
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s,%s,%d,%s,%s,%s", id, name, age, gender.toString(), phone, address);
	}


	
}
