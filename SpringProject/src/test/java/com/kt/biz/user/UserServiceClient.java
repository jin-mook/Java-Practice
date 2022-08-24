package com.kt.biz.user;

import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {
		// 1. 스프링 컨테이너를 구동한다. 
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. 테스트할 비즈니스 객체를 Lookup한다. 
		UserService userService = (UserService) container.getBean("userService");
		
		// 3. Lookup한 객체를 테스트한다. 
		UserVO vo = new UserVO();
		vo.setId("test");
		vo.setPassword("test");
		UserVO user = userService.getUser(vo);
		if(user != null) {
			System.out.println(user.getName() + "님 로그인 환영");
		} else {
			System.out.println("로그인 실패");
		}
		
		// 4. 컨테이너를 종료한다. 
		container.close();
	}

}
