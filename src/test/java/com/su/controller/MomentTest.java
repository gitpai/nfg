package com.su.controller;

import java.util.Date;
import java.util.List;

import com.flower.dao.MomentDao;
import com.flower.dao.impl.MomentDaoImpl;
import com.flower.models.Moments;

public class MomentTest {
	public static void main(String[] args) {
	/*	for(int i=0;i<10;i++){
			Moments moment=new Moments();
			moment.setUserName("wuyujie123");
			moment.setUserSex(true);
			moment.setUserLocal("�Ϻ���ѧ��ӢѧԺ"+i);
			moment.setUserMoment("������ɡ�ܺ���"+i);
			moment.setMomentTime(new Date());
			MomentDao mDao=new MomentDaoImpl();
			mDao.addMoment(moment);	
		}*/
		MomentDao mDao=new MomentDaoImpl();
		List<Moments> mm=	mDao.findLatestMoment();
		Moments m=mm.get(0);
		System.out.println(m.getMomentTime());
		
	}
}
