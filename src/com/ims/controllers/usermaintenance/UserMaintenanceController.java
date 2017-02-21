package com.ims.controllers.usermaintenance;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;

@WebServlet("/UserMaintenanceController")
public class UserMaintenanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		try {
			List<Users> users = service.getUsers();
			for (Users u : users) {
				System.out.println(u.getFirstName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String page = "views/userMaintenance/userMaintenanceScreen.jsp";

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		try {
			if (action.equals("goToUserMaintenanceScreen")) {
				System.out.println("To User Maintenance Screen...");
				page = "views/userMaintenance/userMaintenanceScreen.jsp";
			} else if (action.equals("backToUserListingPage")) {
				System.out.println("Go back to User Listing Page...");
				page = "views/userMaintenance/userListingPage.jsp";
			} else if (action.equals("saveUser")) {
				System.out.println("Record inserted.");
				service.insertNewUser(request);
			}
		} catch (Exception e) {

		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
