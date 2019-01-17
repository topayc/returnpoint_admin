package com.returnp.admin.service.interfaces;

import java.text.ParseException;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.Recommender;

@Transactional
public interface EventService {
	public void joinRecommenderEvent(Recommender recommender) throws ParseException;
}
