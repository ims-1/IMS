package com.ims.controllers.computerunitscontroller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;

@WebServlet("/ComputerUnitsInventoryController")
public class ComputerUnitsInventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");

		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");

		try {
			computerUnitService.getComputerUnits();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");

		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");

		try {
			computerUnitService.insertComputerUnits(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
