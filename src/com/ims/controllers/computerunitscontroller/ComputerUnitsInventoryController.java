package com.ims.controllers.computerunitscontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.peripherals.Peripherals;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;
import com.ims.service.peripherals.PeripheralsService;
import com.ims.utilities.FilterRecord;
import com.ims.utilities.PaginationHelper;

@WebServlet("/ComputerUnitsInventoryController")
public class ComputerUnitsInventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int pageLimit = 10;

	@SuppressWarnings({ "resource", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		String action = request.getParameter("action");
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			if (action.equals("pagination")) {

				List<ComputerUnits> computerUnits = null;
				HttpSession sessionList = request.getSession();
				computerUnits = computerUnitService.getComputerUnits();
				sessionList.setAttribute("list", computerUnits);

				String json = "";
				if (!computerUnits.isEmpty()) {
					json = PaginationHelper.getPageComputerUnit(computerUnits, 0, pageLimit, computerUnits.size());
					response.getWriter().write(json);
				} else {
					response.setStatus(201);
				}

			} else if (action.equals("getSize")) {
				List<ComputerUnits> computerUnits = null;
				HttpSession sessionList = request.getSession();
				computerUnits = (List<ComputerUnits>) sessionList.getAttribute("list");

				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(computerUnits.size()));

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);
			} else if (action.equals("getRecordPage")) {
				List<ComputerUnits> computerUnits = null;
				HttpSession sessionList = request.getSession();
				computerUnits = (List<ComputerUnits>) sessionList.getAttribute("list");
				int page = Integer.parseInt(request.getParameter("page"));
				String json = "";
				if (!computerUnits.isEmpty()) {

					json = PaginationHelper.getPageComputerUnit(computerUnits, page, pageLimit, computerUnits.size());
					response.getWriter().write(json);
				}
			} else if (action.equals("getComputerUnitByUnitNo")) {
				int unitNo = Integer.parseInt(request.getParameter("unitNo"));

				System.out.println(unitNo);
				List<ComputerUnits> compUnitList = new LinkedList<>();
				compUnitList = computerUnitService.getComputerUnitByUnitNo(unitNo);

				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
				String json = gson.toJson(compUnitList);
				response.getWriter().write(json);

			} else if (action.equals("getFilteredRecord")) {
				List<ComputerUnits> filteredList = new LinkedList<>();

				List<ComputerUnits> computerUnits = null;
				HttpSession sessionList = request.getSession();
				computerUnits = (List<ComputerUnits>) sessionList.getAttribute("list");

				filteredList.removeAll(computerUnits);

				String words = request.getParameter("filterText");
				words = words.toUpperCase();
				if (!computerUnits.isEmpty()) {
					filteredList = FilterRecord.getFilterCompUnits(computerUnits, words);
				}
				String json = "";
				if (!filteredList.isEmpty()) {
					json = PaginationHelper.getPageComputerUnit(filteredList, 0, pageLimit, filteredList.size());

				}
				sessionList.setAttribute("filteredList", filteredList);
				response.getWriter().write(json);
			} else if (action.equals("getFilteredSize")) {

				List<ComputerUnits> filteredList = new LinkedList<>();
				HttpSession filteredSession = request.getSession();
				filteredList = (List<ComputerUnits>) filteredSession.getAttribute("filteredList");

				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(filteredList.size()));

				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);
			} else if (action.equals("getFilteredRecordPage")) {

				List<ComputerUnits> filteredList = new LinkedList<>();
				HttpSession filteredSession = request.getSession();
				filteredList = (List<ComputerUnits>) filteredSession.getAttribute("filteredList");

				int page = Integer.parseInt(request.getParameter("page"));
				String json = "";
				if (!filteredList.isEmpty()) {
					json = PaginationHelper.getPageComputerUnit(filteredList, page, pageLimit, filteredList.size());

					response.getWriter().write(json);
				}
			} else if (action.equals("getComputerType")) {

				List<ComputerType> compTypeList = computerUnitService.getComputerType();
				Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
				String json = gson.toJson(compTypeList);
				response.getWriter().write(json);

			} else if (action.equals("getUserAuth")) {
				HttpSession userSession = request.getSession();
				String userAuth = (String) userSession.getAttribute("user_auth");
				response.getWriter().write(userAuth);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.sendError(410);
		}
	}

	@SuppressWarnings({ "unchecked", "resource" })
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		String currentAction = "";

		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			String action = request.getParameter("action");
			if (action.equals("insertNewComputerUnit")) {
				// Add comp units
				HttpSession session = request.getSession();
				session.setAttribute("currentAction", "NONE");
				currentAction = (String) session.getAttribute("currentAction");
				System.out.println(currentAction);
				if (currentAction.equals("UPDATE")) {
					String warningMsg = "Your current changes were not yet saved.";
					response.getWriter().write(warningMsg);
				} else {
					session.setAttribute("currentAction", "ADD");
					List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");
					if (sessionCompList == null) {
						sessionCompList = new LinkedList<>();
					}
					ComputerUnits newUnitAdded = computerUnitService.returnComputerUnits(request);

					sessionCompList.add(newUnitAdded);
					session.setAttribute("sessionCompList", sessionCompList);

					List<ComputerUnits> computerUnits = null;
					computerUnits = (List<ComputerUnits>) session.getAttribute("list");
					computerUnits.add(0, newUnitAdded);
					String json = "";
					if (!computerUnits.isEmpty()) {
						json = PaginationHelper.getPageComputerUnit(computerUnits, 0, pageLimit, computerUnits.size());
						response.getWriter().write(json);
					}

				}
			} else if (action.equals("deleteComputerUnit")) {
				// Delete Comp Units
				PeripheralsService peripheral = (PeripheralsService) context.getBean("servicePeripheralsBean");
				List<Peripherals> periList = new LinkedList<>();
				periList = peripheral.getPeripherals(request);
				String warningMsg = "";
				if (!periList.isEmpty()) {
					warningMsg = "Cannot delete record.";
					response.getWriter().write(warningMsg);
				} else {
					computerUnitService.deleteComputerUnit(request);
					warningMsg = "Record deleted!";
					response.getWriter().write(warningMsg);
				}

			} else if (action.equals("updateComputerUnit")) {
				// Update comp units
				if (currentAction.equals("ADD")) {
					String warningMsg = "Your current changes were not yet saved.";
					response.getWriter().write(warningMsg);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("currentAction", "UPDATE");
					List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");
					if (sessionCompList == null) {
						sessionCompList = new LinkedList<>();
					}
					sessionCompList.add(computerUnitService.returnComputerUnits(request));
					session.setAttribute("sessionCompList", sessionCompList);

					// to update frontend table
					ComputerUnits updatedUnits = new ComputerUnits();
					updatedUnits = computerUnitService.returnComputerUnits(request);
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").serializeNulls().create();
					String json = gson.toJson(updatedUnits);
					response.getWriter().write(json);
				}
			} else if (action.equals("save")) {
				HttpSession session = request.getSession();
				currentAction = (String) session.getAttribute("currentAction");
				List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");

				if (currentAction.equals("ADD")) {
					if (sessionCompList != null) {
						for (ComputerUnits x : sessionCompList) {
							computerUnitService.insertComputerUnits(x);
						}
					}
				} else if (currentAction.equals("UPDATE")) {
					for (ComputerUnits x : sessionCompList) {
						computerUnitService.updateComputerUnit(x);
					}
				}
				// remove session records
				if (!sessionCompList.isEmpty()) {
					sessionCompList.clear();
					session.setAttribute("sessionCompList", sessionCompList);
					session.setAttribute("currentAction", "NONE");
				}
			} else if (action.equals("cancel")) {
				HttpSession session = request.getSession();
				List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");
				if (!sessionCompList.isEmpty()) {
					sessionCompList.clear();
					session.setAttribute("sessionCompList", sessionCompList);
					session.setAttribute("currentAction", "NONE");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			response.setStatus(410);
		}
	}

	class GetSize {
		public int listSize;

		public GetSize(int listSize) {
			this.listSize = listSize;
		}
	}
}
