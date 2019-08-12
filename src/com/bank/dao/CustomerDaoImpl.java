package com.bank.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
					Date date = new Date();
					LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
					int year  = localDate.getYear();
					int month = localDate.getMonthValue();
					int day   = localDate.getDayOfMonth();
					String accountNo = String.valueOf(day) + String.valueOf(month) + String.valueOf(year) + customerId;
					
					ps.close();
					
					customerPojo.setCustomerId(customerId);
					
					String updateQuery = "Update customer_details set account_no = ? where customer_id = ?";
					ps = con.prepareStatement(updateQuery);
					ps.setString(1, accountNo);
					ps.setInt(2, customerId);
					int updateCount = ps.executeUpdate();
					if(updateCount>0) {
						customerPojo.setAccountNo(accountNo);
						customerPojo.setAccountBalance(0);
						System.out.println("Account No. generated");
						con.commit();
					}
					
				} else {
					con.rollback();
				}
			} else {
				System.out.println("Couldn't add");
			}
		}
		catch (Exception e) {
			System.out.println("ERROR: Couldn't create Customer");
			
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
	public boolean updateCustomer(CustomerPojo customerPojo) {

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			getDBConnection();

			String updateQuery = "update  customer_details set customer_name=?, username=?, age=?, gender=? where customer_id=?";
			ps = con.prepareStatement(updateQuery);

			ps.setString(1, customerPojo.getName().trim());
			ps.setString(2, customerPojo.getUsername().trim());
			ps.setInt(3, customerPojo.getAge());
			ps.setString(4, customerPojo.getGender().trim());
			ps.setInt(5, customerPojo.getCustomerId());
			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("Updated Customer record successfully...");
				return true;
			} else {
				System.out.println("Error in updating Customer record...");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			closeConnection();
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
		return false;
	}

	@Override
	public void deleteCustomer(int customerId) {
		// TODO Auto-generated method stub

	}

	@Override
	public CustomerPojo checkCredentials(String username, String password) {

		ResultSet rs = null;
		PreparedStatement ps = null;
//		int customerId = -1;
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
				customerPojo.setAccountBalance(rs.getInt("account_balance"));
				customerPojo.setAccountNo(rs.getString("account_no"));
			}
			else {
				customerPojo = null;
			}
		} catch (Exception e) {
			System.out.println("Couldn't Login");
			e.printStackTrace();
		}	finally {
			closeConnection();
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
	
		return customerPojo;
	}

	@Override
	public boolean addMoney(int amount, int customerId) {

		ResultSet rs = null;
		PreparedStatement ps = null;

		try {
			getDBConnection();

			String updateQuery = "update  customer_details set account_balance=? where customer_id=?";
			ps = con.prepareStatement(updateQuery);

			ps.setInt(1, amount);
			ps.setInt(2, customerId);
			int count = ps.executeUpdate();
			if (count > 0) {
				System.out.println("Updated Customer record successfully...");
				return true;
			} else {
				System.out.println("Error in updating Customer record...");
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			closeConnection();
			closeResultSet(rs);
			closePreparedStatement(ps);
		}
		return false;
	}

}
