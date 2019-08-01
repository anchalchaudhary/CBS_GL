package com.bank.service;

import java.util.List;

import com.bank.pojo.CustomerPojo;

public interface CustomerService {

	int createCustomer(CustomerPojo customerPojo);

	List<CustomerPojo> listCustomers();

	CustomerPojo getCustomer(int customerId);

	void updateCustomer(CustomerPojo customerPojo);

	void deleteCustomer(int customerId);

	CustomerPojo checkCredentials(String username, String password);
}
