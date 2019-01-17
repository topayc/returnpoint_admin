package com.returnp.admin.security;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;


public class SetableParamRequest  extends HttpServletRequestWrapper {

    Map<String, String[]> params;

    public SetableParamRequest(HttpServletRequest request) {

        super(request);
        this.params = new HashMap<String, String[]>();
        this.params.putAll(request.getParameterMap()); 

    }

    @Override
    public String getParameter(String name) {

        String[] paramArray = getParameterValues(name);

        if (paramArray != null && paramArray.length > 0) {

            return paramArray[0];

        } else {

            return null;

        }

    }

    @Override
    public Map<String, String[]> getParameterMap() {

        return Collections.unmodifiableMap(params);

    }

    @Override
    public Enumeration<String> getParameterNames() {

        return Collections.enumeration(params.keySet());

    }

    @Override
    public String[] getParameterValues(String name) {

    	String[] result = null;
        String[] temp = (String[]) params.get(name);
        

        if (temp != null) {

            result = new String[temp.length];
            int count = 0;
            for(String o:temp) {
            	result[count++] = o.toString();
            }
        }

        return result;
    }

    public void setParameter(String name, String value) {

    	String[] oneParam = {value};
        setParameter(name, oneParam);

    }
    
    public void setParameter(String name, String[] values) {

        params.put(name, values);

    }
    
}