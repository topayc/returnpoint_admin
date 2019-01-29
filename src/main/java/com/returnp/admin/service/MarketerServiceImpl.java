package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.code.CodeGenerator;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MarketerMapper;
import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Marketer;
import com.returnp.admin.service.interfaces.MarketerService;
import com.returnp.admin.web.message.MessageUtils;

@Service
public class MarketerServiceImpl implements MarketerService {

	@Autowired MarketerMapper marketerMapper;
	@Autowired MessageUtils messageUtils;
	
	/* 
	 * 마케팅 조직 생성
	 * 이진트리 을 1차원 배열로 생성 후 디비에 저장 
	 * (non-Javadoc)
	 * @see com.returnp.admin.service.interfaces.MarketerService#createMarketer(int)
	 */
	@Override
	public ReturnpBaseResponse createMarketer(int count) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			String code = null;
			int offset = 0;
			
			Marketer m = this.marketerMapper.selectMaxCodeRow(); 
			if (m == null) {
				code = "@";
				offset = 0;
			}else {
				code = m.getMarketerCode().split("_")[0];
				offset = m.getMarketerDegree();
			}
			 
			ArrayList<Marketer> parents = new ArrayList<Marketer>();
			ArrayList<Marketer> parentTemp = new ArrayList<Marketer>();
			
			/* 부모 노드가 존재하면 쿼리후 세팅*/
			if (m != null) {
				Marketer option = new Marketer();
				option.setMarketerDegree(m.getMarketerDegree());
				parentTemp = this.marketerMapper.findSortedMarketers(option);
			}
			
			ArrayList<Marketer> nodes = new ArrayList<Marketer>();
			ArrayList<String> genCodeList = CodeGenerator.generatorMarketerCode(code, count);

			for (int i = offset ; i < offset + count; i++) {
				parents.clear();
				for (Marketer  marketer : parentTemp) {
					parents.add(marketer);
				}
				parentTemp.clear();
				for (int j = 0 ; j < Math.pow(2, i+ 1) ; j++) {
					Marketer marketer = new Marketer();
					marketer.setMarketerDegree(i+1);
					marketer.setMarketerCode(genCodeList.get(i - offset) + "_"  + String.format("%08d", j+1));
					marketer.setMarketerStatus("1");
					marketer.setRegAdminNo(2);
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
	public ReturnpBaseResponse deleteMarketer(int marketerNo) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
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
	public ReturnpBaseResponse udpateMarketer(Marketer marketer) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			Marketer option = new Marketer();
			option.setMemberNo(marketer.getMemberNo());
			ArrayList<Marketer> marketerList = this.marketerMapper.findSortedMarketers(option);
			
			if (marketerList.size()> 1) {
				 ResponseUtil.setResponse(res, "19091", this.messageUtils.getMessage("common.error_mulit_marketer_row"));
				 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
				 return res;
			}
			
			if (marketerList.size() ==  1) {
				if (marketer.getMarketerNo() != marketerList.get(0).getMarketerNo()) {
					 ResponseUtil.setResponse(res, "19091", this.messageUtils.getMessage("common.already_existed_marketer"));
					 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					 return res;
				}
			}
			
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
	public ReturnpBaseResponse findMarketerCommands(MarketerCommand command) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			ArrayList<MarketerCommand> commands = this.marketerMapper.findMarketerCommands(command);
			ArrayListResponse<MarketerCommand> ar = new ArrayListResponse<MarketerCommand>();
			ar.setRows(commands);
			ar.setTotal(commands.size());
			ResponseUtil.setResponse(ar, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse findMarketers(Marketer marketer) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			ArrayList<Marketer> markteres = this.marketerMapper.findSortedMarketers(marketer);
			ArrayListResponse<Marketer> ar = new ArrayListResponse<Marketer>();
			ar.setRows(markteres);
			ar.setTotal(markteres.size());
			ResponseUtil.setResponse(ar, "100", this.messageUtils.getMessage("pointback.transaction_completed"));
			return ar;
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, "1001", this.messageUtils.getMessage("pointback.message.inner_server_error"));
			return res;
		}
	}
}
