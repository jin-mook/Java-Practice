package com.kt.tv;

public class SamsungTV implements TV {
	private int price;
	
	public SamsungTV() {
		System.out.println("===> SamsungTV 생성");
	}
	public void 멤버변수초기화() {
		System.out.println("===> 멤버변수초기화() 호출");
		price = 1700000;
	}
	public void 자원해제() {
		System.out.println("===> 자원해제() 호출");
		price = 0;
	}
	public void powerOn() {
		System.out.println("SamsungTV---전원 켠다." + price);
	}
	public void powerOff() {
		System.out.println("SamsungTV---전원 끈다.");
	}
	public void volumeUp() {
		System.out.println("SamsungTV---소리 올린다.");
	}
	public void volumeDown() {
		System.out.println("SamsungTV---소리 내린다.");
	}
}
