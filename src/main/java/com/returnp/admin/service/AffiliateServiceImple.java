package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.AffiliateMapper;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.service.interfaces.AffiliateService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class AffiliateServiceImple implements AffiliateService {

	
	@Value("#{properties['sales_point.target_info']}")
	private String salePontTarget;

	@Value("#{properties['sales_point.fee_rate']}")
	private float salePointFeeRate;
	
	@Autowired AffiliateMapper affiliateMapper;
	@Autowired SearchService searchService;

	@Override
	public int deleteByPrimaryKey(Integer affiliateNo) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.deleteByPrimaryKey(affiliateNo);
	}

	@Override
	public int insert(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.insert(record);
	}

	@Override
	public int insertSelective(Affiliate record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Affiliate selectByPrimaryKey(Integer affiliateNo) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.selectByPrimaryKey(affiliateNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.updateByPrimaryKey(record);
	}

	@Override
	public AffiliateCommand selectAffiliateCommandByPrimaryKey(Integer affiliateNo) {
		AffiliateCommand command = new AffiliateCommand();
		command.setAffiliateNo(affiliateNo);
		return this.searchService.findAffiliateCommands(command).get(0);
	}

	@Override
	public ReturnpBaseResponse salePontAcc(String salePontTarget, String targetDateStr) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
        
        try {
            return res;
            
        }catch(Exception e) {
            e.printStackTrace();
            return res;
        }
	}

}
