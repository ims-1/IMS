package com.ims.utilities;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ims.service.usermaintenance.UserMaintenanceService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_auth = (String) session.getAttribute("user_auth");
		if (user_auth != null || user_auth != "") {
			response.sendRedirect("/home");
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UserMaintenanceService service = (UserMaintenanceService) context.getBean("serviceUsersBean");

		String action = request.getParameter("action");
		if (action.equals("auth")) {
			HttpSession session = request.getSession();

			String user = ((String) session.getAttribute("user_auth")) == null ? ""
					: ((String) session.getAttribute("user_auth"));
			if (user != "") {
				response.setStatus(204);
				return;
			} else {
				response.setStatus(200);
				return;
			}

		}

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			String hashed_password = service.getPassword(username.toLowerCase());
			if (hashed_password != null) {
				boolean userAuthentic = Password.checkPassword(password, hashed_password);
				if (userAuthentic) {

					String user_access = service.getUserAccess(username);
					HttpSession session = request.getSession();
					session.setAttribute("user_auth", username);
					session.setAttribute("user_access", user_access);
				} else {
					response.sendError(201);
					return;
				}
			} else {
				response.setStatus(202);
				return;
			}
		} catch (SQLException e) {
			response.sendError(401);
			return;
		}
	}
}
