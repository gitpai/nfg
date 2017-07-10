package com.su.controller;

import java.util.Date;

import com.flower.dao.IotDao;
import com.flower.dao.impl.IotDaoImpl;
import com.flower.models.IotDevice;
import com.flower.util.Md5_1;

public class IotAddTest {
	public static void main(String[] args) {
		IotDao dao=new IotDaoImpl();
		IotDevice iot=new IotDevice();
		iot.setName("µÚÒ»¸ö");
		iot.setStatus(false);
		iot.setOwner("Ø£Óñ½à");				
		iot.setTime(new Date());
		iot.setSerial("20170708");
		iot.setDevice_uuid(Md5_1.GetMD5Code("20170708"));
		dao.addDevice(iot);
	}
}
