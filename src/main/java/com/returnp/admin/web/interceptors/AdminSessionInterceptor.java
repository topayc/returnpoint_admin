package com.returnp.admin.web.interceptors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.returnp.admin.common.AppConstants;

public class AdminSessionInterceptor extends HandlerInterceptorAdapter {
	
	private Logger logger = Logger.getLogger(AdminSessionInterceptor.class);
	
	@Value("#{properties['log.session']}")
    private boolean showSession;
    
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
		
		try {
			if (request.getSession().getAttribute(AppConstants.ADMIN_SESSION) == null) {
				if (isAjaxRequest(request)) {
					response.sendError(400);
					return false;
				} else {
					response.sendRedirect("signIn");
					return false;
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
        return true;
	}
	
    private boolean isAjaxRequest(HttpServletRequest req) {
        String header = req.getHeader("AJAX");
        if ("true".equals(header)){
         return true;
        }else{
         return false;
        }
    }
    
    private void printConsoleLog(boolean debug,HttpServletRequest request) {
    	
    	if(debug) {
	    	
	    	HttpSession session = request.getSession();
	    	
	    	logger.info("------------------------------------- [WEB]login info ---------------------------------------");
	        logger.info("RequestUrl : " + request.getRequestURL());
	        logger.info("UserIP : " + request.getRemoteAddr());
	        logger.info("ClientIP : " + getUserIP(request));
	        logger.info("Referer : " + request.getHeader("REFERER"));
	        logger.info("User-Agent : " + request.getHeader("User-Agent"));
	        logger.info("QueryString : " + request.getQueryString());
	        logger.info("Parameter Size : " + request.getParameterMap().size());
	        logger.info("----------------------------------------------------------------------------------------");
	        
	        boolean showLine = false;
	        
	        if (showSession) {
	            
	            Enumeration<String> enum_01 = session.getAttributeNames();
	            showLine = enum_01.hasMoreElements();
	            if (showLine) logger.info("------------------------------------- [WEB]session info -------------------------------------");
	            while (enum_01.hasMoreElements()) {
	                String session_name = enum_01.nextElement().toString();
	                Object session_value = session.getAttribute(session_name);
	                
	                logger.info("[session] " + session_name + " : " + objectToString(session_value));
	            }
	            if (showLine) logger.info("----------------------------------------------------------------------------------------");
	        }
	        
	        if (showParameter) {
	            Enumeration<String> paramNames = request.getParameterNames();
	            showLine = paramNames.hasMoreElements();
	            if (showLine) logger.info("------------------------------------- [WEB]request param info -------------------------------");
	            
	            while (paramNames.hasMoreElements()) {
	                String key = (String) paramNames.nextElement();
	                String value = request.getParameter(key);
	                logger.info("[request] " + key + " : " + value + "");
	            }
	            if (showLine) logger.info("----------------------------------------------------------------------------------------");
	        }
	        
	        if (showAttribute) {
	            Enumeration<String> attributeNames = request.getAttributeNames();
	            showLine = attributeNames.hasMoreElements();
	            if (showLine) logger.info("------------------------------------- [WEB]request att info ---------------------------------");
	            while (attributeNames.hasMoreElements()) {
	                String key = (String) attributeNames.nextElement();
	                String value = request.getParameter(key);
	                logger.info("[request] " + key + " : " + value + "");
	            }
	            if (showLine) logger.info("----------------------------------------------------------------------------------------");
	        }
    	}
    }
    
    private String objectToString(Object obj) {
        
        String returnStr = "";
        
        if (obj instanceof String) {
            returnStr = (String) obj;
        } else if (obj instanceof Map) {
            Map<?, ?> map = (Map) obj;
            
            for (Map.Entry entry : map.entrySet()) {
                returnStr += entry.getKey() + "=" + entry.getValue() + ",";
            }
        } else if (obj instanceof ArrayList) {
            
            if (showAttribute) {
                List<?> objList = (List<?>) obj;
                
                for (Object o : objList) {
                    returnStr += "\n[";
                    returnStr += objectToString(o) + " ";
                    returnStr += "]";
                }
                returnStr = returnStr.replace(",\n", ",");
                returnStr = returnStr.trim().replace(" ", ",");
            } else {
                returnStr = "ArrayList [******내용확인: application.properties -> log.list = true******]";
            }
            
            return returnStr;
        } else {
            try {
                
                for (Field field : obj.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    returnStr += field.getName() + "=" + field.get(obj) + ",";
                }
                
            } catch (Exception e) {
                returnStr = "can not read session value.";
            }
            
        }
        returnStr = returnStr.replace(",", ",\n");
        return returnStr;
    }
    
    public String getUserIP(HttpServletRequest request) {
        
        String ip = request.getHeader("X-FORWARDED-FOR");
        
        logger.info("TEST : X-FORWARDED-FOR : " + ip);
        
        if (ip == null) {            
            ip = request.getHeader("Proxy-Client-IP");            
            logger.info("TEST : Proxy-Client-IP : " + ip);            
        }
        
        if (ip == null) {            
            ip = request.getHeader("WL-Proxy-Client-IP");            
            logger.info("TEST : WL-Proxy-Client-IP : " + ip);            
        }
        
        if (ip == null) {            
            ip = request.getHeader("HTTP_CLIENT_IP");            
            logger.info("TEST : HTTP_CLIENT_IP : " + ip);            
        }
        
        if (ip == null) {            
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");            
            logger.info("TEST : HTTP_X_FORWARDED_FOR : " + ip);            
        }
        
        if (ip == null) {            
            ip = request.getRemoteAddr();            
        }
        
        return ip;
        
    }
}
