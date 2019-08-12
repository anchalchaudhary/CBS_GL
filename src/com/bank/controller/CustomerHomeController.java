package com.bank.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.pojo.CustomerPojo;
import com.bank.service.CustomerService;
import com.bank.service.CustomerServiceImpl;
import com.mysql.cj.Session;

/**
 * Servlet implementation class CustomerHomeController
 */
@WebServlet("/CustomerHomeController")
public class CustomerHomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CustomerService customerService = new CustomerServiceImpl();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerHomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String actionClicked = request.getParameter("act");
		HttpSession session = request.getSession(false);
		if(actionClicked!=null) {
			if(actionClicked.equals("dashboard")) {
				request.getRequestDispatcher("customerHome.jsp").forward(request, response);
			} else if (actionClicked.equals("addMoney")) {
				request.getRequestDispatcher("addMoney.jsp").forward(request, response);
			} else if (actionClicked.equals("sendMoney")) {
				request.getRequestDispatcher("sendMoney.jsp").forward(request, response);
			} else if (actionClicked.equals("logout") && session!=null) {
				session.invalidate();
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String actionClicked = request.getParameter("act");
		CustomerPojo customerPojo1 = new CustomerPojo();
		CustomerPojo customerPojo = (CustomerPojo) request.getSession(false).getAttribute("customerPojo");

		if(actionClicked!=null) {
			if(actionClicked.equals("editCustomer")) {
				String cId = request.getParameter("customerId");
				int customerId = Integer.parseInt(cId);
				String name = request.getParameter("cname");
				String username = request.getParameter("username");
				String gender = request.getParameter("age");
				String ageStr = request.getParameter("age");
				int age = Integer.parseInt(ageStr);

				customerPojo1.setCustomerId(customerId);
				customerPojo1.setName(name);
				customerPojo1.setUsername(username);
				customerPojo1.setGender(gender);
				customerPojo1.setUsername(username);
				customerPojo1.setAge(age);
				customerPojo1.setAccountBalance(customerPojo.getAccountBalance());
				customerPojo1.setAccountNo(customerPojo.getAccountNo());

				boolean update = customerService.updateCustomer(customerPojo1);
				if(update==true) {
					request.setAttribute("updateMessage", "Updated");
//					request.getRequestDispatcher("customerHome.jsp").forward(request, response);
					response.sendRedirect("customerHome.jsp");
					request.getSession().setAttribute("customerPojo", customerPojo1);				
				}
			}
			if(actionClicked.equals("addAmount")) {
				String money = request.getParameter("amount");
				int amount = Integer.parseInt(money);
				String cId = request.getParameter("customerId");
				int customerId = Integer.parseInt(cId);
				boolean update = customerService.addMoney(amount, customerId);
				if(update==true) {
					request.setAttribute("updateMessage", "Updated");
					response.sendRedirect("customerHome.jsp");
					request.getSession().setAttribute("customerPojo", customerPojo1);				
				}
			}
			doGet(request, response);
		}
	}
}
