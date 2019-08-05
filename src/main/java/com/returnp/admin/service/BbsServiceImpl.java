package com.returnp.admin.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.MainBbsMapper;
import com.returnp.admin.dao.mapper.SubBbsMapper;
import com.returnp.admin.dto.AdminSession;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.MainBbs;
import com.returnp.admin.model.SubBbs;
import com.returnp.admin.service.interfaces.BbsService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class BbsServiceImpl implements BbsService {
	@Autowired SearchService searchService;
	@Autowired MainBbsMapper mainBbsMapper;;
	@Autowired SubBbsMapper subBbsMapper;;
	
	@Override
	public ReturnpBaseResponse  selectMainBbses(MainBbs mainBbs) {
		ArrayListResponse<MainBbs>  res = new ArrayListResponse<MainBbs>();
		try {
			ArrayList<MainBbs> mainBbsList= this.searchService.selectMainBbses(mainBbs);
			res.setRows(mainBbsList);
			res.setTotal(mainBbsList.size());
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse selectSubBbses(SubBbs subBbs) {
		ArrayListResponse<SubBbs>  res = new ArrayListResponse<SubBbs>();
		try {
			ArrayList<SubBbs> subBbsList= this.searchService.selectSubBbses(subBbs);
			res.setRows(subBbsList);
			res.setTotal(subBbsList.size());
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "성공");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse createMainBbs(MainBbs bbs, HttpSession session) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			AdminSession adminSession = (AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION);
			if (adminSession == null) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "4000", "어드민 세션이 없습니다. 로그인을 하신 후 시도해주세요");
				throw new ReturnpException(res); 
			}
			bbs.setWriterNo(adminSession.getAdmin().getAdminNo());
			bbs.setWriterNo(adminSession.getAdmin().getAdminNo());
			bbs.setRerv6(adminSession.getAdmin().getAdminEmail());
			bbs.setRerv2(adminSession.getAdmin().getAdminName());
			bbs.setStatus(AppConstants.BbsStatus.SHOWN);
			if (this.mainBbsMapper.insert(bbs) < 1) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "3101", "Article 생성 실패");
				throw new ReturnpException(res); 
			}
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "Article 생성 성공");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        res = e.getBaseResponse();
	        return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse createSubBbs(SubBbs bbs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnpBaseResponse updateMainBbs(MainBbs bbs, HttpSession session) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			AdminSession adminSession = (AdminSession)session.getAttribute(AppConstants.ADMIN_SESSION);
			if (adminSession == null) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "4000", "어드민 세션이 없습니다. 로그인을 하신 후 시도해주세요");
				throw new ReturnpException(res); 
			}
			
			MainBbs mainBbs = this.mainBbsMapper.selectByPrimaryKey(bbs.getMainBbsNo());
			if (mainBbs == null ) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "4310", "mainBbsNo 에 해당하는 글이 없습니다. 확인후 다시 시도해주세요");
				throw new ReturnpException(res); 
			}
			
			mainBbs.setWriterNo(adminSession.getAdmin().getAdminNo());
			mainBbs.setWriterNo(adminSession.getAdmin().getAdminNo());
			mainBbs.setRerv6(adminSession.getAdmin().getAdminEmail());
			mainBbs.setRerv2(adminSession.getAdmin().getAdminName());
			mainBbs.setContent(bbs.getContent());
			mainBbs.setBbsType2(bbs.getBbsType2());
			mainBbs.setTitle(bbs.getTitle());
			
			if (this.mainBbsMapper.updateByPrimaryKeyWithBLOBs(mainBbs) < 1) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "3101", "Article 수정 실패");
				throw new ReturnpException(res); 
			}
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "Article 수정 성공");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        res = e.getBaseResponse();
	        return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse updateSubBbs(SubBbs bbs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnpBaseResponse removeMainBbs(MainBbs bbs) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			
			if (this.mainBbsMapper.deleteByPrimaryKey(bbs.getMainBbsNo()) < 1) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "3101", "Article 삭제 실패");
				throw new ReturnpException(res); 
			}
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "Article 삭제 성공");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        res = e.getBaseResponse();
	        return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse removeSubBbs(SubBbs bbs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReturnpBaseResponse reply(SubBbs subBbsCommand, HttpSession httpSession) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			AdminSession adminSession = (AdminSession)httpSession.getAttribute(AppConstants.ADMIN_SESSION);
			if (adminSession == null) {
				ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "4000", "어드민 세션이 없습니다. 로그인을 하신 후 시도해주세요");
				throw new ReturnpException(res); 
			}
			
			subBbsCommand.setWriterNo(adminSession.getAdmin().getAdminNo());
			
			SubBbs subBbs= new SubBbs();
			subBbs.setMainBbsNo(subBbsCommand.getMainBbsNo());
			ArrayList<SubBbs> subBbsList = this.searchService.selectSubBbses(subBbs);
			
			if (subBbsList.size() == 0) {
				/* SubBbs insert*/	
				if (this.subBbsMapper.insert(subBbsCommand) < 1) {
					ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "3101", "답변 달기 실패");
					throw new ReturnpException(res); 
				}
				MainBbs mainBbs = new MainBbs();
				mainBbs.setMainBbsNo(subBbsCommand.getMainBbsNo());
				ArrayList<MainBbs> mainBbsList = this.searchService.selectMainBbses(mainBbs);
				mainBbs = mainBbsList.get(0);
				mainBbs.setReplyCompleted("Y");
				this.mainBbsMapper.updateByPrimaryKeySelective(mainBbs);
			}else if (subBbsList.size() == 1){
				/*SubBbs update*/
				subBbs = subBbsList.get(0);
				subBbs.setContent(subBbsCommand.getContent());
				if (this.subBbsMapper.updateByPrimaryKeyWithBLOBs(subBbs) < 1) {
					ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "3101", "답변 달기 실패");
					throw new ReturnpException(res); 
				}
			}
			
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_OK, "100", "답변 달기  성공");
			return res;
		}catch(ReturnpException e) {
			e.printStackTrace();
	        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        res = e.getBaseResponse();
	        return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res,ResponseUtil.RESPONSE_ERROR, "500", "Server Error");
			return res;
		}
	}

}
