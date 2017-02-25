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
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ims.model.usermaintenance.Users;
import com.ims.service.usermaintenance.UserMaintenanceService;
import com.ims.utilities.FilterRecord;
import com.ims.utilities.PaginationHelper;

@WebServlet("/UserMaintenanceController")
public class UserMaintenanceController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	

	@SuppressWarnings({ "unchecked"})
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		String action = request.getParameter("action");
		int pageLimit = 10;

		try {
			if (action.equals("pagination")) {
				List<Users> users = null;
				HttpSession userSession = request.getSession();
				users = service.getUsers();
				userSession.setAttribute("list", users);
				String json = "";
				
				if (users != null) {
					/*for (int start = 0; start < pageLimit; start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
					String json = gson.toJson(returnUsers); */
					
					json = PaginationHelper.getPageUsers(users, 0, pageLimit, users.size());
					response.getWriter().write(json);
				}
			} else if (action.equals("getSize")) {
				List<Users> users = null;
				HttpSession userSession = request.getSession();
				users = (List<Users>) userSession.getAttribute("list");
				
				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(users.size()));

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);
			} else if (action.equals("getRecordPage")) {
				List<Users> users = null;
				HttpSession userSession = request.getSession();
				users = (List<Users>) userSession.getAttribute("list");
				int page = Integer.parseInt(request.getParameter("page"));
				String json = "";
				if (users != null) {
					/*for (int start = (page * pageLimit) - pageLimit; start < (page * pageLimit); start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnUsers);
					System.out.println(json);*/
					json = PaginationHelper.getPageUsers(users, page, pageLimit, users.size()); 
					response.getWriter().write(json);
				}
			} else if (action.equals("filterUserRecords")) {
				List<Users> filteredUsers = new LinkedList<>();
				List<Users> users = null;
				HttpSession userSession = request.getSession();
				
				users = (List<Users>) userSession.getAttribute("list");
				
				filteredUsers.removeAll(users);
				
				String json = "";
				String filterTxt = request.getParameter("filterTxt");
				filterTxt = filterTxt.toUpperCase();
				
				if (users != null) {
					/*for (int start = 0; start < pageLimit; start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
					String json = gson.toJson(returnUsers); */
					
					//json = PaginationHelper.getPageUsers(filteredUsers, 0, pageLimit, filteredUsers.size());
					filteredUsers = FilterRecord.getFilterUsers(users, filterTxt);
				}
				if (filteredUsers != null) {
					json = PaginationHelper.getPageUsers(filteredUsers, 0, pageLimit, filteredUsers.size());
				}
				userSession.setAttribute("filteredUsers", filteredUsers);
				response.getWriter().write(json);
			} else if (action.equals("getFilteredSize")) {
				List<Users> filteredUserRecords = new LinkedList<>();
				HttpSession filteredUserSession = request.getSession();
				
				filteredUserRecords = (List<Users>) filteredUserSession.getAttribute("filteredUsers");
				
				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(filteredUserRecords.size()));

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);
			} else if (action.equals("getFilteredRecordPage")) {
				List<Users> filteredUserRecords = new LinkedList<>();
				HttpSession filteredUserSession = request.getSession();
				filteredUserRecords = (List<Users>) filteredUserSession.getAttribute("filteredUsers");
				
				int page = Integer.parseInt(request.getParameter("page"));
				String json = "";
				
				if (filteredUserRecords != null) {
					/*for (int start = (page * pageLimit) - pageLimit; start < (page * pageLimit); start++) {
						returnUsers.add(users.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnUsers);
					System.out.println(json);*/
					//json = PaginationHelper.getPageUsers(filteredUserRecords, page, pageLimit, filteredUserRecords.size()); 
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
		
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		try {
			if (action.equals("saveUser")) {
				if (hidden.equals("edit")) {
					service.updateUser(request);
					System.out.println("Record updated successfully!");
				}	
				if (hidden.equals("")) {
					System.out.println("Creating new user...");
					String userId = request.getParameter("userId");
					String result = service.getUserId(userId);
					
					if (userId.equals(result)) {
						System.out.println("Ooops! USER ID ALREADY IN USE");
						response.setStatus(203);
						return;
					} else {
						service.insertNewUser(request);
						System.out.println("Record inserted.");
					}	
				}	
			} else if (action.equals("editUser")) {
				System.out.println("fjlas");
				System.out.println("we");
				System.out.println("Editing user...");
				String json ="";
				
				List<Users> users = service.populateUserFields(request);
	
				if (!users.isEmpty()) {
					System.out.println("NOT EMPTY");
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
					json = gson.toJson(users);
				}
				System.out.println("EMPTY");
				response.getWriter().write(json);
				return;
			} else if (action.equals("userChangePassword")) {
				String userId = request.getParameter("userId");
				List<Users> users = service.getUser(userId);
				for (Users u : users) {	
					request.setAttribute("userId", u.getUserId());
				}	
			} else if (action.equals("confirmPassword")) {
				service.updatePassword(request);
			}
		} catch (Exception e) {
			System.err.println("Error in doPost.");
		}

	}

}

class GetSize {
	public int listSize;

	public GetSize(int listSize) {
		this.listSize = listSize;
	}
}
