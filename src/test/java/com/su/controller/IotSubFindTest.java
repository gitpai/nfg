package com.su.controller;

import com.flower.dao.impl.IotDaoImpl;
import com.flower.models.IotSubDevice;

public class IotSubFindTest {
	public static void main(String[] args) {
		IotDaoImpl dao=new IotDaoImpl();
		IotSubDevice iot=	dao.findSubDeviceByUuid("1bea2523508e6d5758ed61a80b01d342", 2);
		System.out.println(iot.getName());
	}

}
