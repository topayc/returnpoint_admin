package com.returnp.admin.service;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.AffiliateDetailMapper;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateDetail;
import com.returnp.admin.service.interfaces.AffiliateDetailService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.utils.FileManager;

@Service
public class AffiliateDetailServiceImpl implements AffiliateDetailService {
	@Autowired SearchService  searchService;
	@Autowired AffiliateDetailMapper affiliateDetailMapper;

	@Override
	public ReturnpBaseResponse selectAffiliateDetails(AffiliateDetail affilaiteDetail) {
		ArrayListResponse<AffiliateDetail> res = new ArrayListResponse<AffiliateDetail>();
		try {
			ResponseUtil.setSuccessResponse(res, "100" , "조회 성공");
			res.setRows(this.searchService.selectAffiliateDetails(affilaiteDetail));
			return res;
		} /*catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}*/catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse updateAffiliateDetail(AffiliateDetail affiliateDetail, HttpServletRequest request) {
		ObjectResponse< AffiliateDetail> res = new ObjectResponse< AffiliateDetail>();
		try {
			String message = "";
			File file = null;
			
			/*이미지 업로드 여부 판단*/
			if (!affiliateDetail.getAmImage1().isEmpty()) {
				file = FileManager.save(affiliateDetail.getAmImage1(),String.format(request.getSession().getServletContext().getRealPath("/resources/upload/images/affiliate_detail")+ "/%s", affiliateDetail.getAffiliateNo()));
				affiliateDetail.setAffiliateMainImage1(String.format("/resources/upload/images/affiliate_detail/%s/%s", affiliateDetail.getAffiliateNo(), file.getName()));
			}
			
			if (!affiliateDetail.getAmImage2().isEmpty()) {
				file = FileManager.save(affiliateDetail.getAmImage2(),String.format(request.getSession().getServletContext().getRealPath("/resources/upload/images/affiliate_detail")+ "/%s", affiliateDetail.getAffiliateNo()));
				affiliateDetail.setAffiliateMainImage2(String.format("/resources/upload/images/affiliate_detail/%s/%s", affiliateDetail.getAffiliateNo(), file.getName()));	
			}
			
			if (!affiliateDetail.getAmImage3().isEmpty()) {
				file = FileManager.save(affiliateDetail.getAmImage3(),String.format(request.getSession().getServletContext().getRealPath("/resources/upload/images/affiliate_detail")+ "/%s", affiliateDetail.getAffiliateNo()));
				affiliateDetail.setAffiliateMainImage3(String.format("/resources/upload/images/affiliate_detail/%s/%s", affiliateDetail.getAffiliateNo(), file.getName()));
			}
			
			if (!affiliateDetail.getAmImage4().isEmpty()) {
				file = FileManager.save(affiliateDetail.getAmImage4(),String.format(request.getSession().getServletContext().getRealPath("/resources/upload/images/affiliate_detail")+ "/%s", affiliateDetail.getAffiliateNo()));
				affiliateDetail.setAffiliateMainImage4(String.format("/resources/upload/images/affiliate_detail/%s/%s", affiliateDetail.getAffiliateNo(), file.getName()));
			}
			
			if (affiliateDetail.getAffiliateDetailNo() == 0) {
				/*insert*/	
				this.affiliateDetailMapper.insert(affiliateDetail);
				message = "협력업체 세부 정보 등록 성공";
			}else {
				/*update*/	
				this.affiliateDetailMapper.updateByPrimaryKeySelective(affiliateDetail);
				message = "협력업체 세부 정보 수정 성공";
			}
			
	
			res.setData(this.affiliateDetailMapper.selectByPrimaryKey(affiliateDetail.getAffiliateDetailNo()));
			ResponseUtil.setSuccessResponse(res, "100" , message);
			return res;
		}/* catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}*/catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "에러");
			return res;
		}
	}


	@Override
	public  ReturnpBaseResponse deleteAffiliateDetail(AffiliateDetail affiliateDetaill) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			
			ResponseUtil.setSuccessResponse(res, "100" , "삭제 성공");
			return res;
		}/* catch(ReturnpException e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return e.getBaseResponse();
		}*/catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "삭제 에러");
			return res;
		}
		
	}

	
}
