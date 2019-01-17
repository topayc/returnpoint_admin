package com.returnp.admin.dao.interfaces;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.returnp.admin.dto.command.GreenPointCommand;
import com.returnp.admin.dto.command.MembershipRequestCommand;
import com.returnp.admin.dto.command.PointConversionTransactionCommand;
import com.returnp.admin.dto.command.PointConvertRequestCommand;
import com.returnp.admin.dto.command.RecommenderCommand;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.command.SoleDistCommand;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Admin;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.model.SoleDist;

public interface SearchDao {
	public ArrayList<Policy> findPolicies(Policy policy);
	public ArrayList<Admin> findAdmins(@Param("admin")Admin admin);
	public ArrayList<Member> findMembers(Member member);
	ArrayList<GreenPointCommand> findGreenPointCommands(GreenPointCommand command);
	public ArrayList<GreenPoint> findGreenPoints(GreenPoint cond);
	ArrayList<RedPointCommand> findRedPointCommands(RedPointCommand command);
	
	public ArrayList<RecommenderCommand> findRecommenderCommands(Recommender recommender);
	public Recommender findRecommender(Recommender  recommender);
	public ArrayList<Branch> findBranches(Branch brach);
	public ArrayList<Agency> findAgencies(Agency agency);
	public ArrayList<Affiliate> findAffiliates(Affiliate affiliate);
	public ArrayList<SaleManager> findSaleManagers(SaleManager saleManager);
	public ArrayList<MembershipRequestCommand> findMembershipRequests(SearchCondition nodeSearch);
	public MembershipRequest findMembershipRequest(MembershipRequest  membershipRequest);
	public MembershipRequestCommand findMembershipRequestCommand(MembershipRequestCommand  membershipRequestCommand);
	public ArrayList<CompanyBankAccount> findCompanyBanks(CompanyBankAccount companyBankAccount);
	public ArrayList<PaymentTransaction> findPaymentTransactions(PaymentTransaction record);
	public ArrayList<SoleDist> findSoleDists(SoleDist record);
	public ArrayList<SoleDistCommand> findSoleDistCommands(SoleDistCommand record);
	public ArrayList<MembershipRequestCommand> findMembershipRequestCommands(MembershipRequestCommand mrCond);
	public ArrayList<PointConvertRequestCommand> findPointConvertRequestCommands(PointConvertRequestCommand mrCond);
	public ArrayList<PointConversionTransactionCommand> findPointConversionTransactionCommands(PointConversionTransactionCommand mrCond);
	public ArrayList<PointConversionTransaction> findPointConversionTransactions(PointConversionTransaction mrCond);
}
