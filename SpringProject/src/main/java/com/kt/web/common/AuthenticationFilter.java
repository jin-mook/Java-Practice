package com.kt.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kt.biz.user.UserVO;

@WebFilter("*.kt")
public class AuthenticationFilter implements Filter {
	public AuthenticationFilter() {
		System.out.println("===> AuthenticationFilter 생성");
	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {		
	
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		String uri = req.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		if(path.equals("/login.kt")) {
			chain.doFilter(request, response);
		} else {
			HttpSession session = req.getSession();
			UserVO user = (UserVO) session.getAttribute("user");
			
			// 세션에 회원의 아이디가 있는지 확인한다.
			if(user == null) {
				res.sendRedirect("login.html");
			} else {	
				chain.doFilter(req, res);
			}
		}

	}
	
	public void destroy() {
	}

}
