package com.kt.biz.common;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class BoardPointcut {
	
	@Pointcut("execution(* com.kt.biz..*Impl.*(..))")
	public void allPointcut() {}
	
	@Pointcut("execution(* com.kt.biz..*Impl.get*(..))")
	public void getPointcut() {}
	
	@Pointcut("execution(* com.kt.biz.board.*Impl.*(..))")
	public void boardPointcut() {}
	
	@Pointcut("execution(* com.kt.biz.user.*Impl.*(..))")
	public void userPointcut() {}
}
