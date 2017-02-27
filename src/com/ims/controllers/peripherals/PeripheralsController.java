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
						System.out.println("here1");
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");

						System.out.println("here2");
						peripherals = service.getPeripherals(request) == null ? new LinkedList<>()
								: service.getPeripherals(request);

						// --printing
						System.out.println("printing units");
						for (Peripherals p : peripherals) {
							System.out.println(p.getUnitNo());
						}

						System.out.println("here3");
						if (!peripherals.isEmpty()) {
							System.out.println("here4");

							// fetch sessionPeripherals
							List<Peripherals> sessionPeripherals = (List<Peripherals>) session
									.getAttribute("sessionPeripheral") == null ? new LinkedList<>()
											: (List<Peripherals>) session.getAttribute("sessionPeripheral");

							for (Peripherals p : sessionPeripherals) {
								peripherals.add(0, p);
							}

							session.setAttribute("list", peripherals);
							System.out.println("here5");

							System.out.println("here6");
							String json = PaginationHelper.getPagePeripherals(peripherals, 0, pageLimit,
									peripherals.size());

							System.out.println("here7");
							System.out.println(json);

							response.getWriter().write(json);
							return;
						} else {
							session.setAttribute("list", new LinkedList<>());
							response.setStatus(201);
						}

					} catch (SQLException e) {
						e.printStackTrace();
						response.sendError(400);
						return;
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
						response.sendError(400);
						return;
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

			} else if (action.equals("getUnits")) {
				System.out.println("getting units");
				response.setContentType("application/json");

				ComputerUnitsInventoryService serviceUnit = (ComputerUnitsInventoryService) context
						.getBean("serviceComputerUnitsInventoryBean");

				List<ComputerUnits> compUnits = serviceUnit.getComputerUnits();
				for (ComputerUnits comp : compUnits) {
					System.out.println(comp.getUnitNo());
				}
				if (!compUnits.isEmpty()) {
					Gson gson = new GsonBuilder().setDateFormat("mm/dd/yyyy").serializeNulls().create();
					String units = gson.toJson(compUnits);
					response.getWriter().write(units);
				}
			}
		} catch (

		Exception e)

		{
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
			List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral") == null
					? new LinkedList<>() : (List<Peripherals>) session.getAttribute("sessionPeripheral");

			List<Peripherals> toSave = new LinkedList<>();
			String user = (String) session.getAttribute("user_auth") == null ? ""
					: (String) session.getAttribute("user_auth");
			String unitNo = request.getParameter("unitNo");
			for (Peripherals p : sessionPeripheral) {
				p.setUserId(user);
				toSave.add(p);
			}

			SystemStatus stat;
			try {
				stat = service.savePeripheral(toSave);

				if (stat == SystemStatus.committed) {
					session.setAttribute("sessionPeripheral", new LinkedList<>());
					response.setStatus(200);
					return;
				} else if (stat == SystemStatus.exception) {
					response.setStatus(201);
					return;
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.sendError(400);
				return;
			}

		} else if (action.equals("add")) {
			try {
				List<Peripherals> sessionPeripheral = (List<Peripherals>) session
						.getAttribute("sessionPeripheral") == null ? new LinkedList<>()
								: (List<Peripherals>) session.getAttribute("sessionPeripheral");

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
					List<Peripherals> onSession = (List<Peripherals>) session.getAttribute("list") == null
							? new LinkedList<>() : (List<Peripherals>) session.getAttribute("list");

					onSession.add(0, p);
					System.out.println(p.getPeripheralNo() + " was inserted to list");
					session.setAttribute("list", onSession);

					responseJson = PaginationHelper.getPagePeripherals(onSession, 0, 5, onSession.size());

				}
				response.getWriter().write(responseJson);

			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(400);
			}
			//
		} else if (action.equals("getPeripheralRecord")) {
			try {
				System.out.println(request.getParameter("peripheralId"));
				List<Peripherals> peripherals = service.getPeripheralRecord(request);
				if (!peripherals.isEmpty()) {
					Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
					String json = gson.toJson(peripherals);
					response.getWriter().write(json);
				} else {
					response.setStatus(201);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(400);
				return;
			}
		} else if (action.equals("getComputerAssignee")) {
			System.out.println("getting user");
			System.out.println(action);
			Integer unitNo = Integer.parseInt(request.getParameter("unitNo"));
			System.out.println(unitNo);
			try {

				System.out.println("here");
				List<ComputerAssigneeData> computerAssignee = new LinkedList<>();
				computerAssignee = service.getComputerAssigneeData(unitNo);
				System.out.println("here");
				if (!computerAssignee.isEmpty()) {
					if (computerAssignee.get(0).getDeleteTag().equals("Y")) {
						System.out.println("Delete tag" + computerAssignee.get(0).getDeleteTag());
						response.setStatus(202);
						return;
					}
					Gson gson = new GsonBuilder().setDateFormat("mm/dd/yyyy").serializeNulls().create();
					String json = gson.toJson(computerAssignee);
					response.getWriter().write(json);
				} else {
					response.setStatus(201);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(400);
				return;
			}

		} else if (action.equals("getRecordPage")) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");

			int page = Integer.parseInt(request.getParameter("page"));
			System.out.println(page + " page");
			List<Peripherals> peripheralList = (List<Peripherals>) session.getAttribute("list") == null
					? new LinkedList<>() : (List<Peripherals>) session.getAttribute("list");

			int size = peripheralList.size();

			String json = PaginationHelper.getPagePeripherals(peripheralList, page, 5, size);

			response.getWriter().write(json);
		} else if (action.equals("deleteRecord")) {
			Integer no = Integer.parseInt(request.getParameter("no"));
			String unitNo = request.getParameter("num");
			System.out.println("here deleting");

			List<Peripherals> sessionPeripherals = (List<Peripherals>) session.getAttribute("sessionPeripheral") == null
					? new LinkedList<>() : (List<Peripherals>) session.getAttribute("sessionPeripheral");
			if (sessionPeripherals.size() > 0) {
				for (Peripherals ps : sessionPeripherals) {
					if (ps.getPeripheralNo() == no) {
						response.setStatus(204);
						return;
					}
				}
			}
			System.out.println("deleting.....");
			try {
				SystemStatus stat = service.deletePeripheral(no);
				if (stat == SystemStatus.committed) {
					List<Peripherals> p = new LinkedList<>();
					if (unitNo != "" || unitNo != null) {
						p = service.getPeripherals(request) == null ? new LinkedList<>()
								: service.getPeripherals(request);
					} else {
						p = service.getPeripherals() == null ? new LinkedList<>() : service.getPeripherals(request);
					}
					List<Peripherals> sessionList = (List<Peripherals>) session
							.getAttribute("sessionPeripheral") == null ? new LinkedList<>()
									: (List<Peripherals>) session.getAttribute("sessionPeripheral");
							
					System.out.println(sessionList.size());
					for (Peripherals pe : sessionList) {
						p.add(0, pe);
					}
					session.setAttribute("list", p);
					String json = PaginationHelper.getPagePeripherals(p, 0, 5, p.size());

					response.getWriter().write(json);
					response.setStatus(200);
					return;
				} else if (stat == SystemStatus.exception) {
					response.setStatus(205);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				response.sendError(400);
				return;
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
