package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.CompanyBankAccount;

public interface CompanyBankAccountMapper {
    int deleteByPrimaryKey(Integer companyBankAccountNo);

    int insert(CompanyBankAccount record);

    int insertSelective(CompanyBankAccount record);

    CompanyBankAccount selectByPrimaryKey(Integer companyBankAccountNo);

    int updateByPrimaryKeySelective(CompanyBankAccount record);

    int updateByPrimaryKey(CompanyBankAccount record);
}