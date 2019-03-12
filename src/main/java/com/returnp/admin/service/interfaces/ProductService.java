package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.model.Product;

@Transactional
public interface ProductService {
	public ReturnpBaseResponse createProduct(Product product, String saveDir, String webPath);
	public ReturnpBaseResponse deleteProduct(Product product);
	public ReturnpBaseResponse updateProduct(Product product, String saveDir, String webPath);
	
}
