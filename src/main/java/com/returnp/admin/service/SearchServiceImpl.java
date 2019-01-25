package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.SearchMapper;
import com.returnp.admin.dto.QueryCondition;
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
import com.returnp.admin.service.interfaces.SearchService;

@Service
public class SearchServiceImpl implements SearchService{

	@Autowired SearchMapper searchMapper;

	@Override
	public ArrayList<Member> findMembers(Member member) {
		return this.searchMapper.findMembers(member);
	}

	@Override
	public ArrayList<GreenPointCommand> findGreenPointCommands(GreenPointCommand command) {
		// TODO Auto-generated method stub
		return this.searchMapper.findGreenPointCommands(command);
	}
	
	@Override
	public ArrayList<GreenPoint> findGreenPoints(GreenPoint cond) {
		return this.searchMapper.findGreenPoints(cond);
	}
	
	@Override
	public ArrayList<RedPointCommand> findRedPointCommands(RedPointCommand command) {
		// TODO Auto-generated method stub
		return this.searchMapper.findRedPointCommands(command);
	}
	
	@Override
	public ArrayList<RedPoint> findRedPoints(RedPoint command) {
		// TODO Auto-generated method stub
		return this.searchMapper.findRedPoints(command);
	}
	
	@Override
	public ArrayList<RecommenderCommand> findRecommenderCommands(Recommender recommender) {
		return this.searchMapper.findRecommenderCommands(recommender);
	}

	@Override
	public ArrayList<Branch> findBranches(Branch brach) {
		return this.searchMapper.findBranches(brach);
	}

	@Override
	public ArrayList<Agency> findAgencies(Agency agency) {
		return this.searchMapper.findAgencies(agency);
	}

	@Override
	public ArrayList<Affiliate> findAffiliates(Affiliate affiliate) {
		return this.searchMapper.findAffiliates(affiliate);
	}

	@Override
	public ArrayList<SaleManager> findSaleManagers(SaleManager saleManager) {
		return this.searchMapper.findSaleManagers(saleManager);
	}

	@Override
	public ArrayList<Policy> findPolicies(Policy policy) {
		return this.searchMapper.findPolicies(policy);
	}

	@Override
	public ArrayList<Admin> findAdmins(Admin admin) {
		return this.searchMapper.findAdmins(admin);
	}

	@Override
	public ArrayList<CompanyBankAccount> findCompanyBanks(CompanyBankAccount companyBankAccount) {
		return this.searchMapper.findCompanyBanks(companyBankAccount);
	}

	@Override
	public ArrayList<Recommender> findRecommenders(Recommender recommender) {
		return this.searchMapper.findRecommenders(recommender);
	}

	@Override
	public MembershipRequest findMembershipRequest(MembershipRequest membershipRequest) {
		// TODO Auto-generated method stub
		return this.searchMapper.findMembershipRequest(membershipRequest);
	}

	@Override
	public ArrayList<PaymentTransaction> findPaymentTransactions(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return this.searchMapper.findPaymentTransactions(record);
	}
	
	@Override
	public ArrayList<PaymentTransactionCommand> findPaymentTransactionCommands(PaymentTransaction record) {
		// TODO Auto-generated method stub
		return this.searchMapper.findPaymentTransactionCommands(record);
	}

	@Override
	public ArrayList<SoleDist> findSoleDists(SoleDist record) {
		// TODO Auto-generated method stub
		return this.searchMapper.findSoleDists(record);
	}

	@Override
	public ArrayList<MembershipRequestCommand> findMembershipRequestCommands(MembershipRequestCommand mrCond) {
		// TODO Auto-generated method stub
		return this.searchMapper.findMembershipRequestCommands(mrCond);
	}

	@Override
	public ArrayList<PointConvertRequestCommand> findPointConvertRequestCommands(PointConvertRequestCommand mrCond) {
		// TODO Auto-generated method stub
		return this.searchMapper.findPointConvertRequestCommands(mrCond);
	}

	@Override
	public ArrayList<PointConversionTransactionCommand> findPointConversionTransactionCommands(
			PointConversionTransactionCommand mrCond) {
		return this.searchMapper.findPointConversionTransactionCommands(mrCond);
	}

	@Override
	public ArrayList<PointConversionTransaction> findPointConversionTransactions(PointConversionTransaction mrCond) {
		return this.searchMapper.findPointConversionTransactions(mrCond);
	}

