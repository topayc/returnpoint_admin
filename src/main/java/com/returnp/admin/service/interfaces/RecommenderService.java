package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.Recommender;

@Transactional
public interface RecommenderService {
	int deleteByPrimaryKey(Integer recommenderNo);
	int insert(Recommender record);
	int insertSelective(Recommender record);
	Recommender selectByPrimaryKey(Integer recommenderNo);
	int updateByPrimaryKeySelective(Recommender record);
	int updateByPrimaryKey(Recommender record);
	
    public BaseResponse createRecommender(Recommender recommender);
    public BaseResponse updateRecommender(Recommender recommender);
    public BaseResponse deleteRecommender(int recommenderNo);
}
