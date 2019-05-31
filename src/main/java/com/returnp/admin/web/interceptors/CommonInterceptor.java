package com.returnp.admin.web.interceptors;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class CommonInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(CommonInterceptor.class);
	
    @Value("#{properties['log.parameter']}")
    private boolean showParameter;
    
    @Value("#{properties['log.attribute']}")
    private boolean showAttribute;
    
    @Value("#{properties['log.list']}")
    private boolean showList;
    
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
	 
		printConsoleLog(true,request);
        return true;
	}
	
	
    private void printConsoleLog(boolean debug,HttpServletRequest request) {
    	
    	if(debug) {
	    	
	    	HttpSession session = request.getSession();
	    	
	    	logger.info("------------------------------------- [WEB]Access info ---------------------------------------");
	    	logger.info("Secure Mode ("+request.getScheme()+") : " + (request.isSecure()? "Secure Mode" : "Nomal Mode"));
	    	logger.info("GET/POST : " + request.getMethod());	  
	        logger.info("RequestUrl : " + request.getRequestURL());	       
	        logger.info("Referer : " + request.getHeader("REFERER"));	       
	        logger.info("QueryString : " + request.getQueryString());
	        logger.info("Parameter Size : " + request.getParameterMap().size());
	        logger.info("----------------------------------------------------------------------------------------");
	        
	        boolean showLine = false;
	        
	        if (showParameter) {
	        	
	            Enumeration<String> paramNames = request.getParameterNames();
	            showLine = paramNames.hasMoreElements();
	            if (showLine) logger.debug("------------------------------------- [WEB]request param info -------------------------------");
	            
	            while (paramNames.hasMoreElements()) {
	                String key = (String) paramNames.nextElement();
	                String value = request.getParameter(key);
	                
	                logger.debug("[request] " + key + " : " + value );
	            }
	            if (showLine) logger.debug("----------------------------------------------------------------------------------------");
	        }
	        
	        if (showAttribute) {
	            Enumeration<String> attributeNames = request.getAttributeNames();
	            showLine = attributeNames.hasMoreElements();
	            if (showLine) logger.debug("------------------------------------- [WEB]request att info ---------------------------------");
	            while (attributeNames.hasMoreElements()) {
	                String key = (String) attributeNames.nextElement();
	                String value = request.getParameter(key);
	                logger.debug("[request] " + key + " : " + value + "");
	            }
	            if (showLine) logger.debug("----------------------------------------------------------------------------------------");
	        }
    	}
    }
    
}
