package com.returnp.admin.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.concurrent.Executor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.returnp.admin.common.AppConstants;
import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dto.reponse.BaseResponse;
import com.returnp.admin.model.PaymentTransaction;
import com.returnp.admin.service.interfaces.ExecutorService;

@Service
@PropertySource("classpath:/config.properties")
public class ExecutorServiceImpl implements ExecutorService{
	@Autowired Executor executor;
	@Autowired Environment environment;
	
	@Override
	public void executePaymenTransactionPointback(final String command, final int no ) {
		if (environment.getProperty("pointback_async_call","Y").equals("Y")) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					ExecutorServiceImpl.this.callPointbackProgress(command, no);
				}
			});
		}else {
			callPointbackProgress(command,no);
		}
	}
	
	public void callPointbackProgress(final  String cmd, final int no) {
		String remoteCallURL = environment.getProperty("run_mode").equals("dev") ?  
				environment.getProperty("dev.pointback_remote_url_by_admin") : environment.getProperty("real.pointback_remote_url_by_admin");
		String key = environment.getProperty("key");
	
		try {
			URL url = new URL(remoteCallURL + "?paymentTransactionNo=" + no + "&cmd=" + cmd + "&key=" + key);
			System.out.println(remoteCallURL + "?paymentTransactionNo=" + no + "&cmd=" + cmd + "&key=" + key);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoInput(true); 
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader in = null;
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader( new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				System.out.println("Response Message : " + response.toString());
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BaseResponse accumulateRequest(PaymentTransaction transaction) {
		String remoteCallURL = environment.getProperty("run_mode").equals("dev") ?  
				environment.getProperty("dev.manual_accumulate_point") : environment.getProperty("real.manual_accumulate_point");
				String key = environment.getProperty("key");
		BaseResponse res = null;
		String urlData;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String pas  = transaction.getPaymentApprovalStatus().equals(AppConstants.PaymentApprovalStatus.PAYMENT_APPROVAL_OK) ? 
				"0" : 
				(transaction.getPaymentApprovalStatus().equals(AppConstants.PaymentApprovalStatus.PAYMENT_APPROVAL_CANCEL) ? 
					"1": AppConstants.PaymentApprovalStatus.PAYMENT_APPROVAL_ERROR) ;

			String[] paramArr = {
				"pam=" + transaction.getPaymentApprovalAmount(),	
				"pas=" + URLEncoder.encode(pas,"UTF-8"),
				"pat=" + URLEncoder.encode(df.format(transaction.getPaymentApprovalDateTime()),"UTF-8"),
				"pan=" + URLEncoder.encode(transaction.getPaymentApprovalNumber(),"UTF-8"),	
				"af_id=" + URLEncoder.encode(transaction.getAffiliateSerial(),"UTF-8"),
				"phoneNumber=" + URLEncoder.encode(transaction.getMemberPhone(),"UTF-8"),	
				"memberEmail=" + URLEncoder.encode(transaction.getMemberEmail(),"UTF-8"),	
				"key=" + URLEncoder.encode(key,"UTF-8")	
			};
			urlData = remoteCallURL + "?" + org.apache.commons.lang3.StringUtils.join(paramArr, "&");
			return this.sendRequest(urlData);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			res = new BaseResponse();
			ResponseUtil.setResponse(res, "20023", "인코딩 오류 발생");
		}
		return null;
	}

	
	@Override
	public BaseResponse cancelAccumulateRequest(Integer paymentTransactionNo) {
		String remoteCallURL = environment.getProperty("run_mode").equals("dev") ?  
				environment.getProperty("dev.cancel_acc_by_payment_transaction_no") : environment.getProperty("real.cancel_acc_by_payment_transaction_no");
				String key = environment.getProperty("key");
		String url = remoteCallURL + "?paymentTransactionNo=" + paymentTransactionNo + "&key=" + key;
		return this.sendRequest(url);
	}

	@Override
	public BaseResponse sendRequest(String urlData) {
		BaseResponse res = null;
		try {
			URL url = new URL(urlData);
			System.out.println("###### 적립 요청 파라메터 구성");
			System.out.println(urlData);
			
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setDoInput(true); 
			con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			con.setRequestMethod("GET");
			
			int responseCode = con.getResponseCode();
			BufferedReader in = null;
			System.out.println(responseCode);
			
			if (responseCode == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader( new InputStreamReader(con.getInputStream()));
				String inputLine;
				StringBuffer response = new StringBuffer();
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				
				in.close();
				System.out.println("###### 적립 요청 후 Response Message : " + response.toString());
				
				Gson gson = new Gson();
				res = gson.fromJson(response.toString(), BaseResponse.class);
				return res;
			}else {
				res = new BaseResponse();
				ResponseUtil.setResponse(res, "50000", "네트워크 에러");
				return res;
			}
		} catch (MalformedURLException e) {
			res = new BaseResponse();
			ResponseUtil.setResponse(res, "50000", "네트워크 에러");
			e.printStackTrace();
			return res;
		} catch (IOException e) {
			res = new BaseResponse();
			ResponseUtil.setResponse(res, "50000", "네트워크 에러");
			e.printStackTrace();
			return res;
		} catch (Exception e2) {
			res = new BaseResponse();
			ResponseUtil.setResponse(res, "50000", "네트워크 에러");
			e2.printStackTrace();
			return res;
		}
	}
}