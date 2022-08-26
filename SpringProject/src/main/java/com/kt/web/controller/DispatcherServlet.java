package com.kt.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kt.biz.board.BoardDAOJdbc;
import com.kt.biz.board.BoardVO;
import com.kt.biz.user.UserDAO;
import com.kt.biz.user.UserVO;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 사용자 요청 path 정보를 추출한다. 
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		// 2. 요청 path에 따라 로직을 분기처리한다.
		if(path.equals("/login.kt")) {
			System.out.println("로그인 기능 처리");
			
			// Scriptlet : 일반적인 자바 코드가 작성되는 영역
			// 1. 사용자 입력정보 추출
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			// 2. DB 연동 처리
			UserVO vo = new UserVO();
			vo.setId(id);
			vo.setPassword(password);
			
			UserDAO userDAO = new UserDAO();
			UserVO user = userDAO.getUser(vo);
			
			// 3. 화면 이동
			if(user != null) {
				// 로그인 성공 시, 사용자 정보를 세션에 등록한다. 
				HttpSession session = request.getSession();
				session.setAttribute("user", user);

				response.sendRedirect("getBoardList.kt");
			} else {
				response.sendRedirect("/");
			}			
			
		} else if(path.equals("/logout.kt")) {
			System.out.println("로그아웃 기능 처리");
			
			// 로그아웃 요청한 브라우저와 매핑된 세션을 강제로 종료한다.
			HttpSession session = request.getSession();
			session.invalidate();
			
			// 메인 화면으로 이동한다.
			response.sendRedirect("/");
			
		} else if(path.equals("/insertBoard.kt")) {
			System.out.println("글 등록 기능 처리");
			
			// 1. 사용자 입력정보 추출
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setWriter(writer);
			vo.setContent(content);
			
			BoardDAOJdbc boardDAO = new BoardDAOJdbc();
			boardDAO.insertBoard(vo);
			
			// 2. 검색 결과를 바탕으로 응답 화면 구성
			response.sendRedirect("getBoardList.kt");
			
		} else if(path.equals("/updateBoard.kt")) {
			System.out.println("글 수정 기능 처리");
			
			// 1. 사용자 입력정보 추출
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setTitle(title);
			vo.setContent(content);
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJdbc boardDAO = new BoardDAOJdbc();
			boardDAO.updateBoard(vo);
			
			// 2. 검색 결과를 바탕으로 응답 화면 구성
			response.sendRedirect("getBoardList.kt");
			
		} else if(path.equals("/deleteBoard.kt")) {
			System.out.println("글 삭제 기능 처리");
			
			// 1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJdbc boardDAO = new BoardDAOJdbc();
			boardDAO.deleteBoard(vo);
			
			// 2. 검색 결과를 바탕으로 응답 화면 구성
			response.sendRedirect("getBoardList.kt");
			
		} else if(path.equals("/getBoard.kt")) {
			System.out.println("글 상세 조회 기능 처리");
			
			// 1. 사용자 입력정보 추출
			String seq = request.getParameter("seq");
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSeq(Integer.parseInt(seq));
			
			BoardDAOJdbc boardDAO = new BoardDAOJdbc();
			BoardVO board = boardDAO.getBoard(vo);
			
			// 3. Model(DAO)을 이용하여 검색한 데이터를 View(JSP)를 통해서 
			// 출력하기 위해 어딘가에 등록해야 한다. (request)
			request.setAttribute("board", board);
			RequestDispatcher dispatcher = request.getRequestDispatcher("getBoard.jsp");
			dispatcher.forward(request, response);
			
		} else if(path.equals("/getBoardList.kt")) {
			System.out.println("글 목록 검색 기능 처리");
			
			// 1. 사용자 입력정보 추출
			String searchCondition = request.getParameter("searchCondition");
			String searchKeyword = request.getParameter("searchKeyword");
			
			// Null Check
			if(searchCondition == null) searchCondition = "TITLE";
			if(searchKeyword == null) searchKeyword = "";
				
			
			// 2. DB 연동 처리
			BoardVO vo = new BoardVO();
			vo.setSearchCondition(searchCondition);
			vo.setSearchKeyword(searchKeyword);
			
			BoardDAOJdbc boardDAO = new BoardDAOJdbc();
			List<BoardVO> boardList = boardDAO.getBoardList(vo);
			
			// 3. Model(DAO)을 이용하여 검색한 데이터를 View(JSP)를 통해서 
			// 출력하기 위해 어딘가에 등록해야 한다. (request)
			request.setAttribute("boardList", boardList);
			request.setAttribute("search", vo);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("getBoardList.jsp");
			dispatcher.forward(request, response);
		}
	}

}
