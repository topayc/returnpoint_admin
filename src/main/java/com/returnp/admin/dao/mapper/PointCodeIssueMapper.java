package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.PointCodeIssue;

public interface PointCodeIssueMapper {
    int deleteByPrimaryKey(Integer pointCodeIssueNo);

    int insert(PointCodeIssue record);

    int insertSelective(PointCodeIssue record);

    PointCodeIssue selectByPrimaryKey(Integer pointCodeIssueNo);

    int updateByPrimaryKeySelective(PointCodeIssue record);

    int updateByPrimaryKey(PointCodeIssue record);
}