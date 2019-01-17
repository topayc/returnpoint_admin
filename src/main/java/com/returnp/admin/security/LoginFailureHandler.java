package com.returnp.admin.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private final Logger logger =LoggerFactory.getLogger(LoginFailureHandler.class);

	@Override
	public void onAuthenticationFailure(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub

	    System.out.println("Exception Class Name :::::::::::::::::::::::::::::::::::: " +  exception.getClass());
	        
		request.setAttribute("errorMessage", exception.getMessage());
		request.getRequestDispatcher("signIn").forward(request, response);		
	}
}
