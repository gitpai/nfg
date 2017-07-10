package com.flower.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="iden_flower")
public class Flower {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id",unique=true)
	private int id;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name="flowerName")
	private  String flowerName;
	

	public String getFlowerName() {
		return flowerName;
	}

	

	public void setFlowerName(String flowerName) {
		this.flowerName = flowerName;
	}
	
	
}
