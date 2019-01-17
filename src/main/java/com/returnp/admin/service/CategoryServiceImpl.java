package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.CategoryMapper;
import com.returnp.admin.model.Category;
import com.returnp.admin.service.interfaces.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired CategoryMapper categoryMapper;

	@Override
	public int deleteByPrimaryKey(Integer categoryNo) {
		// TODO Auto-generated method stub
		return this.categoryMapper.deleteByPrimaryKey(categoryNo);
	}

	@Override
	public int insert(Category record) {
		// TODO Auto-generated method stub
		return this.categoryMapper.insert(record);
	}

	@Override
	public int insertSelective(Category record) {
		// TODO Auto-generated method stub
		return this.categoryMapper.insertSelective(record);
	}

	@Override
	public Category selectByPrimaryKey(Integer categoryNo) {
		// TODO Auto-generated method stub
		return this.categoryMapper.selectByPrimaryKey(categoryNo);
	}

	@Override
	public int updateByPrimaryKeySelective(Category record) {
		// TODO Auto-generated method stub
		return this.categoryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Category record) {
		// TODO Auto-generated method stub
		return this.categoryMapper.updateByPrimaryKey(record);
	}

}
