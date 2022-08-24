package com.kt.biz.common;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.kt.biz.user.UserVO;

@Service
@Aspect
public class AfterReturningAdvice {	

	// after-returning은 비즈니스 메소드가 리턴하는 데이터를 바인드 변수를 통해 받을 수 있다.
	@AfterReturning(pointcut = "BoardPointcut.getPointcut()", returning = "returnObj")
	public void afterLog(Object returnObj) {
		System.out.println("[사후 처리] 메소드 리턴값 : " + returnObj.toString());
		
		// 비즈니스 메소드가 리턴한 데이터가 UserVO 타입의 데이터냐?
		if(returnObj instanceof UserVO) {
			UserVO user = (UserVO) returnObj;
			// UserVO 객체의 권한이 ADMIN이냐?
			if(user.getRole().equals("ADMIN")) {
				System.out.println(user.getName() + "님 관리자 페이지로 이동합니다.");
			}
		}
	}
}
