package com.returnp.admin.security;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.returnp.admin.model.Admin;
import com.returnp.admin.model.AdminRole;
import com.returnp.admin.service.interfaces.AdminRoleService;
import com.returnp.admin.service.interfaces.SearchService;
/**
 *
 * @author h2y
 *
 */
@Service
public class LoginUserDetailService implements UserDetailsService {
	
	private final Logger logger =LoggerFactory.getLogger(LoginUserDetailService.class);
	
	@Autowired
	private SearchService serachService;
	
	@Autowired
	private AdminRoleService adminRoleService;

	@Override
	public UserDetails loadUserByUsername(final String username) {
	
	    logger.debug("username : " + username);	
		
		Admin param = new Admin();
		Admin adminCond= null;
		param.setAdminEmail(username);
		ArrayList<Admin> admins = this.serachService.findAdmins(param);		
	
		if (admins != null && admins.size()>0) {
		
			adminCond = admins.get(0);
			
			if (adminCond != null) {		        
			    List<AdminRole> roles = adminRoleService.selectByAdminNo(adminCond.getAdminNo());
			    adminCond.setAuthorities(roles);
			}
		}
		return adminCond;
	}
}
