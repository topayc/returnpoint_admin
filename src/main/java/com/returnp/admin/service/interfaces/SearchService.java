package com.returnp.admin.service.interfaces;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.dto.command.CategoryCommand;
import com.returnp.admin.dto.command.GreenPointCommand;
import com.returnp.admin.dto.command.MemberBankAccountCommand;
import com.returnp.admin.dto.command.MemberCommand;
import com.returnp.admin.dto.command.MembershipRequestCommand;
import com.returnp.admin.dto.command.PaymentPointbackRecordCommand;
import com.returnp.admin.dto.command.PaymentTransactionCommand;
import com.returnp.admin.dto.command.PointConversionTransactionCommand;
import com.returnp.admin.dto.command.PointConvertRequestCommand;
import com.returnp.admin.dto.command.PointTransferTransactionCommand;
import com.returnp.admin.dto.command.PointWithdrawalCommand;
import com.returnp.admin.dto.command.RecommenderCommand;
import com.returnp.admin.dto.command.RecommenderCommand2;
import com.returnp.admin.dto.command.RedPointCommand;
import com.returnp.admin.dto.command.SaleManagerCommand;
import com.returnp.admin.dto.command.SoleDistCommand;
import com.returnp.admin.model.Admin;
import com.returnp.admin.model.AdminFile;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.Board;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.Category;
import com.returnp.admin.model.Code;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberBankAccount;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.PointWithdrawal;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.model.SoleDist;

@Transactional
public interface SearchService {
	public ArrayList<Policy> findPolicies(Policy policy);

	public ArrayList<Admin> findAdmins(Admin admin);
	
	public ArrayList<Member> findMembers(Member member);
	
	public ArrayList<MemberCommand> findMemberCommands(MemberCommand memberCommand);
	
	ArrayList<GreenPointCommand> findGreenPointCommands(GreenPointCommand command);
	public ArrayList<GreenPoint> findGreenPoints(GreenPoint cond);

	ArrayList<RedPointCommand> findRedPointCommands(RedPointCommand command);
	public ArrayList<RedPoint> findRedPoints(RedPoint command);
	
	public ArrayList<RecommenderCommand> findRecommenderCommands(Recommender recommender);
	
	public ArrayList<RecommenderCommand2> findRecommenderCommand2s(RecommenderCommand2 recommenderCommander2);
	
	public ArrayList<Recommender> findRecommenders(Recommender recommender);
	
	public ArrayList<Branch> findBranches(Branch brach);
	
	public ArrayList<BranchCommand> findBranchCommands(BranchCommand brach);
	
	public ArrayList<Agency> findAgencies(Agency agency);
	
	public ArrayList<AgencyCommand> findAgencyCommands(AgencyCommand agencyCommand);
	
	
	public ArrayList<Affiliate> findAffiliates(Affiliate affiliate);
	
	public ArrayList<AffiliateCommand> findAffiliateCommands(AffiliateCommand affiliateCommand);
	
	public ArrayList<SaleManager> findSaleManagers(SaleManager saleManager);

	public ArrayList<SaleManagerCommand> findSaleManagerCommands(SaleManagerCommand saleManagerCommand);
	
	public ArrayList<MembershipRequestCommand> findMembershipRequestCommands(MembershipRequestCommand mrCond);
	
	public MembershipRequest  findMembershipRequest(MembershipRequest  membershipRequest);
	
	public MembershipRequestCommand findMembershipRequestCommand(MembershipRequestCommand  membershipRequestCommand);
	
	public ArrayList<CompanyBankAccount> findCompanyBanks(CompanyBankAccount companyBankAccount);
	
	public ArrayList<PaymentTransaction> findPaymentTransactions(PaymentTransaction record);

	public ArrayList<PaymentTransactionCommand> findPaymentTransactionCommands(PaymentTransaction record);
	
	public ArrayList<PaymentPointbackRecordCommand> findPaymentPointbackRecordCommands(PaymentPointbackRecordCommand command);
	
	public ArrayList<SoleDist> findSoleDists(SoleDist record);

	public ArrayList<SoleDistCommand> findSoleDistCommands(SoleDistCommand record);
	
	public ArrayList<PointConvertRequestCommand> findPointConvertRequestCommands(PointConvertRequestCommand mrCond);
	
	public ArrayList<PointConversionTransactionCommand> findPointConversionTransactionCommands(PointConversionTransactionCommand mrCond);
	public ArrayList<PointConversionTransaction> findPointConversionTransactions(PointConversionTransaction mrCond);
	
	public ArrayList<CategoryCommand> findCategoryCommands(CategoryCommand mrCond);
	public ArrayList<Category> findCategories(Category mrCond);
	
	public int deleteCategory(CategoryCommand command);
	
	public ArrayList<PointTransferTransactionCommand> findPointTransferTransactionCommands(PointTransferTransactionCommand mrCond);
	
	public ArrayList<Code> findCodes(Code code);

	public ArrayList<Board> findBoards(Board board);
	
	public ArrayList<AdminFile> findAdminFiles(AdminFile adminFile);
	
	public int selectTotalRecords();
	
	public ArrayList<HashMap<String, Object>> selectDirectNodes(HashMap<String, Object> param);
	
	public ArrayList<MemberBankAccount> findMemberBankAccounts(MemberBankAccount memberBankAccount);

	public ArrayList<MemberBankAccountCommand> findMemberBankAccountCommands(MemberBankAccount memberBankAccount);

	public ArrayList<PointWithdrawal> findPointWithdrawals(PointWithdrawal pointWithdrawal);
	
	public ArrayList<PointWithdrawalCommand> findPointWithdrawalCommands(PointWithdrawalCommand pointWithdrawalCommand);
	
	
}