package com.returnp.admin.service.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateDetail;

@Transactional
public interface AffiliateDetailService {
	public ReturnpBaseResponse updateAffiliateDetail(AffiliateDetail affilaiteDetail,  HttpServletRequest request);
	public ReturnpBaseResponse selectAffiliateDetails(AffiliateDetail affilaiteDetail);
	public ReturnpBaseResponse deleteAffiliateDetail(AffiliateDetail affiliateDetaill);
}
