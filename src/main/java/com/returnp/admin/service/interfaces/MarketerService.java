package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Marketer;

@Transactional
public interface MarketerService {
	public ReturnpBaseResponse createMarketer(int count);
	public ReturnpBaseResponse deleteMarketer(int marketerNo);
	public ReturnpBaseResponse udpateMarketer(Marketer marketer);
	public ReturnpBaseResponse findMarketerCommands(MarketerCommand marketerCommand);
	public ReturnpBaseResponse findMarketers(Marketer marketer);
	
}
