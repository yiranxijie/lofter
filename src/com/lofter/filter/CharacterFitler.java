package com.lofter.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterFitler implements Filter {
	private String charset="utf-8";

	public void destroy() {
		

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp=(HttpServletResponse) response;
		
		req.setCharacterEncoding(charset);
		resp.setCharacterEncoding(charset);
		chain.doFilter(req, resp);
		

	}

	public void init(FilterConfig config) throws ServletException {
		
		if( config.getInitParameter("charset")!=null){
			charset=config.getInitParameter("charset");
		}
	}

}
