package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.Marketer;

@Transactional
public interface MarketerService {
	public BaseResponse createMarketer(int count);
	public BaseResponse deleteMarketer(int marketerNo);
	public BaseResponse udpateMarketer(Marketer marketer);
	public BaseResponse findMarketerCommands(MarketerCommand marketerCommand);
	public BaseResponse findMarketers(Marketer marketer);
	
}
