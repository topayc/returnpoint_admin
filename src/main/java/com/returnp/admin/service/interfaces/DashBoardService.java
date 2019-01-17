package com.returnp.admin.service.interfaces;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.returnp.admin.model.DashBoard;
import com.returnp.admin.model.DashBoardChart;

@Transactional
public interface DashBoardService {
	public DashBoard select();
	public ArrayList<DashBoardChart> selectForChart();
}
