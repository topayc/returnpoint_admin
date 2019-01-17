package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.NodeFileAttach;

public interface NodeFileAttachMapper {
    int deleteByPrimaryKey(Integer nodeFileAttachNo);

    int insert(NodeFileAttach record);

    int insertSelective(NodeFileAttach record);

    NodeFileAttach selectByPrimaryKey(Integer nodeFileAttachNo);

    int updateByPrimaryKeySelective(NodeFileAttach record);

    int updateByPrimaryKey(NodeFileAttach record);
}