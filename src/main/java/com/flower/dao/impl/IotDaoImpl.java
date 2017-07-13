package com.flower.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.flower.dao.IotDao;
import com.flower.models.IotDevice;
import com.flower.models.IotSubDevice;
import com.flower.models.NetResult;
import com.flower.models.Umbrella;
import com.flower.util.MySessionFactory;


public class IotDaoImpl implements IotDao{

	@Override
	public void addDevice(IotDevice iotDevice) {
		// TODO Auto-generated method stub
		Session session=MySessionFactory.getInstance().openSession();
		Transaction tx= session.beginTransaction();
		try{
			session.saveOrUpdate(iotDevice);
			tx.commit();			
		}catch(RuntimeException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
		
	}

	@Override
	public IotDevice findIotByUuid(String userName, String devUuid) {
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();	    
		try{
			String hql="from IotDevice where uuid=:uuid and iot_owner=:userName";			
			Query query  = session.createQuery(hql);
			query.setString("uuid", devUuid);
			query.setString("userName", userName);
			
			@SuppressWarnings("unchecked")
			List<IotDevice> iotDevices=query.list();
			if(iotDevices.size()!=0){
				IotDevice iotDevice=iotDevices.get(0);		
				return iotDevice;	
			}else{
				return null;
			}		
				
		}finally{
			session.close();
		}
	}

	@Override
	public List<IotDevice> findAllIotDevice(String userName) {
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();	    
		try{
			String hql="from IotDevice where  iot_owner=:userName";			
			Query query  = session.createQuery(hql);
			query.setString("userName", userName);
			@SuppressWarnings("unchecked")
			List<IotDevice> iotDevices=query.list();
			if(iotDevices.size()!=0){	
				return iotDevices;	
			}else{
				return null;
			}		
				
		}finally{
			session.close();
		}
	}

	@Override
	public void addSubDevice(IotSubDevice iotsubDevice) {
		// TODO Auto-generated method stub
		Session session=MySessionFactory.getInstance().openSession();
		Transaction tx= session.beginTransaction();
		try{
			session.saveOrUpdate(iotsubDevice);
			tx.commit();			
		}catch(RuntimeException e){
			tx.rollback();
			throw e;
		}finally{
			session.close();
		}
	}

	@Override
	public List<IotSubDevice> findSubDevice(String uuid) {
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();	    
		try{
			String hql="from IotSubDevice where uuid=:uuid order by id asc";			
			Query query  = session.createQuery(hql);
			query.setString("uuid", uuid);		
			@SuppressWarnings("unchecked")
			List<IotSubDevice> iotSubDevices=query.list();
			if(iotSubDevices.size()!=0){
				return iotSubDevices;
			}else{
				return null;
			}						
		}finally{
			session.close();
		}
	}

	@Override
	public IotSubDevice findSubDeviceByUuid(String uuid, int id) {
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();	    
		try{
			String hql="from IotSubDevice where uuid=:uuid and id=:id";			
			Query query  = session.createQuery(hql);
			query.setString("uuid", uuid);	
			query.setInteger("id", id);
			@SuppressWarnings("unchecked")
			List<IotSubDevice> iotSubDevices=query.list();
			if(iotSubDevices.size()!=0){
				return iotSubDevices.get(0);
			}else{
				return null;
			}						
		}finally{
			session.close();
		}
	}

	@Override
	public IotDevice findIotDeviceByUuid(String devUuid) {
		// TODO Auto-generated method stub
				Session session =MySessionFactory.getInstance().openSession();	    
				try{
					String hql="from IotDevice where uuid=:uuid";			
					Query query  = session.createQuery(hql);
					query.setString("uuid", devUuid);							
					@SuppressWarnings("unchecked")
					List<IotDevice> iotDevices=query.list();
					if(iotDevices.size()!=0){
						IotDevice iotDevice=iotDevices.get(0);		
						return iotDevice;	
					}else{
						return null;
					}		
						
				}finally{
					session.close();
				}
	}

	@Override
	public void deleteIot(String uuid) {
		// TODO Auto-generated method stub
		Session session =MySessionFactory.getInstance().openSession();
		try{
		String hql="delete from IotDevice where uuid=:uuid";
		Transaction tx= session.beginTransaction();
		Query query= session.createQuery(hql);
		query.setString("uuid", uuid);
		query.executeUpdate();
		tx.commit();
		}finally{
			session.close();
		}	
	}

}
