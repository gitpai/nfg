package com.flower.dao;

import java.util.List;

import com.flower.models.IotDevice;
import com.flower.models.IotSubDevice;
import com.flower.models.Umbrella;

public interface IotDao {
	public void addDevice(IotDevice iotDevice); 
	public IotDevice findIotByUuid(String userName,String devUuid);
	public IotDevice findIotDeviceByUuid(String devUuid);
	public List<IotDevice> findAllIotDevice(String userName);
	public void addSubDevice(IotSubDevice iotsubDevice); 
	public List<IotSubDevice> findSubDevice(String uuid);
	public IotSubDevice findSubDeviceByUuid(String uuid,int id);
	public void deleteIot(String uuid);
}
