package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Recommender;

public interface RecommenderMapper {
    int deleteByPrimaryKey(Integer recommenderNo);

    int insert(Recommender record);

    int insertSelective(Recommender record);

    Recommender selectByPrimaryKey(Integer recommenderNo);

    int updateByPrimaryKeySelective(Recommender record);

    int updateByPrimaryKey(Recommender record);
}