package com.kt.biz.board;

import java.util.List;

// 3. Service 인터페이스
public interface BoardService {

	void insertBoard(BoardVO vo);

	void updateBoard(BoardVO vo);

	void deleteBoard(BoardVO vo);

	BoardVO getBoard(BoardVO vo);

	List<BoardVO> getBoardList(BoardVO vo);
}