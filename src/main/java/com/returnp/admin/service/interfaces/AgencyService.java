package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.model.Agency;

@Transactional
public interface AgencyService {
	 int deleteByPrimaryKey(Integer agencyNo);

	    int insert(Agency record);

	    int insertSelective(Agency record);

	    Agency selectByPrimaryKey(Integer agencyNo);
	    AgencyCommand  selectAgencyCommandByPrimaryKey(Integer agencyNo);

	    int updateByPrimaryKeySelective(Agency record);

	    int updateByPrimaryKey(Agency record);
}
