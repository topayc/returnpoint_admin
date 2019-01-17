package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.MemberBankAccount;

public interface MemberBankAccountMapper {
    int deleteByPrimaryKey(Integer memberBankAccountNo);

    int insert(MemberBankAccount record);

    int insertSelective(MemberBankAccount record);

    MemberBankAccount selectByPrimaryKey(Integer memberBankAccountNo);

    int updateByPrimaryKeySelective(MemberBankAccount record);

    int updateByPrimaryKey(MemberBankAccount record);
}