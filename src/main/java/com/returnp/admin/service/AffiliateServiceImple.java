package com.returnp.admin.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.common.ReturnpException;
import com.returnp.admin.dao.mapper.AffiliateMapper;
import com.returnp.admin.dao.mapper.AffiliateTagMapper;
import com.returnp.admin.dao.mapper.GreenPointMapper;
import com.returnp.admin.dao.mapper.QueryMapper;
import com.returnp.admin.dto.command.AffiliateCommand;
import com.returnp.admin.dto.reponse.ObjectResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Affiliate;
import com.returnp.admin.model.AffiliateTag;
import com.returnp.admin.model.GreenPoint;
import com.returnp.admin.model.Member;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.AffiliateService;
import com.returnp.admin.service.interfaces.SearchService;
import com.returnp.admin.web.message.MessageUtils;

@Service
@PropertySource("classpath:/config.properties")
@PropertySource("classpath:/affiliate_sales.properties")
public class AffiliateServiceImple implements AffiliateService {

	@Value("#{properties['affilaite.affiliate_sales_count']}")
	private int affiliateSalesCount;;

	@Value("#{properties['affilaite.affiliate_sales_acc_rate']}")
	private float affilaiteSalesAccRate;
	
	@Value("#{properties['affiliate.affiliate_sales_acc_history_file_path']}")
	private String affiliateSalesAccHistroyFilepPath;

	@Value("#{properties['affiliate.affiliate_sale_suffix']}")
	private String affiliateSaleSuffix;
	
	@Autowired ServletContext context;
	@Autowired AffiliateMapper affiliateMapper;
	@Autowired SearchService searchService;
	@Autowired QueryMapper queryMapper;
	@Autowired GreenPointMapper greenPointMapper;
	@Autowired MessageUtils messageUtils;
	@Autowired AffiliateTagMapper affiliateTagMapper;;

