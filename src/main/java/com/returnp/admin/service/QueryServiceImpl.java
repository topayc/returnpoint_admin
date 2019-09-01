package com.returnp.admin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.GreenPointMapper;
import com.returnp.admin.dao.mapper.QueryMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.AffiliateTid;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.MemberBankAccount;
import com.returnp.admin.service.interfaces.QueryService;
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class QueryServiceImpl implements QueryService {

	@Value("#{properties['affilaite.affiliate_sales_count']}")
	private int affiliateSalesCount;;

	@Value("#{properties['affilaite.affiliate_sales_acc_rate']}")
	private float affilaiteSalesAccRate;
	
	@Value("#{properties['affiliate.affiliate_sales_acc_history_file_path']}")
	private String affiliateSalesAccHistroyFilepPath;

	@Autowired ServletContext context;;
	@Autowired QueryMapper queryMapper;
	@Autowired SearchService searchService;
	@Autowired GreenPointMapper greenPointMapper;
	
	@Override
	public int deleteAffiliateTid(AffiliateTid affiliateTid) {
		return this.queryMapper.deleteAffiliateTid(affiliateTid);
	}
	@Override
	public int deleteGPoint(GreenPoint gPoint) {
		return this.queryMapper.deleteGPoint(gPoint);
	}
	@Override
	public int updateMemberBankAccount(MemberBankAccount account) {
		return this.queryMapper.updateMemberBankAccount(account);
	}
}
