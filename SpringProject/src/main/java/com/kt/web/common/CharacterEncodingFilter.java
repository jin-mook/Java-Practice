package com.kt.web.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(urlPatterns = {"*.kt", "*.jsp"}, 
           initParams = @WebInitParam(name = "encoding", value = "UTF-8"))
public class CharacterEncodingFilter implements Filter {
	private String encoding;

    public CharacterEncodingFilter() {
    	System.out.println("===> CharacterEncodingFilter 생성");
    }

	public void init(FilterConfig fConfig) throws ServletException {
		// FilterConfig를 이용하여 필터에 등록된 로컬 파라미터(<init-param>) 정보를 추출한다.
		encoding = fConfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 모든 서블릿이 실행되기 전에 인코딩을 처리한다. 
		request.setCharacterEncoding(encoding);
		
		chain.doFilter(request, response);
	}

	public void destroy() {
		encoding = null;
	}
}
