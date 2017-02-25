package com.ims.controllers.peripherals;

import java.io.IOException;
import java.lang.reflect.Type;
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
import com.google.gson.reflect.TypeToken;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.peripherals.ComputerAssigneeData;
import com.ims.model.peripherals.Peripherals;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;
import com.ims.service.peripherals.PeripheralsService;
import com.ims.utilities.PaginationHelper;
import com.ims.utilities.SystemStatus;

/**
 * Servlet implementation class PeripheralsController
 */
@WebServlet("/PeripheralsController")
public class PeripheralsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Peripherals> peripherals = null;

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");

		HttpSession session = request.getSession();

		try {
			int pageLimit = 5;
			String action = request.getParameter("action");
			System.out.println(action + " action");
			if (action.equals("pagination")) {
				String no = request.getParameter("num");
				System.out.println("num " + no);
				if (no != "") {
					try {
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");

						peripherals = service.getPeripherals(request);

						if (!peripherals.isEmpty()) {
							session.setAttribute("list", peripherals);
							String json = PaginationHelper.getPagePeripherals(peripherals, 0, pageLimit,
									peripherals.size());
							System.out.println(json);

							response.getWriter().write(json);
							return;
						} else {
							response.setStatus(201);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					try {
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");

						peripherals = service.getPeripherals();
						if (!peripherals.isEmpty()) {
							session.setAttribute("list", peripherals);
							String json = PaginationHelper.getPagePeripherals(peripherals, 0, pageLimit,
									peripherals.size());

							System.out.println(json);

							response.getWriter().write(json);
						} else {
							response.setStatus(201);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (action.equals("getSize")) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");

				int size = ((List<Peripherals>) session.getAttribute("list")).size();

				System.out.println("size" + size);
				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(size));

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				response.getWriter().write(json);

			}
			else if(action.equals("getUnit")){
				response.setContentType("application/json");

				ComputerUnitsInventoryService serviceUnit = (ComputerUnitsInventoryService) context.getBean("serviceComputerUnitsInventoryBean");
				
				List<ComputerUnits> compUnits = serviceUnit.getComputerUnits();
				if(!compUnits.isEmpty()){
					Gson gson = new Gson();
					String units = gson.toJson(compUnits);
					response.getWriter().write(units);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(410);
			return;
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");
		System.out.println("service");

		HttpSession session = request.getSession();

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		System.out.println(action);
		String status = request.getParameter("status");
		System.out.println(status);
		if (action.equals("saveRecord")) {

			@SuppressWarnings("unchecked")
			List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral");
			String user = (String) session.getAttribute("auth_user");
			if (sessionPeripheral != null) {
				for (int a = 0; a < sessionPeripheral.size(); a++) {
					try {
						sessionPeripheral.get(a).setUserId(user);
						if (sessionPeripheral.get(a).getStatus().equals("Add")) {
							SystemStatus stat = service.insertNewPeripherals(sessionPeripheral.get(a));
							if (stat == SystemStatus.exception) {
								System.out.println(
										sessionPeripheral.get(a).getPeripheralNo() + " : an exception has occured.");
							} else if (stat == SystemStatus.committed) {
								System.out.println(
										sessionPeripheral.get(a).getPeripheralNo() + " : was successfully committed.");
								sessionPeripheral.remove(a);
							}
						} else {
							SystemStatus stat = service.updatePeripheral(sessionPeripheral.get(a));
							if (stat == SystemStatus.exception) {
								System.out.println(
										sessionPeripheral.get(a).getPeripheralNo() + " : an exception has occured.");
							} else if (stat == SystemStatus.committed) {
								sessionPeripheral.remove(a);
							}
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("add")) {
			try {

				List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral");
				if (sessionPeripheral == null) {
					sessionPeripheral = new LinkedList<>();
				}

				Gson gson = new GsonBuilder().setDateFormat("mm/dd/yyyy").serializeNulls().create();
				String json = request.getParameter("content");

				Type collectionType = new TypeToken<ArrayList<Peripherals>>() {
				}.getType();
				List<Peripherals> addPeripheral = gson.fromJson(json, collectionType);
				String responseJson = "";
				for (Peripherals p : addPeripheral) {
					if (status.equals("Add")) {
						p.setPeripheralNo(service.getPeripheralNo());
					}
					p.setStatus(status);
					sessionPeripheral.add(p);
					session.setAttribute("sessionPeripheral", sessionPeripheral);

					// add the record to the list session
					List<Peripherals> onSession = (List<Peripherals>) session.getAttribute("list");
					if (!onSession.isEmpty()) {
						onSession.add(0, p);
						session.setAttribute("list", onSession);
						responseJson = PaginationHelper.getPagePeripherals(onSession, 0, 5, onSession.size());
					}
				}
				response.getWriter().write(responseJson);

			} catch (SQLException e) {
				e.printStackTrace();
			}
			//
		} else if (action.equals("getPeripheralRecord")) {
			try {
				List<Peripherals> peripherals = service.getPeripheralRecord(request);
				if (!peripherals.isEmpty()) {
					Gson gson = new Gson();
					String json = gson.toJson(peripherals);
					response.getWriter().write(json);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equals("getComputerAssignee")) {
			System.out.println("getting user");
			System.out.println(action);
			Integer unitNo = Integer.parseInt(request.getParameter("unitNo"));
			System.out.println(unitNo);
			try {

				System.out.println("here");
				List<ComputerAssigneeData> computerAssignee;// = new
															// LinkedList<>();
				computerAssignee = service.getComputerAssigneeData(unitNo);
				System.out.println("here");
				if (computerAssignee != null) {
					if (computerAssignee.get(0).getDeleteTag().equals("Y")) {
						System.out.println("Delete tag" + computerAssignee.get(0).getDeleteTag());
						response.setStatus(202);
						return;
					}
					Gson gson = new Gson();
					String json = gson.toJson(computerAssignee);
					response.getWriter().write(json);
				} else {
					response.setStatus(201);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		} else if (action.equals("getRecordPage")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page + " page");
			List<Peripherals> peripheralList = (List<Peripherals>) session.getAttribute("list");

			int size = ((List<Peripherals>) session.getAttribute("list")).size();

			String json = PaginationHelper.getPagePeripherals(peripheralList, page, 5, size);

			response.getWriter().write(json);
		} else if (action.equals("deleteRecord")) {
			Integer no = Integer.parseInt(request.getParameter("no"));
			String unitNo = request.getParameter("num");

			List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral");
			if (!sessionPeripheral.isEmpty()) {
				for (int a = 0; a < sessionPeripheral.size(); a++) {
					if (sessionPeripheral.get(a).getPeripheralNo() == (no)
							&& sessionPeripheral.get(0).getStatus().equals("Update")) {
						response.setStatus(204);
						return;
					} else {
						sessionPeripheral.remove(a);
					}
				}
				session.setAttribute("sessionPeripheral", sessionPeripheral);
			}
			SystemStatus stat;
			try {
				stat = service.deletePeripheral(no);
				if (stat == SystemStatus.exception) {
					response.setStatus(205);
					return;
				} else {
					if (unitNo != "") {
						peripherals = service.getPeripherals(request);
						if (!peripherals.isEmpty()) {
							if (!sessionPeripheral.isEmpty()) {
								for (Peripherals p : sessionPeripheral) {
									peripherals.add(0, p);
								}
							}
							session.setAttribute("list", peripherals);
							String json = PaginationHelper.getPagePeripherals(peripherals, 0, 5, peripherals.size());

							response.getWriter().write(json);
							return;
						}
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}

class GetSize {
	public int listSize;

	public GetSize(int listSize) {
		this.listSize = listSize;
	}

}
