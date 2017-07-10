package com.flower.dao;

import com.flower.models.UserBarHistory;

public interface UserBarHisDao {
		
		public void addBarHis(UserBarHistory userBrHistory);
		public UserBarHistory findLatestHis(String userName);
		
}
