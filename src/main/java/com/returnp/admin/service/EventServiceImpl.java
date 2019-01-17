package com.returnp.admin.service;

import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.dto.command.RecommenderCommand;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.service.interfaces.EventService;
import com.returnp.admin.service.interfaces.GreenPointService;
import com.returnp.admin.service.interfaces.MemberService;
import com.returnp.admin.service.interfaces.PolicyService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.EventInfo;
import com.returnp.admin.utils.EventInfo.EventStatus;

@Service
public class EventServiceImpl implements EventService {
	
	@Autowired MemberService memberService;
	@Autowired GreenPointService greenPointService;
	@Autowired SearchService searchService;
	@Autowired PolicyService policyService;
	
	
	public void findAndExecuteEvent() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-.dd HH:mm:ss", Locale.KOREA);
		Date currentTime = new Date();
		Method[] methods = this.getClass().getMethods();
		try {
			for (int i = 0; i < methods.length; i++) {				
				EventInfo eventInfo= methods[i].getAnnotation(EventInfo.class);
				if (eventInfo != null) {
					if (eventInfo.status() == EventStatus.ON && ( 
							currentTime.getTime() >= dateFormat.parse(eventInfo.startDate()).getTime() && 
							currentTime.getTime() <= dateFormat.parse(eventInfo.startDate()).getTime())){
						System.out.println(eventInfo.name() + " Processing");
						methods[i].invoke(this);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@EventInfo(
			name = "그랜드 오픈, 정회원 등록 이벤트", 
			description = "정회원 가입 이벤트, 정회원 가입시 등록비의 200% 적립, 추천인에게 50% 적립",
			key = "RP1001",
			link  = "",
			status = EventStatus.ON, 
			startDate = "2018-10-10 00:00:00", 
			endDate = "2018-10-10 23:59:59")
	@Override
	public void joinRecommenderEvent(Recommender recommender) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-.dd HH:mm:ss", Locale.KOREA);
		Date currentTime = new Date();

		String startDate = "2018-10-10 00:00:00";
		String endDate = "2018-10-10 23:59:59";
		
		if (currentTime.getTime() >= dateFormat.parse(startDate).getTime() && 
				currentTime.getTime() <= dateFormat.parse(endDate).getTime()) {
			Policy policy= this.searchService.findPolicies(new Policy()).get(0);
			Member recMember = this.memberService.selectByPrimaryKey(recommender.getMemberNo());
			
			/* 정회원  등록시 입금한 포인트의 2배 지급 */
			GreenPoint greenPoint= new GreenPoint();
			greenPoint.setMemberNo(recommender.getMemberNo());
			greenPoint.setNodeType(AppConstants.NodeType.MEMBER);
			greenPoint = this.searchService.findGreenPoints(greenPoint).get(0);
			greenPoint.setPointAmount(greenPoint.getPointAmount()   + (policy.getMembershipTransLimit() * 2));
			this.greenPointService.updateByPrimaryKey(greenPoint);
			
			/*정회원을 등록한 회원의 추천인에게 1/2 지급*/
			if (recMember.getRecommenderNo() != null) {
				Recommender recRec  = new Recommender();
				recRec.setRecommenderNo(recMember.getRecommenderNo());
				recRec = this.searchService.findRecommenders(recRec).get(0);
				
				Member recMember2 = this.memberService.selectByPrimaryKey(recRec.getMemberNo());
				GreenPoint greenPoint2= new GreenPoint();
				greenPoint2.setMemberNo(recMember2.getMemberNo());
				greenPoint2.setNodeType(AppConstants.NodeType.MEMBER);
				greenPoint2 = this.searchService.findGreenPoints(greenPoint2).get(0);
				greenPoint2.setPointAmount(greenPoint2.getPointAmount()   + (policy.getMembershipTransLimit() / 2));
				this.greenPointService.updateByPrimaryKey(greenPoint2);
			}
			
		}
	}
}
