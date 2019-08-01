package com.bank.dao;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

abstract public class BaseDao {

	protected Connection con = null;
	private Properties getProperties() throws Exception{
		Properties properties = new Properties();
		InputStream inputStream = new FileInputStream("C:\\Users\\anchal.chaudhary\\eclipse-workspace\\CBS\\src\\resources\\config.properties");

		properties.load(inputStream);
		inputStream.close();
		return properties;

	}
	protected final void getDBConnection() {

		try {

			Properties properties = getProperties();
			String driverClass = properties.getProperty("driver");
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");

			Class.forName(driverClass);
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			throw new DBConnectionError("Error in Connection...");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void closeResultSet(ResultSet rs) {

		try {
			if(rs!=null)
				rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	protected void closePreparedStatement(PreparedStatement ps) {

		try {
			if(ps!=null)
				ps.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	protected void closeConnection() {
		
		try {
			if(con!=null)
				con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
