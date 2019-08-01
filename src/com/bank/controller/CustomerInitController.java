package com.bank.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.pojo.CustomerPojo;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;

@WebServlet("/CustomerInitController")
public class CustomerInitController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	CustomerService customerService = new CustomerServiceImpl();
	List<CustomerPojo> customerList = new ArrayList<CustomerPojo>();

	public CustomerInitController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//		customerList = customerService.listCustomers();
		//		request.setAttribute("totalEmpCount", customerList.size());

		//		request.setAttribute("empListPojo", customerList);
		//		RequestDispatcher rd = request.getRequestDispatcher("customerHome.jsp");
		//		rd.forward(request, response);
		request.getRequestDispatcher("login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String actionclicked = request.getParameter("act");
		Map<String, String> messages = new HashMap<String, String>();

		CustomerPojo customerPojo = new CustomerPojo();		

		if(actionclicked!=null) {
			if(actionclicked.equals("login")) {
				String username = request.getParameter("username").trim();
				String password = request.getParameter("password");

				if(username == null || username.isEmpty()) {
					messages.put("username", "Please enter Username");
				} if (password == null || password.isEmpty()) {
					messages.put("password","Please enter Password");
				}

				if(messages.isEmpty()) {
					customerPojo = customerService.checkCredentials(username, password);

					if (customerPojo!=null) {
						request.getSession().setAttribute("customer", customerPojo);
						request.setAttribute("messages", messages);
						System.out.println("Session Created successfully with id as" + request.getSession().getId());
						request.getRequestDispatcher("customerHome.jsp").forward(request, response);
					} else {
						messages.put("login", "Invalid!");
						request.setAttribute("messages", messages);
						response.sendRedirect("index.jsp");
					}	
				} else {
					request.setAttribute("messages", messages);
					response.sendRedirect("index.jsp");
				}
			} else if (actionclicked.equals("createUser")) {
				String username = request.getParameter("username").trim();
				String password = request.getParameter("password");
				String name = request.getParameter("cname").trim();
				String ageStr = request.getParameter("age").trim();
				int age =- 1;
				if(ageStr != null)
					age = Integer.parseInt(ageStr);
				String gender = request.getParameter("gender");
				String cId = request.getParameter("customerId");

				if(username == null || username.isEmpty()) {
					messages.put("username", "Please enter Username");
				} if (password == null || password.isEmpty()) {
					messages.put("password","Please enter Password");
				} if(name == null || name.isEmpty()) {
					messages.put("name", "Please enter Name");
				} if (age < 0) {
					messages.put("age","Please enter valid age");
				} if(gender == null || gender.isEmpty()) {
					messages.put("gender", "Please select gender");
				}

				if(messages.isEmpty()) {
					customerPojo.setUsername(username);
					customerPojo.setName(name);
					customerPojo.setPassword(password);
					customerPojo.setAge(age);
					customerPojo.setGender(gender);
					int customerId = customerService.createCustomer(customerPojo);

					if(customerId!=-1) {
						request.getSession().setAttribute("customer", customerPojo);
						request.setAttribute("messages", messages);
						System.out.println("Session Created successfully with id as" + request.getSession().getId());
						request.getRequestDispatcher("customerHome.jsp").forward(request, response);
					} else {
						request.setAttribute("messages", messages);
						System.out.println("Error in customer creation");
					}
				} else {
					request.setAttribute("messages", messages);
					response.sendRedirect("index.jsp");
				}
			}
		}
	}
}
