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
import com.ims.entity.computerunitsinventory.ComputerType;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;
import com.ims.utilities.FilterRecord;
import com.ims.utilities.PaginationHelper;

@WebServlet("/ComputerUnitsInventoryController")
public class ComputerUnitsInventoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings({ "resource", "unchecked" })
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		String action = request.getParameter("action");

		int pageLimit = 10;
		if (action.equals("pagination")) {
			try {
				List<ComputerUnits> computerUnits = null;
				HttpSession sessionList = request.getSession();
				computerUnits = computerUnitService.getComputerUnits();
				sessionList.setAttribute("list", computerUnits);

				// TODO-Jenny all records returned by select, save it to session
				// with setAttribute named list

				// TODO-Jenny pagination was moved here
				// String json = Pagination.getPage(computerUnits, 0, pageLimit,
				// computerUnits.size);
				/*
				 * if (!computerUnits.isEmpty()) { int endRow = pageLimit;
				 * endRow = pageLimit > computerUnits.size() ?
				 * computerUnits.size() : endRow; for (int start = 0; start <
				 * endRow; start++) {
				 * compUnitList.add(computerUnits.get(start)); } Gson gson = new
				 * Gson(); String json = gson.toJson(compUnitList);
				 */
				String json = "";
				if (!computerUnits.isEmpty()) {
					json = PaginationHelper.getPageComputerUnit(computerUnits, 0, pageLimit, computerUnits.size());
					response.getWriter().write(json);
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
				/*
				 * int endRow = page * pageLimit; endRow = endRow >
				 * computerUnits.size() ? computerUnits.size() : endRow; for
				 * (int start = (page * pageLimit) - pageLimit; start <
				 * (endRow); start++) {
				 * compUnitList.add(computerUnits.get(start)); } Gson gson = new
				 * Gson(); String json = gson.toJson(compUnitList);
				 */

				json = PaginationHelper.getPageComputerUnit(computerUnits, page, pageLimit, computerUnits.size());
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
		} else if (action.equals("getFilteredRecord")) {
			List<ComputerUnits> filteredList = new LinkedList<>();

			List<ComputerUnits> computerUnits = null;
			HttpSession sessionList = request.getSession();
			computerUnits = (List<ComputerUnits>) sessionList.getAttribute("list");

			filteredList.removeAll(computerUnits);

			String words = request.getParameter("filterText");
			words = words.toUpperCase();

			if (!computerUnits.isEmpty()) {

				// TODO-Jenny created function to filter, please test
				filteredList = FilterRecord.getFilterCompUnits(computerUnits, words);
			}
			String json = "";
			if (!filteredList.isEmpty()) {

				// TODO-Jenny sample of using paginationhelper
				json = PaginationHelper.getPageComputerUnit(filteredList, 0, pageLimit, filteredList.size());

				// int endRow = pageLimit;
				// endRow = pageLimit > filteredList.size() ?
				// filteredList.size() : endRow;
				// for (int start = 0; start < endRow; start++) {
				// compUnitList.add(filteredList.get(start));
				// }
			}
			// Gson gson = new Gson();
			// String json = gson.toJson(compUnitList);
			sessionList.setAttribute("filteredList", filteredList);
			response.getWriter().write(json);
		} else if (action.equals("getFilteredSize")) {

			List<ComputerUnits> filteredList = new LinkedList<>();
			HttpSession filteredSession = request.getSession();
			filteredList = (List<ComputerUnits>) filteredSession.getAttribute("filteredList");

			List<GetSize> getSize = new ArrayList<>();
			getSize.add(new GetSize(filteredList.size()));

			Gson gson = new Gson();
			String json = gson.toJson(getSize);
			response.getWriter().write(json);
		} else if (action.equals("getFilteredRecordPage")) {

			List<ComputerUnits> filteredList = new LinkedList<>();
			HttpSession filteredSession = request.getSession();
			filteredList = (List<ComputerUnits>) filteredSession.getAttribute("filteredList");

			int page = Integer.parseInt(request.getParameter("page"));
			String json = "";
			if (!filteredList.isEmpty()) {
				//json = PaginationHelper.getPageComputerUnit(filteredList, page, pageLimit, filteredList.size());

				response.getWriter().write(json);
			}
		} else if (action.equals("getComputerType")) {
			try {
				List<ComputerType> compTypeList = computerUnitService.getComputerType();

				for (int x = 0; x < compTypeList.size(); x++) {
					System.out.println(compTypeList.get(x).getGirValue());
				}

				Gson gson = new Gson();
				String json = gson.toJson(compTypeList);
				response.getWriter().write(json);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
				.getBean("serviceComputerUnitsInventoryBean");
		String currentAction = "";

		try {
			String action = request.getParameter("action");
			if (action.equals("insertNewComputerUnit")) {
				HttpSession session = request.getSession();
				session.setAttribute("currentAction", "ADD");

				List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");

				if (sessionCompList == null) {
					sessionCompList = new LinkedList<>();
				}
				sessionCompList.add(computerUnitService.returnComputerUnits(request));
				session.setAttribute("sessionCompList", sessionCompList);

			} else if (action.equals("deleteComputerUnit")) {
				computerUnitService.deleteComputerUnit(request);
			} else if (action.equals("updateComputerUnit")) {
				computerUnitService.updateComputerUnit(request);
			} else if (action.equals("save")) {
				HttpSession session = request.getSession();
				List<ComputerUnits> sessionCompList = (List<ComputerUnits>) session.getAttribute("sessionCompList");
				currentAction = (String) session.getAttribute("currentAction");

				if (sessionCompList != null) {
					if (currentAction.equals("ADD")) {
						for (ComputerUnits x : sessionCompList) {
							computerUnitService.insertComputerUnits(x);
						}
						session.invalidate();
						session.setAttribute("currentAction", "NONE");
					} else if (currentAction.equals("UPDATE")) {
						for (ComputerUnits x : sessionCompList) {

						}
					}
				}
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
