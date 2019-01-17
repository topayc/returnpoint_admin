package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.Board;

public interface BoardMapper {
    int deleteByPrimaryKey(Integer boardNo);
    int deleteBoards(Board board);

    int insert(Board record);

    int insertSelective(Board record);

    Board selectByPrimaryKey(Integer boardNo);

    int updateByPrimaryKeySelective(Board record);

    int updateByPrimaryKeyWithBLOBs(Board record);

    int updateByPrimaryKey(Board record);
}