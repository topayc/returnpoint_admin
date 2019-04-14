package com.returnp.admin.dao.mapper;

import com.returnp.admin.model.DeviceInfo;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(Integer deviceInfoNo);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(Integer deviceInfoNo);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);
}