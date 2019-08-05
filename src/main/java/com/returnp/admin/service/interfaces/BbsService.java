package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.MainBbs;
import com.returnp.admin.model.SubBbs;

@Transactional
public interface BbsService {
	public ReturnpBaseResponse selectMainBbses(MainBbs mainBbs);
	public ReturnpBaseResponse selectSubBbses(SubBbs mainBbs);

	public ReturnpBaseResponse createMainBbs(MainBbs bbs, HttpSession session);
	public ReturnpBaseResponse createSubBbs(SubBbs bbs);
	
	public ReturnpBaseResponse updateMainBbs(MainBbs bbs, HttpSession session);
	public ReturnpBaseResponse updateSubBbs(SubBbs bbs);
	
	public ReturnpBaseResponse removeMainBbs(MainBbs bbs);
	public ReturnpBaseResponse removeSubBbs(SubBbs bbs);
	public ReturnpBaseResponse reply(SubBbs subBbs, HttpSession httpSession);
	
}
