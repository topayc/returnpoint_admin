package com.returnp.admin.service;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.returnp.admin.common.ResponseUtil;
import com.returnp.admin.dao.mapper.ProductMapper;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Product;
import com.returnp.admin.service.interfaces.ProductService;
import com.returnp.admin.utils.FileManager;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired ProductMapper productMapper;

	@Override
	public ReturnpBaseResponse createProduct(Product product, String saveDir, String webPath) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			if (product.getProductImg1().isEmpty() == false ) {
				file = FileManager.saveProductImange(product.getProductImg1(), saveDir);
				product.setProductImgPath1(webPath + "/" + file.getName());
			}
			
			if (product.getProductImg2().isEmpty() == false ) {
				file = FileManager.saveProductImange(product.getProductImg2(), saveDir);
				product.setProductImgPath2(webPath + "/" + file.getName());
			}
			product.setProductCategory("상품권");
			int affectedRow = this.productMapper.insert(product);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품 생성 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 생성 에러");
			return res;
		}
	}
	
	@Override
	public ReturnpBaseResponse updateProduct(Product product, String saveDir, String webPath) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		File file = null;
		try {
			if (product.getProductImg1().isEmpty() == false ) {
				file = FileManager.saveProductImange(product.getProductImg1(), saveDir);
				product.setProductImgPath1(webPath + "/" + file.getName());
			}
			
			if (product.getProductImg2().isEmpty() == false ) {
				file = FileManager.saveProductImange(product.getProductImg2(), saveDir);
				product.setProductImgPath2(webPath + "/" + file.getName());
			}
			product.setProductCategory("상품권");
			int affectedRow = this.productMapper.updateByPrimaryKey(product);
			if (affectedRow != 1) {
				throw new Exception();
			}
			ResponseUtil.setSuccessResponse(res, "100" , "상품 수정 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 수정 에러");
			return res;
		}
	}

	@Override
	public ReturnpBaseResponse deleteProduct(Product product) {
		ReturnpBaseResponse res = new ReturnpBaseResponse();
		try {
			this.productMapper.deleteByPrimaryKey(product.getProductNo());
			ResponseUtil.setResponse(res, "100" , "상품 삭제 완료");
			return res;
		}catch(Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			ResponseUtil.setResponse(res, ResponseUtil.RESPONSE_ERROR, "500", "상품 삭제 에러");
			return res;
		}
	}

}