	@Override
	public int deleteByPrimaryKey(Integer affiliateNo) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.deleteByPrimaryKey(affiliateNo);
	}

	@Override
	public int insert(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.insert(record);
	}

	@Override
	public int insertSelective(Affiliate record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Affiliate selectByPrimaryKey(Integer affiliateNo) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.selectByPrimaryKey(affiliateNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Affiliate record) {
		// TODO Auto-generated method stub
		return this.affiliateMapper.updateByPrimaryKey(record);
	}

	@Override
	public AffiliateCommand selectAffiliateCommandByPrimaryKey(Integer affiliateNo) {
		AffiliateCommand command = new AffiliateCommand();
		command.setAffiliateNo(affiliateNo);
		return this.searchService.findAffiliateCommands(command).get(0);
	}

	@Override
	public ReturnpBaseResponse salePontAcc(String salePontTarget, String targetDateStr) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
        
        try {
            return res;
            
        }catch(Exception e) {
            e.printStackTrace();
            return res;
        }
	}

	@Override
	public ReturnpBaseResponse salePontAcc(String targetDateStr) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		/*
		 * 해당 연월에 해당하는 포인트 지급 정보가 있는 지 확인
		 * 해당 지급 정보가 존재한다면, "이미 지급된 연월입니다"라는 메시지를 리턴함 
		 * 
		 * */
	
		try {
			String path = this.context.getRealPath(this.affiliateSalesAccHistroyFilepPath);
			List<String> lines = FileUtils.readLines(new File(path), "UTF-8");
			for (String content : lines) {
				if (targetDateStr.trim().equals(content.trim())) {
					ResponseUtil.setResponse(res, "867", targetDateStr + " 이미 포인트 지급이 완료된 월입니다. </br>확인후 다시 시도해주세요");
					  throw new ReturnpException(res);
				}
			}
			
			if (this.affilaiteSalesAccRate == 0) {
				ResponseUtil.setResponse(res, "895", "영업자에게 지급할 포인트 수수료율이 지정되지 않았습니다. affiliate_sales.properties 파일을 확인하세요.");
				return res;
			}
			
			ArrayList<PaymentTransaction> ptrList;
			ArrayList<Member> memberList;
			ArrayList<GreenPoint> greenPoints = null;

			HashMap<String , Object> dbParams = new HashMap<String, Object>();
			Member member;
			GreenPoint greenPoint;
			int approvalAmount  = 0; 
			int cancelAmount  = 0; 
			float updatePoint = 0.0f;
			String[] affiliateSalesInfos;   
			String line = null;
			Affiliate affiliate = null;
			
			for (int i = 0; i < this.affiliateSalesCount; i++){
	            line = this.messageUtils.getMessage((i+1) + this.affiliateSaleSuffix); 

	            affiliateSalesInfos = line.split(",", 5);
	            int affiliateNo = affiliateSalesInfos[0] == null || affiliateSalesInfos[0].equals("") ? 0 : Integer.parseInt(affiliateSalesInfos[0]);
	            String affiliateName = affiliateSalesInfos[1];
	            String name = affiliateSalesInfos[2].trim();
	            String email = affiliateSalesInfos[3].trim();

	            System.out.println(affiliateSalesInfos.length + ":" + affiliateNo + ":" +affiliateName  + ":" +  name + ":" + email); 
	            System.out.println("");
	             
	             /*필수 항목이 누락되어 있으면 패스*/
	             if (affiliateNo == 0 || name.equals("") || name == null || email == null || email.equals("") || affiliateName == null || affiliateName.equals("")){
	            	 continue;
	             }
	             
	             member = new Member();
	             member.setMemberEmail(email);
	             memberList= this.searchService.findMembers(member);
	            
	             if (memberList.size() !=1 ) {
	            	 ResponseUtil.setResponse(res, "756", email + "[ " + name+" ] 의 회원이 존재하지 않습니다. </br>affiliate_sales.properties 파일을 확인해주세요 : " + affiliateSalesInfos.length);
	            	 throw new ReturnpException(res);
	             }
	             
	             affiliate = new Affiliate();
	             affiliate.setAffiliateNo(affiliateNo);
	             
	             if (this.searchService.findAffiliates(affiliate).size() != 1) {
	            	 ResponseUtil.setResponse(res, "778", "affilaiteNo : " + affiliateNo +  " 의  협력업체가 존재하지 않습니다. </br>affiliate_sales.properties 파일을 확인해주세요 : " + affiliateSalesInfos.length);
	            	 throw new ReturnpException(res);
	             }
	             member = memberList.get(0);

	             dbParams.put("affiliateNo", affiliateNo);
	             dbParams.put("searchMonth", targetDateStr);
	             dbParams.put("paymentApprovalStatus", "1");

	             /*결제 승인 총 금액 */
	             approvalAmount = this.searchService.selectPaymentTransactionSumForSales(dbParams);
	             if (approvalAmount == 0) {
	            	 memberList = null;
	            	 dbParams.clear();
	            	 ptrList = null;
	            	 greenPoints = null;
	            	 approvalAmount  = 0; 
	            	 cancelAmount  = 0; 
	            	 updatePoint = 0.0f;
	            	 affiliate = null;
	            	 continue;
	             }

	             /*결제 취소 총 금액*/
	             dbParams.put("paymentApprovalStatus", "2");
	             cancelAmount =  this.searchService.selectPaymentTransactionSumForSales(dbParams);

	             /*결제 승인 과 취소금액을 감안하여, 최종 입금 G 포인트 */
	             updatePoint =(this.affilaiteSalesAccRate / 100) * (approvalAmount - cancelAmount);

	             greenPoint =  new GreenPoint();
	             greenPoint.setMemberNo(member.getMemberNo());
	             greenPoint.setNodeType( AppConstants.NodeType.MEMBER);

	             greenPoints = this.searchService.findGreenPoints(greenPoint);
	             if (greenPoints.size() != 1) {
	            	 ResponseUtil.setResponse(res, "746", affiliateSalesInfos[0] + " 해당 포인트 정보가 존재하지 않습니다. 관리자에게 문의 바랍니다");
	            	 throw new ReturnpException(res);
	             }

	             greenPoint = greenPoints.get(0); 
	             greenPoint.setPointAmount(greenPoint.getPointAmount() +updatePoint );
	             this.greenPointMapper.updateByPrimaryKey(greenPoint);

	             memberList = null;
	             dbParams.clear();
	             ptrList = null;
	             greenPoints = null;
	             approvalAmount  = 0; 
	             cancelAmount  = 0; 
	             updatePoint = 0.0f;
	             affiliate = null;
	        }
			
			/*
			 * 모든 영업자의 포인트 지급이 완료된 후, 해당 파일에 해당 월의 지급 여부를 기록 
			 */
			
			lines.add(targetDateStr);
			Collections.sort(lines);
			FileUtils.writeLines(new File(path), lines);
			ResponseUtil.setResponse(res, "100", "단말기 영업자 포인트가 지급되었습니다.");
			return res;
		   }catch(ReturnpException e) {
	            e.printStackTrace();
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	            res = e.getBaseResponse();
	            return res;
	        }catch(Exception e) {
	            e.printStackTrace();
	            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
	        	ResponseUtil.setResponse(res, "500", "단말기 영업자 포인트 지급 작업 오류.");
	            return res;
	        }
	}

	@Override
	public ReturnpBaseResponse selectAffiliateTag(AffiliateTag affiliateTag) {
		ObjectResponse<AffiliateTag> res = new ObjectResponse<AffiliateTag>();
        try {
            ArrayList<AffiliateTag> tags = this.searchService.findAffiliateTags(affiliateTag);
            if (tags.size() < 1) {
            	ResponseUtil.setResponse(res, "896", "해당 협력업체는 태그가 등록되어 있지 않습니다. 등록해주시기 바랍니다");
            }else {
            	ResponseUtil.setResponse(res, "100", "처리 완료");
            	res.setData(this.searchService.findAffiliateTags(affiliateTag).get(0));
            }
            return res;
        }catch(Exception e) {
            e.printStackTrace();
            return res;
        }
	}

	@Override
	public ReturnpBaseResponse createAffiliateTag(AffiliateTag affiliateTag) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
        try {
        	 ArrayList<AffiliateTag> tags = this.searchService.findAffiliateTags(affiliateTag);
        	 String message = null;
        	
        	 /* 신규 태그 등록 */
        	 if (tags.size() < 1) {
        		 affiliateTag.setAffiliateTagStatus("Y");
        		 this.affiliateTagMapper.insert(affiliateTag);
        		 message = "태그 신규 등록 완료";
        	 }
        	 /* 기존 태그 업데이트 */
        	 else if (tags.size() == 1) {
        		 affiliateTag.setAffiliateTagNo(tags.get(0).getAffiliateTagNo());
        		 affiliateTag.setAffiliateTagStatus(tags.get(0).getAffiliateTagStatus());
        		 this.affiliateTagMapper.updateByPrimaryKey(affiliateTag);
        		 message = "태그 수정 완료";
        	 }
        	 ResponseUtil.setResponse(res, "100", message);
        	return res;
            
        }catch(Exception e) {
            e.printStackTrace();
            return res;
        }
	}

}
