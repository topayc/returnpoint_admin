package com.returnp.admin.dao.mapper;

import java.util.ArrayList;

import com.returnp.admin.model.ApiService;

public interface ApiServiceMapper {
    int deleteByPrimaryKey(Integer apiServiceNo);

    int insert(ApiService record);

    int insertSelective(ApiService record);

    ApiService selectByPrimaryKey(Integer apiServiceNo);

    int updateByPrimaryKeySelective(ApiService record);

    int updateByPrimaryKey(ApiService record);
    
    ArrayList<ApiService >findApiServices(ApiService service);
}