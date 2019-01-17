package com.returnp.admin.security;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.model.Admin;
import com.returnp.admin.web.controller.ApplicationController.RequestRedirect;

@Service
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
    @Override
	public final void onAuthenticationSuccess(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication)
					throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		Admin admin = null;
		try{
			admin = (Admin) authentication.getPrincipal();
			
		}catch(Exception e){logger.error("사용자 정보를 찾을 수 없습니다.");}

		if(admin!=null){
			AdminSession adminSession = new AdminSession();
			adminSession.setAdmin(admin);
			adminSession.setLogin(true);
			adminSession.setLoginDatetime(new Date());
			session.setAttribute(AppConstants.ADMIN_SESSION, adminSession);

			response.sendRedirect("/");
		}
	}

}
