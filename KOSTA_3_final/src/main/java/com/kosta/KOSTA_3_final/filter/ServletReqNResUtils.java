package com.kosta.KOSTA_3_final.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletReqNResUtils {
	public static HttpServletRequest asHttpServletRequest(ServletRequest servletRequest) {
		if(!(servletRequest instanceof HttpServletRequest)) {
			throw new RuntimeException();
		}
		return (HttpServletRequest) servletRequest;
	}
	
	public static HttpServletResponse asHttpServletResponse(ServletResponse servletResponse) {
		return(HttpServletResponse) servletResponse;
	}
}
