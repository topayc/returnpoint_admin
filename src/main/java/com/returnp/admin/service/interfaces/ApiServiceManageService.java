package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.ApiService;

@Transactional
public interface ApiServiceManageService {
	 int deleteByPrimaryKey(Integer apiServiceNo);

	    int insert(ApiService record);

	    int insertSelective(ApiService record);

	    ApiService selectByPrimaryKey(Integer apiServiceNo);
	    
	    ArrayList<ApiService> findApiServices(ApiService apiService);

	    int updateByPrimaryKeySelective(ApiService record);

	    int updateByPrimaryKey(ApiService record);
}
