package com.returnp.admin.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.returnp.admin.dto.CodeKeyValuePair;
import com.returnp.admin.model.ApiService;

public class Common {

    public static Map<String,Object> objectToMap(Object obj) {
        try {
                // Field[] fields = obj.getClass().getFields(); //private field는 나오지 않음.
                Field[] fields = obj.getClass().getDeclaredFields();
                Map<String,Object> resultMap = new HashMap<String,Object>();
                for (int i = 0; i <= fields.length - 1; i++) {
                        fields[i].setAccessible(true);
                        resultMap.put(fields[i].getName(), fields[i].get(obj));
                }
                return resultMap;
        } catch (IllegalArgumentException e) {
                e.printStackTrace();
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        }
        return null;
    }
    
    public static Map<String, Object> voToMap(Object abc) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Map<String, Object> map = new HashMap<>();

        Field[] fields = abc.getClass().getDeclaredFields();

        for(Field field : fields){
            String filedName = field.getName();
            String methodName = filedName.replace(filedName.substring(0, 1), filedName.substring(0, 1).toUpperCase());
            map.put(filedName, abc.getClass().getDeclaredMethod("get" + methodName).invoke(abc));
        }

        return map;
    }
    
	public CodeKeyValuePair getNodeType(ArrayList<CodeKeyValuePair>codeKeyValues, String code) {
		for(CodeKeyValuePair data : codeKeyValues) {
			if (data.getKey().equals(code)) {
				return data;
			}
		}
		return null;
	}
	
    public static String createApiServiceToken(String apiService) {
		SecureRandom random = new SecureRandom ();
		byte rnd[] = new byte[200];		
		random.nextBytes(rnd);
		String rndStr = rnd.toString() + apiService;
		String token = Crypto.encode_base64(rndStr.getBytes(),rndStr.length());
		
    	return token;
    }
    
    public static String createApiToken(String data) {
    	   String token = null;
    	    SecureRandom secureRandom;
    	    try {
    	        secureRandom = SecureRandom.getInstance("SHA1PRNG");
    	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
    	        secureRandom.setSeed(secureRandom.generateSeed(128));
    	        token= new String(digest.digest((secureRandom.nextLong() + "").getBytes()));
    	        token = Crypto.encode_base64(token.getBytes(),token.length());
    	    } catch (NoSuchAlgorithmException e) {
    	    	e.printStackTrace();
    	    }
    	    return token;
    }
    /*
    404 - RESOURCE NOT FOUND
	400 - BAD REQUEST
	401 - UNAUTHORIZED
	415 - UNSUPPORTED TYPE - Representation not supported for the resource
	500 - SERVER ERROR
	*/
    public static String decodeApiKey(String token) throws Exception {
		
    	if(token==null) {			
			throw new NullPointerException("verify data is empty.");
		}
    	
		System.out.println(token);
		String decodeToken = new String(Crypto.decode_base64(token,token.length()));
		System.out.println(decodeToken);
		
    	return decodeToken;
    }
    
    
    public static ArrayList<ApiService> getApiServiceList(ApplicationContext applicationContext){
    	ApiService apiMap;
    	ArrayList<ApiService> apiList = new ArrayList<ApiService>();
    	Map<String, Object> map = applicationContext.getBeansWithAnnotation(Controller.class);
		
		for (final Object clazz : map.values()) {
			Class<? extends Object> targetClass = clazz.getClass();
			
			ApiServiceInfo apiServiceInfoGroup =  targetClass.getAnnotation(ApiServiceInfo.class);
			RequestMapping annotationGroup = targetClass.getAnnotation(RequestMapping.class);
			String groupString="";
			if (annotationGroup != null) {
				/* GET,POST 필요한 경우 구현하여 사용
				for (RequestMethod s : annotation.method()) {
					System.out.println(annotation.value()[0]+":"+s);
				}
				*/
				groupString = annotationGroup.value()[0];
				if(apiServiceInfoGroup!= null) {
					apiMap = new ApiService();
					apiMap.setApiService(groupString);
					apiMap.setApiServiceName(apiServiceInfoGroup.value());
					apiList.add(apiMap);
				}
			}
			
			Method[] methods = targetClass.getMethods();
			//System.out.println("Controller:" + targetClass);
			for (int i = 0; i < methods.length; i++) {				
				ApiServiceInfo apiServiceInfo = methods[i].getAnnotation(ApiServiceInfo.class);
				RequestMapping annotation = methods[i].getAnnotation(RequestMapping.class);
					
				if (annotation != null) {
					/* GET,POST 필요한 경우 구현하여 사용
					for (RequestMethod s : annotation.method()) {
						System.out.println(annotation.value()[0]+":"+s);
					}
					*/
					if(apiServiceInfo!= null) {
						apiMap = new ApiService();
						apiMap.setApiService(groupString+annotation.value()[0]);
						apiMap.setApiServiceName(apiServiceInfo.value());
						apiList.add(apiMap);
					}
					
				}
			}
		}
		
		return apiList;
    }

	public static String createRfId(String apiService) {
		return null;
	}
}