package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.MarketerMapper;
import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.dto.command.SoleDistCommand;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.model.Marketer;
import com.returnp.admin.service.interfaces.MarketerService;
import com.returnp.admin.web.message.MessageUtils;

@Service
public class MarketerServiceImpl implements MarketerService {

	@Autowired MarketerMapper marketerMapper;
	@Autowired MessageUtils messageUtils;
	
	@Override
	public BaseResponse createMarketer(int count) {
		BaseResponse res = new BaseResponse();
		try {
			String code = this.marketerMapper.selectMaxCodeRow(); 
			if (code == null) code = "@";
			
			ArrayList<Marketer> parents = new ArrayList<Marketer>();
			ArrayList<Marketer> parentTemp = new ArrayList<Marketer>();
			ArrayList<Marketer> nodes = new ArrayList<Marketer>();
			ArrayList<String> genCodeList = CodeGenerator.generatorMarketerCode(code, count);
			/*for (String genCode : genCodeList) {
				System.out.println("genCode : " + genCode);
			}*/
			int index  = 0; 
			for (int i = 0 ; i < count; i++) {
				parents.clear();
				for (Marketer  marketer : parentTemp) {
					parents.add(marketer);
				}
				parentTemp.clear();
				for (int j = 0 ; j < Math.pow(2, i+ 1) ; j++) {
					Marketer marketer = new Marketer();
					marketer.setMarketerDegree(i);
					marketer.setMarketerCode(genCodeList.get(i) + "_"  + String.format("%08d", j+1));
					marketer.setMarketerStatus("1");
					if (i != 0) {
						marketer.setParent(parents.get(j / 2).getMarketerCode());
					} 
					parentTemp.add(marketer);
					nodes.add(marketer);
				}
				
			}
			
			for (Marketer marketer : nodes) {
				this.marketerMapper.insert(marketer);
			}
			ResponseUtil.setResponse(res, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
	}

	@Override
	public BaseResponse deleteMarketer(int marketerNo) {
		BaseResponse res = new BaseResponse();
		try {
			this.marketerMapper.deleteByPrimaryKey(marketerNo);
			ResponseUtil.setResponse(res, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
		
	}

	@Override
	public BaseResponse udpateMarketer(Marketer marketer) {
		BaseResponse res = new BaseResponse();
		try {
			this.marketerMapper.updateByPrimaryKeySelective(marketer);
			ResponseUtil.setResponse(res, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
	}

	@Override
	public BaseResponse findMarketerCommands(MarketerCommand command) {
		BaseResponse res = new BaseResponse();
		try {
			ArrayList<MarketerCommand> commands = this.marketerMapper.findMarketerCommand(command);
			ArrayListResponse<MarketerCommand> ar = new ArrayListResponse<MarketerCommand>();
			ar.setRows(commands);
			ar.setTotal(commands.size());
			ResponseUtil.setResponse(res, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
	}
}
