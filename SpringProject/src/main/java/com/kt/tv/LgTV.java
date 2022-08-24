package com.kt.tv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tv")
public class LgTV implements TV {
	
	// @Autowired 주의사항
	// 1. 반드시 의존성 주입 대상 객체가 메모리에 있어야 한다. 
	// 2. 의존성 주입 대상 객체는 반드시 유일해야 한다. 
	@Autowired // Type Injection	
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("===> LgTV 생성");
	}
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}
}




















