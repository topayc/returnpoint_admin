package com.returnp.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.NodeFileAttachMapper;
import com.returnp.admin.model.NodeFileAttach;
import com.returnp.admin.service.interfaces.NodeFileAttachService;

@Service
public class NodeFileAttachServiceImpl implements NodeFileAttachService {

	@Autowired NodeFileAttachMapper nodeFileAttachMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer fileAttachNo) {
		return this.nodeFileAttachMapper.deleteByPrimaryKey(fileAttachNo);
	}

	@Override
	public int insert(NodeFileAttach record) {
		return this.nodeFileAttachMapper.insert(record);
	}

	@Override
	public int insertSelective(NodeFileAttach record) {
		return this.nodeFileAttachMapper.insertSelective(record);
	}

	@Override
	public NodeFileAttach selectByPrimaryKey(Integer fileAttachNo) {
		return this.nodeFileAttachMapper.selectByPrimaryKey(fileAttachNo);
	}

	@Override
	public int updateByPrimaryKeySelective(NodeFileAttach record) {
		return this.nodeFileAttachMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(NodeFileAttach record) {
		return this.nodeFileAttachMapper.updateByPrimaryKey(record);
	}

}
