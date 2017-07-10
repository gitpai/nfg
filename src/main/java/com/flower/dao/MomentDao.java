package com.flower.dao;

import java.util.List;

import com.flower.models.Moments;

public interface MomentDao {
	
	public List<Moments> findLatestMoment();

	public void addMoment(Moments moment);
}
