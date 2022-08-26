package com.kt.biz.board;

import java.sql.Date;

import com.kt.biz.common.SearchVO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// VO(Value Object) 클래스
// 메소드의 매개변수나 리턴 데이터를 하나의 객체로 통합하는 클래스 
@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BoardVO extends SearchVO {
	// 테이블의 컬럼 이름에 해당하는 멤버변수
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
}











