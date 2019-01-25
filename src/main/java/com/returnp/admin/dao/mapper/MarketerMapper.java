package com.returnp.admin.dao.mapper;

import java.util.ArrayList;

import com.returnp.admin.dto.command.MarketerCommand;
import com.returnp.admin.model.Marketer;

public interface MarketerMapper {
    int deleteByPrimaryKey(Integer marketerNo);

    int insert(Marketer record);

    int insertSelective(Marketer record);

    Marketer selectByPrimaryKey(Integer marketerNo);

    int updateByPrimaryKeySelective(Marketer record);

    int updateByPrimaryKey(Marketer record);

    String selectMaxCodeRow();
    
    ArrayList<MarketerCommand> findMarketerCommand(MarketerCommand command);
}