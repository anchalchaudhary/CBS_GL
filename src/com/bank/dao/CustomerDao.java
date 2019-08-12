package com.bank.dao;

import java.util.List;

import com.bank.pojo.CustomerPojo;

public interface CustomerDao {
	int saveCustomer(CustomerPojo customerPojo);

	List<CustomerPojo> listCustomer();

	CustomerPojo getCustomer(int customerId);

	boolean updateCustomer(CustomerPojo customerPojo);

	void deleteCustomer(int customerId);

	CustomerPojo checkCredentials(String username, String password);

	boolean addMoney(int amount, int customerId);
}
