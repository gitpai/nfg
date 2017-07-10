package com.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flower.dao.FlowerDao;
import com.flower.models.Flower;
import com.flower.models.Moments;
import com.flower.util.ExcelUtil;
import com.flower.util.MySessionFactory;

public class FlowerDaoImpl implements FlowerDao {

	
	@Override
	public Flower matchFlower(String value) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = MySessionFactory.getInstance().openSession();
		try {

			String hql = "from Flower where id=:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", Integer.parseInt(value));
			List<Flower> flower = query.list();
			if (flower.size() == 0) {
				return null;
			}
			return flower.get(0);
		} finally {
			session.close();
		}
	}
	@Override
	public void addFlower(Flower flower) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session = MySessionFactory.getInstance().openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("¿ªÊ¼´æ´¢");
		try {
			session.saveOrUpdate(flower);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {

			session.close();
		}
	}
	@Override
	public void addFlowers(String filePath) {
		// TODO Auto-generated method stub
		ExcelUtil.analysisXlsx(filePath);
	}
	
	}


