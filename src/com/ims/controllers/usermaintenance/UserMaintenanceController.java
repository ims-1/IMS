package com.ims.controllers.usermaintenance;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;

@WebServlet("/UserMaintenanceController")
public class UserMaintenanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Users> users = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		String action = request.getParameter("action");
		int pageLimit = 10;

		try {
			if (action.equals("pagination")) {
				List<Users> returnUsers = new LinkedList<>();
				users = service.getUsers();

				if (users != null) {
					for (int start = 0; start < pageLimit; start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnUsers);
					response.getWriter().write(json);
				}
			} else if (action.equals("getSize")) {
				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(users.size()));

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);
			} else if (action.equals("getRecordPage")) {
				List<Users> returnUsers = new LinkedList<>();
				int page = Integer.parseInt(request.getParameter("page"));
				if (users != null) {
					for (int start = (page * pageLimit) - pageLimit; start < (page * pageLimit); start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnUsers);
					System.out.println(json);
					response.getWriter().write(json);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String hidden = request.getParameter("hidden");
		String page = "views/userMaintenance/userMaintenanceScreen.jsp";

		System.out.println("hidden" + hidden);
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		try {
			if (action.equals("goToUserMaintenanceScreen")) {
				System.out.println("To User Maintenance Screen...");
				hidden = "";
				page = "views/userMaintenance/userMaintenanceScreen.jsp";
			} else if (action.equals("backToUserListingPage")) {
				System.out.println("Go back to User Listing Page...");
				page = "views/userMaintenance/userListingPage.jsp";
			} else if (action.equals("saveUser")) {
				if (hidden.equals("edit")) {
					System.out.println("Record updated successfully!");
					service.updateUser(request);
				}	
				if (hidden.equals("")) {
					System.out.println("Creating new user...");
					String userId = request.getParameter("userId");
					String result = service.getUserId(userId);

					if (userId.equals(result)) {
						System.out.println("Ooops! USER ID ALREADY IN USE");
						response.sendError(401);
						return;
					} else {
						System.out.println("Record inserted.");
						service.insertNewUser(request);
					}	
				}	
			} else if (action.equals("editUser")) {
				System.out.println("Editing user...");
				//populate fields
				String uId = request.getParameter("userId");
				hidden = "edit";
				request.setAttribute("hidden", hidden);
				//disable uid button
				request.setAttribute("disableUserId", "disabled='disabled'");
				List<Users> users = service.getUser(uId);
				for (Users u : users) {
					request.setAttribute("userId", 			u.getUserId());
					request.setAttribute("firstName", 		u.getFirstName());
					request.setAttribute("middleInitial", 	u.getMiddleInitial());
					request.setAttribute("lastName", 		u.getLastName());
					request.setAttribute("email", 			u.getEmail());
					request.setAttribute("activeTag", 		u.getActiveTag());
					request.setAttribute("userAccess", 		u.getUserAccess());
					request.setAttribute("entryDate", 		u.getEntryDate());
					request.setAttribute("remarks", 		u.getRemarks());
					request.setAttribute("lastUserId", 		u.getLastUserId());
					request.setAttribute("lastUpdate", 		u.getLastUpdate());	
				}				
				page = "views/userMaintenance/userMaintenanceScreen.jsp";
			} else if (action.equals("search")) {
				
			}
		} catch (Exception e) {
			System.err.println("Error in doPost.");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}

class GetSize {
	public int listSize;

	public GetSize(int listSize) {
		this.listSize = listSize;
	}
}
