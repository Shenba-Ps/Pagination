package com.p.stateless.bean;

import java.util.List;

import com.p.model.UserTest;

import jakarta.ejb.Remote;

@Remote
public interface TestUserBeanRemote {
	
	public void saveUser(UserTest test);
	public List<UserTest> getallusers();
	public List<UserTest> findUsers(int currentPage, int recordsPerPage);
	 public Integer getNumberOfRows();

}
