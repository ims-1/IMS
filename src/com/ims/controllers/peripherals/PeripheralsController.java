package com.ims.controllers.peripherals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ims.model.peripherals.Peripherals;
import com.ims.service.peripherals.PeripheralsService;

/**
 * Servlet implementation class PeripheralsController
 */
@WebServlet("/PeripheralsController")
public class PeripheralsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PeripheralsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");

		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");

		try {
			List<Peripherals> peripherals = service.getPeripherals(1);
			for (Peripherals p : peripherals) { 
				System.out.print(p.getUnitNo());
				System.out.print(" ");
				System.out.println(p.getPeripheralType());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
