package com.bank.service;

import java.util.List;

import com.bank.dao.CustomerDao;
import com.bank.dao.CustomerDaoImpl;
import com.bank.pojo.CustomerPojo;

public class CustomerServiceImpl implements CustomerService{

	CustomerDao customerDao = new CustomerDaoImpl();
	@Override
	public int createCustomer(CustomerPojo customerPojo) {
		int customerId = customerDao.saveCustomer(customerPojo);
		//send sms
		//send email
		//generate employee ID
		customerPojo.setCustomerId(customerId);
		return 0;
	}

	@Override
	public List<CustomerPojo> listCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerPojo getCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCustomer(CustomerPojo customerPojo) {

		return customerDao.updateCustomer(customerPojo);
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerPojo checkCredentials(String username, String password) {

		CustomerPojo customerPojo = new CustomerPojo();
		customerPojo = customerDao.checkCredentials(username, password);
		return customerPojo;
	}

	@Override
	public boolean addMoney(int amount, int customerId) {

		return customerDao.addMoney(amount, customerId);
	}

}
