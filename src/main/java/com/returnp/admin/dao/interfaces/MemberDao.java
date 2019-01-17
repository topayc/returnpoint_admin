package com.returnp.admin.dao.interfaces;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.returnp.admin.model.Member;

@Repository
public interface MemberDao {
	 int deleteByPrimaryKey(Integer memberNo);

	    int insert(Member record);

	    int insertSelective(Member record);

	    Member selectByPrimaryKey(Integer memberNo);

	    int updateByPrimaryKeySelective(Member record);

	    int updateByPrimaryKey(Member record);
}
