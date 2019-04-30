package com.returnp.admin.security;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.returnp.admin.model.Admin;
import com.returnp.admin.model.AdminRole;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired(required = false)
    private HttpServletRequest request;
	
	@Autowired
	private LoginUserDetailService loginUserDetailService;
	
	@Override
	public final Authentication authenticate(
			final Authentication authentication)
			throws AuthenticationException{

		
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		Admin admin = null;		
		List<AdminRole> authorities;
		
		admin =  (Admin) loginUserDetailService.loadUserByUsername(username);
		if (admin==null) {
			throw new InternalAuthenticationServiceException("접속자 정보를 찾을 수 없습니다.");
		}

		/*if (!Crypto.sha(password).equals(admin.getPassword())) {*/
		if (!password.equals(admin.getPassword())) {
			throw new InternalAuthenticationServiceException("비밀번호가 일치하지 않습니다.");
		}
		
		if (admin.getAuthorities().size()<1) {
            throw new InternalAuthenticationServiceException("해당 사용자의 권한이 없습니다. 시스템 관리자에게 문의하세요.");
        }
		
		authorities = admin.getAuthorities();

		return new UsernamePasswordAuthenticationToken(admin, password, authorities);
	}

	@Override
	public final boolean supports(final Class<?> authentication) {		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
