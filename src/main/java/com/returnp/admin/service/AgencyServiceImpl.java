package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.AgencyMapper;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.model.Agency;
import com.returnp.admin.service.interfaces.AgencyService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class AgencyServiceImpl implements AgencyService {

	@Autowired AgencyMapper agencyMapper;
	@Autowired SearchService searchService;

	@Override
	public int deleteByPrimaryKey(Integer agencyNo) {
		// TODO Auto-generated method stub
		return this.agencyMapper.deleteByPrimaryKey(agencyNo);
	}

	@Override
	public int insert(Agency record) {
		// TODO Auto-generated method stub
		return this.agencyMapper.insert(record);
	}

	@Override
	public int insertSelective(Agency record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Agency selectByPrimaryKey(Integer agencyNo) {
		// TODO Auto-generated method stub
		return this.agencyMapper.selectByPrimaryKey(agencyNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Agency record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(Agency record) {
		// TODO Auto-generated method stub
		return this.agencyMapper.updateByPrimaryKey(record);
	}

	@Override
	public AgencyCommand selectAgencyCommandByPrimaryKey(Integer agencyNo) {
		AgencyCommand command = new AgencyCommand();
		command.setAgencyNo(agencyNo);
		return this.searchService.findAgencyCommands(command).get(0);
	}
}
