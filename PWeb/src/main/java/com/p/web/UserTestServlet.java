package com.p.web;

import java.io.IOException;
import java.util.List;

import com.p.model.UserTest;
import com.p.stateless.bean.TestUserBeanRemote;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class UserTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB(beanName = "TestUserBean")
    TestUserBeanRemote testbean;
	
    public UserTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String action = request.getServletPath();
		
		switch(action) {
		case "/insert":
			insertUser(request,response);
			break;
			
		default:
			listPage(request,response);
			break;
			
		
		}
	}

	private void listPage(HttpServletRequest request, HttpServletResponse response) {
		  int page = 1;
	        int recordsPerPage = 5;
	        if(request.getParameter("page") != null)
	            page = Integer.parseInt(request.getParameter("page"));
	       
	        List<UserTest> listUser = testbean.findUsers((page-1)*recordsPerPage,
	                                 recordsPerPage);
	        int noOfRecords = testbean.getNumberOfRows();
	        System.out.println(noOfRecords);
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
	        request.setAttribute("listUser", listUser);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        RequestDispatcher view = request.getRequestDispatcher("list.jsp");
	        try {
				view.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    
		
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response) {
		List<UserTest> listUser =  testbean.getallusers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) {
		UserTest user = new UserTest();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		user.setUsername(username);
		user.setEmail(email);
		testbean.saveUser(user);
		try {
			response.sendRedirect("list.jsp");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
