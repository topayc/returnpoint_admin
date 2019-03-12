package com.returnp.admin.web.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.returnp.admin.code.CodeDefine;
import com.returnp.admin.dto.reponse.ArrayListResponse;
import com.returnp.admin.dto.reponse.ReturnpBaseResponse;
import com.returnp.admin.dto.request.SearchCondition;
import com.returnp.admin.model.Product;
import com.returnp.admin.service.interfaces.ProductService;
import com.returnp.admin.service.interfaces.SearchService;

@Controller
@RequestMapping("/api")
@SessionAttributes("productFormInfo")
public class ProductController extends ApplicationController{
	
	@Autowired ProductService productService;
	@Autowired SearchService searchService;
	
	@RequestMapping(value = "/product/form/createForm", method = RequestMethod.GET)
	public String formProductRequest(
			@RequestParam(value = "action", required = true,defaultValue = "create") String action,
			@RequestParam(value = "productNo", defaultValue = "0") int productNo,
			Model model){

		model.addAttribute("productStatusList", CodeDefine.getProductStatusList());
		if (action.equals("create")) {
		
		}else if (action.equals("modify")){
			Product product = new Product();
			product.setProductNo(productNo);
			model.addAttribute("productFormInfo", this.searchService.selectProducts(product).get(0));
		}
		return "template/form/createProduct";
	}
	
	@ResponseBody
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ReturnpBaseResponse selectProducts(
			SearchCondition searchCondition){
		Product product = new Product();
		if (StringUtils.isEmpty(searchCondition.getSearchKeyword())) {
			searchCondition.setSearchKeyword(null);
		}
		product.valueOf(searchCondition);
		ArrayListResponse<Product> res = new ArrayListResponse<Product>();
		ArrayList<Product> products = this.searchService.selectProducts(product);
		res.setRows(products);
		res.setTotal(this.searchService.selectTotalRecords());
		this.setSuccessResponse(res);
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public ReturnpBaseResponse createProduct( Product product, HttpServletRequest request){
		//System.out.println("###### createProduct");
		return this.productService.createProduct(product, request.getSession().getServletContext().getRealPath("/assets/images/products"), "/assets/images/products");
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/update", method = RequestMethod.POST)
	public ReturnpBaseResponse udpateProduct( @ModelAttribute("productFormInfo")  Product product, SessionStatus sessionStatus, BindingResult result, HttpServletRequest request){
		//System.out.println("###### updateProduct");
		ReturnpBaseResponse res = this.productService.updateProduct(product, request.getSession().getServletContext().getRealPath("/assets/images/products"), "/assets/images/products");
		sessionStatus.setComplete();
		return res;
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/delete", method = RequestMethod.POST)
	public ReturnpBaseResponse deleteProduct( Product product){
		//System.out.println("###### deleteProduct");
		return this.productService.deleteProduct(product);
	}
}
