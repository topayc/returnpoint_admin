package com.returnp.admin.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ApiServiceResponse;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.SingleDataObjectResponse;
import com.returnp.admin.model.Admin;
import com.returnp.admin.model.AdminRole;
import com.returnp.admin.model.ApiService;
import com.returnp.admin.service.interfaces.ApiServiceManageService;
import com.returnp.admin.utils.Common;

@Controller
@RequestMapping("/v1/api")
@SessionAttributes("apiServiceFormInfo")
public class ApiAcessController extends ApplicationController{	
	
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired 
	private ApiServiceManageService apiServiceService;
	
	@ResponseBody
	@RequestMapping(value = {"/{dUrl}","/{sUrl}/{dUrl}","/{mUrl}/{sUrl}/{dUrl}","/{lUrl}/{mUrl}/{sUrl}/{dUrl}"}, method = {RequestMethod.POST,RequestMethod.GET})
	public  BaseResponse apiControl(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestHeader(value = "Authorization", required = true) String  access_key,
			@PathVariable Map<String, String> pathVariables) throws Exception{		
		
		String lUrlSting = !pathVariables.containsKey("lUrl") ? "" : "/" + pathVariables.get("lUrl");
		String mUrlSting = !pathVariables.containsKey("mUrl") ? "" : "/" + pathVariables.get("mUrl");
		String sUrlSting = !pathVariables.containsKey("sUrl") ? "" : "/" + pathVariables.get("sUrl");
		String dUrlSting = !pathVariables.containsKey("dUrl") ? "" : "/" + pathVariables.get("dUrl");
		
		return verifyKey(request,response,access_key,lUrlSting+mUrlSting+sUrlSting+dUrlSting);		
	}
	
	
	public  BaseResponse verifyKey(
			HttpServletRequest request,
			HttpServletResponse response,
			String access_key,
			String requestApiService) throws Exception{
		
		SingleDataObjectResponse<String> res = new SingleDataObjectResponse<String>();
		
		ApiService apiService=new ApiService();
		String ip = request.getRemoteAddr();
		String domain = request.getRemoteHost();
		
		String decodeApiKey = Common.decodeApiKey(access_key);
		//System.out.println("decodeApiKey:" + decodeApiKey);
		//System.out.println("requestApiService:" + requestApiService);
		if(!decodeApiKey.contains(requestApiService.replace("/api", ""))) {
			this.setErrorResponse(res,"허가되지 않은 접근입니다.");
			return res;
		}
		
		apiService.setApiService(requestApiService);
		apiService.setIp(ip);
		apiService.setDomain(domain);
		
		System.out.println("ip:" + ip);
		System.out.println("domain:" + domain);
		
		ArrayList<ApiService> as = apiServiceService.findApiServices(apiService);
		
		if(as.size()<1) {
			this.setErrorResponse(res,"서비스가 종료되었거나, 이용할 수 없는 서비스입니다.");
			return res;
		}
		
		boolean isExists = false;
		ArrayList<ApiService> apiServiceList = Common.getApiServiceList(applicationContext);
		Iterator<ApiService> itr = apiServiceList.iterator();			     
		while (itr.hasNext()) {
			ApiService each =itr.next();
			String api = each.getApiService();
			System.out.println("api:" + api + " | requestApiService :" + requestApiService);
			System.out.println("as.get(0).getApiKey():" + as.get(0).getApiKey());
			//서비스하고 있는 api중에 요청한 서비스가 해당되는지 확인하고,
			//requestApiService가 /api인 경우 /api로 시작하는 모든 apiservice를 이용할 수 있다고 가정
			if(requestApiService.equals(api) && requestApiService.startsWith(as.get(0).getApiService())) {
				isExists = true;
				break;
			}
		}
		
		if(!isExists) {
			this.setErrorResponse(res,"서비스되지 않거나,이용할 수 없는 서비스입니다.");	
			return res;
		}
		
		Admin admin = new Admin();
		admin.setAdminEmail(access_key);
		admin.setAdminPassword(access_key);
		List<AdminRole> roles = new ArrayList<AdminRole>();
		AdminRole role = new AdminRole();
		role.setRole("ROLE_API");
		roles.add(role);
		admin.setAuthorities(roles);
		Authentication authentication  = new UsernamePasswordAuthenticationToken(admin, admin.getPassword(), admin.getAuthorities());
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);		
		
		AdminSession adminSession = new AdminSession();
		adminSession.setAdmin(admin);
		adminSession.setLogin(true);
		adminSession.setLoginDatetime(new Date());
		request.getSession().setAttribute(AppConstants.ADMIN_SESSION, adminSession);
		request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
		
		//response.sendRedirect(requestApiService);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(requestApiService);
        dispatcher.forward(request, response);
        
		return null;
	}
	
	@ResponseBody
	//api test url  --> http://localhost:8080/v1/api/test
	@RequestMapping(value = "/test", method = {RequestMethod.POST,RequestMethod.GET})
	public  BaseResponse test(HttpServletRequest request, HttpServletResponse response,Model model){
		ApiServiceResponse<String> res = new ApiServiceResponse<String>();
		
		
		final String AUTH_HOST = "http://localhost:8080";
	    final String requestUrl = AUTH_HOST + "/v1/api/api/apiService/list";
	    System.out.println("Request Url : " + requestUrl);
	    HttpURLConnection conn = null;
	    OutputStreamWriter writer = null;
	    BufferedReader reader = null;
	    InputStreamReader isr= null;
	    
	    final String params = String.format("%s","UyH.KRayLRPiKBGtWV/nS0TwbkjhXQ7qYVLy");
  
	    URL url;
		try {
			url = new URL(requestUrl);
  
		    conn = (HttpURLConnection) url.openConnection();
		    conn.setRequestProperty("Authorization", params);	    
		    conn.setRequestMethod("POST");
		    conn.setDoOutput(true);
	
		    writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.flush();

		    final int responseCode = conn.getResponseCode();
		    System.out.println("Post parameters : " + params);
		    System.out.println("Response Code : " + responseCode);
	  
		    isr = new InputStreamReader(conn.getInputStream());
			reader = new BufferedReader(isr);
			final StringBuffer buffer = new StringBuffer();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
				System.out.println(line);
			}
		    ObjectMapper mapper = new ObjectMapper();
		    mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
		    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			res = mapper.readValue(buffer.toString(), ApiServiceResponse.class);
			
		} catch (JsonParseException e) {
			System.out.println(request.getRequestURI());
			this.setErrorResponse(res,"시스템 에러입니다.");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println(request.getRequestURI());
			this.setErrorResponse(res,"시스템 에러입니다.");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			this.setErrorResponse(res,e.getMessage());
			e.printStackTrace();
		} catch (ProtocolException e) {
			this.setErrorResponse(res,e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			this.setErrorResponse(res,e.getMessage());
			e.printStackTrace();
		} 
		return res;
	}
}
