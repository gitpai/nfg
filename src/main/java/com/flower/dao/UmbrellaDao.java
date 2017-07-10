package com.flower.dao;

import java.net.Socket;
import java.util.List;
import java.util.Map;

import com.flower.models.Umbrella;
import com.flower.models.UmbrellaNear;

/**
 * @author Yujie
 *
 */
public interface UmbrellaDao {
	public List<Umbrella> findAllDevice();//�ҳ������豸(��ʵ��)
	public List<Umbrella> findAllAvlDevice();//�ҳ����п����豸
	public void deleteDevice(String uuid);//ɾ��ָ���豸(��ʵ��)
	public Umbrella findDeviceByUuid(String devUuid);//����UUid����ָ���豸(��ʵ��)
	public void updateDeviceInfo(String deviceId,Umbrella umbrella);//����ɡ����Ϣ(��ʱ���������ַ�������)
	public void addDevice(Umbrella umbrella);        			//��ӣ�Or���£�ɡ���豸(��ʵ��)
	public boolean getDeviceStatus(String uuid);                //��ȡָ��ɡ�ܵ�״̬��Ϣ  ���߻�����
	public Map<Integer,Boolean> getUmbrellaSta(String deviceId);//��ȡָ��ɡ�ܸ���ɡ��״̬��Ϣ
	public void openUmbrellaById(Socket socket,byte [] operate);//�軹ָ��ɡ(��ʵ��)
	public List<UmbrellaNear> findNearDevice(double lon,double lat);
	
	
}
