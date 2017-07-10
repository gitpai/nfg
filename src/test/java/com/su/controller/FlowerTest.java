package com.su.controller;

import com.flower.dao.FlowerDao;
import com.flower.dao.impl.FlowerDaoImpl;
import com.flower.models.Flower;

public class FlowerTest {
	public static void main(String[] args) {
		//Flower flower=new Flower();
		//flower.setFlowerName("ÂúÌìÐÇ");
		FlowerDao dao=new FlowerDaoImpl();
		dao.addFlowers("e://flower/106.xlsx");
		/*dao.addFlower(flower);*/
		System.out.println(dao.matchFlower("1"));
	}
}