	@Override
	public MembershipRequestCommand findMembershipRequestCommand(MembershipRequestCommand membershipRequestCommand) {
		return this.searchMapper.findMembershipRequestCommand(membershipRequestCommand);
	}


	@Override
	public ArrayList<SoleDistCommand> findSoleDistCommands(SoleDistCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.findSoleDistCommands(record);
	}

	@Override
	public ArrayList<BranchCommand> findBranchCommands(BranchCommand brach) {
		// TODO Auto-generated method stub
		return this.searchMapper.findBranchCommands(brach);
	}

	@Override
	public ArrayList<AgencyCommand> findAgencyCommands(AgencyCommand agencyCommand) {
		// TODO Auto-generated method stub
		return this.searchMapper.findAgencyCommands(agencyCommand);
	}

	@Override
	public ArrayList<AffiliateCommand> findAffiliateCommands(AffiliateCommand affiliateCommand) {
		// TODO Auto-generated method stub
		return this.searchMapper.findAffiliateCommands(affiliateCommand);
	}

	@Override
	public ArrayList<SaleManagerCommand> findSaleManagerCommands(SaleManagerCommand saleManagerCommand) {
		// TODO Auto-generated method stub
		return this.searchMapper.findSaleManagerCommands(saleManagerCommand);
	}

	@Override
	public ArrayList<MemberCommand> findMemberCommands(MemberCommand memberCommand) {
		// TODO Auto-generated method stub
		return this.searchMapper.findMemberCommands(memberCommand);
	}

	@Override
	public ArrayList<RecommenderCommand2> findRecommenderCommand2s(RecommenderCommand2 recommenderCommander2) {
		// TODO Auto-generated method stub
		return this.searchMapper.findRecommenderCommand2s(recommenderCommander2);
	}

	@Override
	public ArrayList<PaymentPointbackRecordCommand> findPaymentPointbackRecordCommands(
			PaymentPointbackRecordCommand command) {
		// TODO Auto-generated method stub
		return this.searchMapper.findPaymentPointbackRecordCommands(command);
	}

	@Override
	public ArrayList<CategoryCommand> findCategoryCommands(CategoryCommand mrCond) {
		// TODO Auto-generated method stub
		return this.searchMapper.findCategoryCommands(mrCond);
	}

	@Override
	public int deleteCategory(CategoryCommand command) {
		// TODO Auto-generated method stub
		return this.searchMapper.deleteCategory(command);
	}

	@Override
	public ArrayList<Category> findCategories(Category mrCond) {
		// TODO Auto-generated method stub
		return this.searchMapper.findCategories(mrCond);
	}

	@Override
	public ArrayList<PointTransferTransactionCommand> findPointTransferTransactionCommands(
			PointTransferTransactionCommand mrCond) {
		return this.searchMapper.findPointTransferTransactionCommands(mrCond);
	}
	
	@Override
	public ArrayList<Code> findCodes(Code code) {
		return this.searchMapper.findCodes(code);
	}

	@Override
	public ArrayList<Board> findBoards(Board board) {
		return this.searchMapper.findBoards(board);
	}
	
	@Override
	public int selectTotalRecords() {
		return this.searchMapper.selectTotalRecords();
	}

	@Override
	public ArrayList<AdminFile> findAdminFiles(AdminFile adminFile) {
		return this.searchMapper.findAdminFiles(adminFile);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectDirectNodes(HashMap<String, Object> param) {
		return this.searchMapper.selectDirectNodes(param);
	}

	@Override
	public ArrayList<MemberBankAccount> findMemberBankAccounts(MemberBankAccount memberBankAccount) {
		return this.searchMapper.findMemberBankAccounts(memberBankAccount);
	}

	@Override
	public ArrayList<MemberBankAccountCommand> findMemberBankAccountCommands(MemberBankAccount memberBankAccount) {
		return this.searchMapper.findMemberBankAccountCommands(memberBankAccount);
	}

	@Override
	public ArrayList<PointWithdrawal> findPointWithdrawals(PointWithdrawal pointWithdrawal) {
		return this.searchMapper.findPointWithdrawals(pointWithdrawal);
	}

	@Override
	public ArrayList<PointWithdrawalCommand> findPointWithdrawalCommands( PointWithdrawalCommand pointWithdrawalCommand) {
		return this.searchMapper.findPointWithdrawalCommands(pointWithdrawalCommand);
	}

	
}