package com.kt.biz.board;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	

	public static void main(String[] args) {
		// 1. 스프링 컨테이너를 구동한다. 
		GenericXmlApplicationContext container = 
			new GenericXmlApplicationContext("business-layer.xml");
		
		// 2. 테스트할 비즈니스 객체를 Lookup한다. 
		BoardService boardService = 
			(BoardService) container.getBean("boardService");
		
		// 3. Lookup한 객체를 테스트한다. 
		BoardVO vo = new BoardVO();
		vo.setSeq(101);
		vo.setTitle("스프링JDBC 테스트");
		vo.setWriter("테스터");
		vo.setContent("스프링JDBC 테스트중입니다...");
		//boardService.insertBoard(vo);
		
		List<BoardVO> boardList = boardService.getBoardList(vo);
		for (BoardVO board : boardList) {
			System.out.println("---> " + board.toString());
		}
		
		// 4. 컨테이너를 종료한다. 
		container.close();
	}

}
