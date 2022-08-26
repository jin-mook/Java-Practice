package com.kt.collection;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionUser {

	public static void main(String[] args) {
		
		// 1. 스프링 IoC 컨테이너를 생성한다.
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("applicationContext.xml");
		
		// 2. 스프링 컨테이너로부터 테스트할 객체를 검색(Lookup)한다.
		CollectionBean collection = (CollectionBean) container.getBean("list");
		
		Properties list = collection.getAddressList();
		// Key 목록(이름)을 꺼낸다.
		Set<Object> nameList = list.keySet();
		for (Object name : nameList) {
			System.out.println("---> " + name.toString());
		}
		
		// Value 목록(주소)을 꺼낸다.
		Collection<Object> addressList = list.values();
		for (Object address : addressList) {
			System.out.println("---> " + address.toString());
		}

		// 3. 컨테이너를 종료한다. 
		container.close();		
	}

}



















