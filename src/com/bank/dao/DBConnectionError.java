package com.bank.dao;

public class DBConnectionError extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DBConnectionError(String errorMessage) {

		super(errorMessage);
	}
}
