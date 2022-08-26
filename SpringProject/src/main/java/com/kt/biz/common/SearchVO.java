package com.kt.biz.common;

import lombok.Data;

@Data
public class SearchVO {
	// 검색 관련 변수
	private String searchCondition;
	private String searchKeyword;
}
