package com.returnp.admin.dao.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Param;

import com.returnp.admin.dto.command.AffiliateTidCommand;
import com.returnp.admin.dto.command.GreenPointCommand;
import com.returnp.admin.dto.command.InnerPointBackTarget;
import com.returnp.admin.dto.command.MembershipRequestCommand;
import com.returnp.admin.dto.command.OuterPointBackTarget;
import com.returnp.admin.dto.command.PaymentPointbackRecordCommand;
import com.returnp.admin.dto.command.PointBackTarget;
import com.returnp.admin.dto.command.PointConversionTransactionCommand;
import com.returnp.admin.dto.command.PointConvertRequestCommand;
import com.returnp.admin.dto.command.RecommenderCommand;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Admin;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.PaymentPointbackRecord;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.model.SoleDist;

public interface PointBackMapper {
	public ArrayList<Policy> findPolicies(Policy cond);
	
	public PointBackTarget findInnerPointBackFindTarget(PointBackTarget target);
	public OuterPointBackTarget findOuterPointBackTarget(OuterPointBackTarget target);
	public InnerPointBackTarget findInnerPointBackTarget(@Param("affiliateSerial") String affiliateSerial);
	
	public ArrayList<Admin> findAdmins(Admin admin);
	public ArrayList<Member> findMembers(Member member);
	public ArrayList<GreenPointCommand> findGreenPointCommands(GreenPointCommand cond);
	public ArrayList<GreenPoint> findGreenPoints(GreenPoint cond);
	public ArrayList<RedPointCommand> findGreenPointCommands(RedPointCommand cond);
	public ArrayList<RedPointCommand> findRedPointCommands(RedPointCommand command);
	
	public ArrayList<RecommenderCommand> findRecommenderCommands(Recommender recommender);
	public Recommender findRecommender(Recommender recommender);
	public ArrayList<Branch> findBranches(Branch brach);
	public ArrayList<Agency> findAgencies(Agency agency);
	public ArrayList<Affiliate> findAffiliates(Affiliate affiliate);
	public ArrayList<SaleManager> findSaleManagers(SaleManager saleManager);
	public ArrayList<MembershipRequestCommand> findMembershipRequests(SearchCondition  nodeSearch);
	public MembershipRequest findMembershipRequest(MembershipRequest  membershipRequest);
	public MembershipRequestCommand findMembershipRequestCommand(MembershipRequestCommand  membershipRequestCommand);
	public ArrayList<CompanyBankAccount> findCompanyBanks(CompanyBankAccount companyBankAccount);
	public ArrayList<PaymentTransaction> findPaymentTransactions(PaymentTransaction record);
	public ArrayList<SoleDist> findSoleDists(SoleDist record);
	public ArrayList<MembershipRequestCommand> findMembershipRequestCommands(MembershipRequestCommand mrCond);

	public ArrayList<PaymentPointbackRecord> findPaymentPointbackRecords(PaymentPointbackRecord paymentPointbackRecord);
	public ArrayList<PaymentPointbackRecordCommand> findPaymentPointbackRecordCommands(PaymentPointbackRecordCommand paymentPointbackRecordCommand);

	public ArrayList<PointConvertRequestCommand> findPointConvertRequestCommands(PointConvertRequestCommand mrCond);
	public ArrayList<PointConversionTransactionCommand> findPointConversionTransactionCommands(PointConversionTransactionCommand mrCond);
	public ArrayList<PointConversionTransaction> findPointConversionTransactions(PointConversionTransaction mrCond);
	public ArrayList<HashMap<String, Object>> selectDirectNodes(HashMap<String, Object> param);
	public ArrayList<AffiliateTidCommand> selectAffilaiteTidCommands(AffiliateTidCommand record);
}
