package com.returnp.admin.dao.mapper;

import java.util.ArrayList;

import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;

public interface DashBoardMapper {
	int insert(DashBoard record);

	int insertSelective(DashBoard record);

	DashBoard select();
	ArrayList<DashBoardChart> selectForChart();
}