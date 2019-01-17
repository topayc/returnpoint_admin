package com.returnp.admin.service.interfaces;

import org.springframework.transaction.annotation.Transactional;
import com.returnp.admin.model.Category;

@Transactional
public interface CategoryService {
	int deleteByPrimaryKey(Integer categoryNo);
	
    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer categoryNo);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);
}
