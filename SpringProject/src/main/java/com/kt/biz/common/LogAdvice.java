package com.kt.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect // Aspect = Pointcut(핵심관심) + Advice(횡단관심)
public class LogAdvice {
	
	@Before("BoardPointcut.allPointcut()")
	public void printLog(JoinPoint jp) {
		String method = jp.getSignature().getName();
		// 호출된 비즈니스 메소드의 인자 목록을 배열로 얻을 수 있다. 
		Object[] args = jp.getArgs(); 
		
		System.out.println("[사전 처리] " + method + 
			"() 메소드 인자 정보 : " + args[0].toString());
	}
}
