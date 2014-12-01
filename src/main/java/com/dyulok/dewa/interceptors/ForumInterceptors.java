package com.dyulok.dewa.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ForumInterceptors implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest req,
			HttpServletResponse resp, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("afterCompletion executed...");
		
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2, ModelAndView arg3) throws Exception {
		System.out.println("postHandle executed...");
		
	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp,
			Object arg2) throws Exception {
		System.out.println("preHandle executed...");
		return true;
	}

}
