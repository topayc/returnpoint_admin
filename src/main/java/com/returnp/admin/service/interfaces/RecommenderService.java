package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Recommender;

@Transactional
public interface RecommenderService {
	int deleteByPrimaryKey(Integer recommenderNo);
	int insert(Recommender record);
	int insertSelective(Recommender record);
	Recommender selectByPrimaryKey(Integer recommenderNo);
	int updateByPrimaryKeySelective(Recommender record);
	int updateByPrimaryKey(Recommender record);
	
    public ReturnpBaseResponse createRecommender(Recommender recommender);
    public ReturnpBaseResponse updateRecommender(Recommender recommender);
    public ReturnpBaseResponse deleteRecommender(int recommenderNo);
}
