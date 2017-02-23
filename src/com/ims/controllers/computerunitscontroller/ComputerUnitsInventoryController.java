package com.ims.controllers.computerunitscontroller;

import java.io.IOException;
import java.sql.SQLException;
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
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;

@WebServlet("/ComputerUnitsInventoryController")
public class ComputerUnitsInventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<ComputerUnits> computerUnits = null;

	@SuppressWarnings("resource")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		String action = request.getParameter("action");
		int pageLimit = 10;
		if (action.equals("pagination")) {
			try {
				List<ComputerUnits> compUnitList = new LinkedList<>();
				computerUnits = computerUnitService.getComputerUnits();
				if (!computerUnits.isEmpty()) {
					int endRow = pageLimit;
					endRow = pageLimit > computerUnits.size() ? computerUnits.size() : endRow;
					for (int start = 0; start < endRow; start++) {
						compUnitList.add(computerUnits.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(compUnitList);
					response.getWriter().write(json);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (action.equals("getSize")) {
			List<GetSize> getSize = new ArrayList<>();
			getSize.add(new GetSize(computerUnits.size()));

			Gson gson = new Gson();
			String json = gson.toJson(getSize);
			response.getWriter().write(json);
		} else if (action.equals("getRecordPage")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<ComputerUnits> compUnitList = new LinkedList<>();
			if (!computerUnits.isEmpty()) {
				int endRow = page * pageLimit;
				endRow = endRow > computerUnits.size() ? computerUnits.size() : endRow;
				for (int start = (page * pageLimit) - pageLimit; start < (endRow); start++) {
					compUnitList.add(computerUnits.get(start));
				}
				Gson gson = new Gson();
				String json = gson.toJson(compUnitList);
				response.getWriter().write(json);
			}
		} else if (action.equals("getComputerUnitByUnitNo")) {
			int unitNo = Integer.parseInt(request.getParameter("unitNo"));
			try {
				System.out.println(unitNo);
				List<ComputerUnits> compUnitList = new LinkedList<>();
				compUnitList = computerUnitService.getComputerUnitByUnitNo(unitNo);

				Gson gson = new Gson();
				String json = gson.toJson(compUnitList);
				response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		try {
			String action = request.getParameter("action");
			if (action.equals("insertNewComputerUnit")) {
				System.out.println("insert");
				computerUnitService.insertComputerUnits(request);
			} else if (action.equals("deleteComputerUnit")) {
				computerUnitService.deleteComputerUnit(request);
			} else if (action.equals("updateComputerUnit")) {
				computerUnitService.updateComputerUnit(request);
			} else {
				System.out.println("wrongAction");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class GetSize {
		public int listSize;

		public GetSize(int listSize) {
			this.listSize = listSize;
		}
	}
}
