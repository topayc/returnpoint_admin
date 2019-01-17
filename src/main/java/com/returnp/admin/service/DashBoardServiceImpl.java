package com.returnp.admin.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.returnp.admin.dao.mapper.DashBoardMapper;
import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;
import com.returnp.admin.service.interfaces.DashBoardService;

@Service
public class DashBoardServiceImpl implements DashBoardService {

	@Autowired DashBoardMapper dashBoardMapper;
	
	@Override
	public DashBoard select() {
		// TODO Auto-generated method stub
		return this.dashBoardMapper.select();
	}
	
	@Override
	public ArrayList<DashBoardChart> selectForChart() {
		// TODO Auto-generated method stub
		return this.dashBoardMapper.selectForChart();
	}
}
