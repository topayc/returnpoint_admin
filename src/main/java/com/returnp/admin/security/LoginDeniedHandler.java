package com.returnp.admin.security;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

public class LoginDeniedHandler implements AccessDeniedHandler {
    private Boolean redirect = true;
    public Boolean getRedirect() {
        return redirect;
    }
    public void setRedirect(Boolean redirect) {
        this.redirect = redirect;
    }
 
    private String errorPage;
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
    public String getErrorPage() {
        return errorPage;
    }
 
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
       
    	StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		accessDeniedException.printStackTrace(pw);
		String sStackTrace = sw.toString();
		
    	System.out.println("Exception Class Name :::::::::::::::::::::::::::::::::::: " +  sStackTrace);
    	
        if( redirect ){
            response.sendRedirect(errorPage);
        }else{
            RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
            dispatcher.forward(request, response);
        }
    }
 
}

