package com.returnp.admin.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.SearchMapper;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.command.AffiliateTidCommand;
import com.returnp.admin.dto.command.AgencyCommand;
import com.returnp.admin.dto.command.BranchCommand;
import com.returnp.admin.dto.command.CategoryCommand;
import com.returnp.admin.dto.command.GiftCardAccHistoryCommand;
import com.returnp.admin.dto.command.GiftCardIssueCommand;
import com.returnp.admin.dto.command.GiftCardOrderCommand;
import com.returnp.admin.dto.command.GiftCardOrderItemCommand;
import com.returnp.admin.dto.command.GiftCardPaymentCommand;
import com.returnp.admin.dto.command.GiftCardSalesOrganCommand;
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
import com.returnp.admin.model.AffiliateCategory;
import com.returnp.admin.model.AffiliateCiderpay;
import com.returnp.admin.model.AffiliateDetail;
import com.returnp.admin.model.AffiliatePaymentRouter;
import com.returnp.admin.model.Agency;
import com.returnp.admin.model.Board;
import com.returnp.admin.model.Branch;
import com.returnp.admin.model.Category;
import com.returnp.admin.model.Code;
import com.returnp.admin.model.CompanyBankAccount;
import com.returnp.admin.model.GiftCard;
import com.returnp.admin.model.GiftCardAccHistory;
import com.returnp.admin.model.GiftCardSalesOrgan;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.MainBbs;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.MemberAddress;
import com.returnp.admin.model.MemberBankAccount;
import com.returnp.admin.model.MemberConfig;
import com.returnp.admin.model.MemberPlainPassword;
import com.returnp.admin.model.MembershipRequest;
import com.returnp.admin.model.PaymentRouter;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.model.PointConversionTransaction;
import com.returnp.admin.model.PointCouponTransaction;
import com.returnp.admin.model.PointWithdrawal;
import com.returnp.admin.model.Policy;
import com.returnp.admin.model.Recommender;
import com.returnp.admin.model.RedPoint;
import com.returnp.admin.model.SaleManager;
import com.returnp.admin.model.SoleDist;
import com.returnp.admin.model.SubBbs;
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
	public ArrayList<PaymentTransactionCommand> findPaymentTransactionCommands(PaymentTransactionCommand record) {
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

	@Override
	public int selectMemberCount() {
		return this.searchMapper.selectMemberCount();
	}

	@Override
	public ArrayList<MemberCommand> findMyMemberCommands(MemberCommand memberCommand) {
		// TODO Auto-generated method stub
		return this.searchMapper.findMyMemberCommands(memberCommand);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectMyTotalPointInfo(MemberCommand memberCommand) {
		return this.searchMapper.selectMyTotalPointInfo(memberCommand);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectAffiliteSaleReport() {
		return this.searchMapper.selectAffiliteSaleReport();
	}
	
	@Override
	public ArrayList<PaymentTransactionCommand> selectOverlapPaymentTransactionCommands(PaymentTransactionCommand record){
		return this.searchMapper.SelectOverlapPaymentTransactionCommands(record);
	}

	@Override
	public ArrayList<GiftCard> selectGiftCards(GiftCard giftCard) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCards(giftCard);
	}

	@Override
	public ArrayList<GiftCardSalesOrgan> selectGiftCardSalesOrgans(GiftCardSalesOrgan record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardSalesOrgans(record);
	}

	@Override
	public ArrayList<GiftCardSalesOrganCommand> selectGiftCardSalesOrganCommands(GiftCardSalesOrganCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardSalesOrganCommands(record);
	}

	@Override
	public ArrayList<GiftCardOrderCommand> selectGiftCardOrderCommands(GiftCardOrderCommand record) {
		return this.searchMapper.selectGiftCardOrderCommands(record);
	}

	@Override
	public ArrayList<GiftCardOrderItemCommand> selectGiftCardOrderItemCommands(GiftCardOrderItemCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardOrderItemCommands(record);
	}

	@Override
	public ArrayList<GiftCardIssueCommand> selectGiftCardIssueCommands(GiftCardIssueCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardIssueCommands(record);
	}

	@Override
	public ArrayList<GiftCardPaymentCommand> selectGiftCardPaymentCommands(GiftCardPaymentCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardPaymentCommands(record);
	}

	@Override
	public ArrayList<GiftCardAccHistory> selectGiftCardAccHistories(GiftCardAccHistory record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardAccHistories(record);
	}

	@Override
	public ArrayList<GiftCardAccHistoryCommand> selectGiftCardAccHistoryCommands(GiftCardAccHistoryCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardAccHistoryCommands(record);
	}

	@Override
	public ArrayList<MemberAddress> selectMemberAddresses(MemberAddress record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectMemberAddresses(record);
	}

	@Override
	public ArrayList<AffiliateTidCommand> selectAffilaiteTidCommands(AffiliateTidCommand record) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectAffilaiteTidCommands(record);
	}

	@Override
	public GiftCardSalesOrganCommand selectGiftCardSalesOrganCommand(GiftCardSalesOrganCommand organ) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGiftCardSalesOrganCommand(organ);
	}

	@Override
	public ArrayList<MemberConfig> selectMemberConfigs(MemberConfig memberConfig) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectMemberConfigs(memberConfig);
	}

	@Override
	public ArrayList<PaymentRouter> selectPaymentRouters(PaymentRouter paymentRouter) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectPaymentRouters(paymentRouter);
	}

	@Override
	public ArrayList<AffiliatePaymentRouter> selectAffiliatePaymentRouters(AffiliatePaymentRouter affiliatePaymentRouter) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectAffiliatePaymentRouters(affiliatePaymentRouter);
	}

	@Override
	public ArrayList<AffiliateCiderpay> selectAffiliateCiderPays(AffiliateCiderpay affiliateCiderpay) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectAffiliateCiderPays(affiliateCiderpay);
	}

	@Override
	public ArrayList<MemberPlainPassword> selectMemberPlainPasswords(MemberPlainPassword memberPlainPassword) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectMemberPlainPasswords(memberPlainPassword);
	}

	@Override
	public ArrayList<AffiliateDetail> selectAffiliateDetails(AffiliateDetail affiliateDetail) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectAffiliateDetails(affiliateDetail);
	}

	@Override
	public int selectPaymentTransactionSumForSales(HashMap<String, Object> params) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectPaymentTransactionSumForSales(params);
	}

	@Override
	public ArrayList<MainBbs> selectMainBbses(MainBbs mainBbs) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectMainBbses(mainBbs);
	}

	@Override
	public ArrayList<SubBbs> selectSubBbses(SubBbs subBbs) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectSubBbses(subBbs);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectGpointPayments(HashMap<String, Object> dbParams) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectGpointPayments(dbParams);
	}

	@Override
	public ArrayList<AffiliateCategory> selectAffiliateCategories(AffiliateCategory affiliateCategory) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectAffiliateCategories(affiliateCategory);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectPointCouponPointbackRecords(HashMap<String, Object> dbParams) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectPointCouponPointbackRecords(dbParams);
	}

	@Override
	public ArrayList<HashMap<String, Object>> selectPointCouponTransactions(HashMap<String, Object> dbParams) {
		// TODO Auto-generated method stub
		return this.searchMapper.selectPointCouponTransactions(dbParams);
	};
}
