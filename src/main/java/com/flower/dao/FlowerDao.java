package com.flower.dao;

import com.flower.models.Flower;

public interface FlowerDao {
	public  Flower  matchFlower(String value);
	public void addFlower(Flower flower);
	public void addFlowers(String filePath);
}
