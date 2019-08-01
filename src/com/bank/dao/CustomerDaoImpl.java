package com.bank.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.bank.pojo.CustomerPojo;

public class CustomerDaoImpl extends BaseDao implements CustomerDao {

	@Override
	public int saveCustomer(CustomerPojo customerPojo) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		int customerId = -1;

		try{
			getDBConnection();
			con.setAutoCommit(false);
			String insertQuery = "insert into customer_details (username, customer_name, gender, age, password) values (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customerPojo.getUsername());
			ps.setString(2, customerPojo.getName());
			ps.setString(3, customerPojo.getGender());
			ps.setInt(4, customerPojo.getAge());
			ps.setString(5, customerPojo.getPassword());
			int insertCount = ps.executeUpdate();

			if(insertCount>0) {

				rs = ps.getGeneratedKeys();
				if(rs.next()) {
					con.commit();
					customerId = rs.getInt(1);
					customerPojo.setCustomerId(customerId);
				} else {
					con.rollback();
				}
			} else {
				System.out.println("Couldn't add");
			}
		}
		catch (Exception e) {
			System.out.println("ERROR: Couldn't create Employee");
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closePreparedStatement(ps);
			closeConnection();
		}
		return customerId;
	}

	@Override
	public List<CustomerPojo> listCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerPojo getCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateCustomer(CustomerPojo customerPojo) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerPojo checkCredentials(String username, String password) {

		ResultSet rs = null;
		PreparedStatement ps = null;
		int customerId = -1;
		CustomerPojo customerPojo = new CustomerPojo();
		try {
			getDBConnection();
			String findUserQuery = "select * from customer_details where username=? and password=?";
			ps = con.prepareStatement(findUserQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				customerPojo.setCustomerId(rs.getInt("customer_id"));
				customerPojo.setName(rs.getString("customer_name"));
				customerPojo.setUsername(rs.getString("username"));
				customerPojo.setPassword(rs.getString("password"));
				customerPojo.setAge(rs.getInt("age"));
				customerPojo.setGender(rs.getString("gender"));
			}
		} catch (Exception e) {
			System.out.println("Couldn't Login");
			e.printStackTrace();
		}		
		return customerPojo;
	}

}
