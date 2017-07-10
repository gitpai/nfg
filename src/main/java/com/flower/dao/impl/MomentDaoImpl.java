package com.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flower.dao.MomentDao;
import com.flower.models.Moments;
import com.flower.models.Umbrella;
import com.flower.util.MySessionFactory;

public class MomentDaoImpl implements MomentDao{

	@Override
	public List<Moments> findLatestMoment() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();
	    
		try{
			String hql="from Moments";	
			Query query  = session.createQuery(hql);
			List<Moments> moments=query.list();
			if(moments.size()==0){
				return null;
			}
			return moments;
		}finally{
			session.close();
		}	
	}

	@Override
	public void addMoment(Moments monment) {
		// TODO Auto-generated method stub
		Session session = MySessionFactory.getInstance().openSession();
		Transaction tx = session.beginTransaction();
		System.out.println("��ʼ�洢");
		try {
			session.saveOrUpdate(monment);
			tx.commit();
		} catch (RuntimeException e) {
			tx.rollback();
			throw e;
		} finally {

			session.close();
		}
	}

}
