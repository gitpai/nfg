package com.flower.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iot_device")
public class IotDevice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	private int id;
	
	@Column(name="iot_name") //�豸����
	private String name;
	
	@Column(name="iot_owner") //�豸ӵ����
	private String owner;
	
	@Column(name="iot_serial")
	private String serial;  //�豸��� 
	
	@Column(name="uuid")//�����豸�������
	private String device_uuid;
	
	@Column(name="iot_regtime")//�豸���ʱ��
	private Date time; 	
	
	@Column(name="iot_sta") //�豸����״̬
	private boolean status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getDevice_uuid() {
		return device_uuid;
	}

	public void setDevice_uuid(String device_uuid) {
		this.device_uuid = device_uuid;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
