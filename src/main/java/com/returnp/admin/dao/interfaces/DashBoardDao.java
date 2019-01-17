package com.returnp.admin.dao.interfaces;

import java.util.ArrayList;

import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;

public interface DashBoardDao {
	public DashBoard select();
	public ArrayList<DashBoardChart> selectForChart();
}
