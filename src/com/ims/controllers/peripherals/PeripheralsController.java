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
import com.ims.model.peripherals.Peripherals;
import com.ims.service.peripherals.PeripheralsService;

/**
 * Servlet implementation class PeripheralsController
 */
@WebServlet("/PeripheralsController")
public class PeripheralsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<Peripherals> peripherals = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");

		try {
			int pageLimit = 5;
			String action = request.getParameter("action");
			if (action.equals("pagination")) {
				String no = request.getParameter("num");
				System.out.println(no);
				if (no != "") {
					try {
						List<Peripherals> returnPeripherals = new LinkedList<>();
						peripherals = service.getPeripherals(request);
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						for (Peripherals p : peripherals) {
							System.out.println(p.getPeripheralNo());
						}
						if (!peripherals.isEmpty()) {
							int endRow = pageLimit;
							endRow = pageLimit > peripherals.size() ? peripherals.size() : endRow;
							for (int start = 0; start < endRow; start++) {
								returnPeripherals.add(peripherals.get(start));
							}
							Gson gson = new Gson();
							String json = gson.toJson(returnPeripherals);
							response.getWriter().write(json);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				} else {
					try {
						List<Peripherals> returnPeripherals = new LinkedList<>();
						peripherals = service.getPeripherals();
						response.setContentType("application/json");
						response.setCharacterEncoding("UTF-8");
						if (!peripherals.isEmpty()) {
							int endRow = pageLimit;
							endRow = pageLimit > peripherals.size() ? peripherals.size() : endRow;
							for (int start = 0; start < endRow; start++) {
								returnPeripherals.add(peripherals.get(start));
							}
							Gson gson = new Gson();
							String json = gson.toJson(returnPeripherals);
							response.getWriter().write(json);
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			} else if (action.equals("getSize")) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				List<GetSize> getSize = new ArrayList<>();
				getSize.add(new GetSize(peripherals.size()));
				System.out.println(peripherals.size());

				Gson gson = new Gson();
				String json = gson.toJson(getSize);
				System.out.println(json);
				response.getWriter().write(json);
			} else if (action.equals("getRecordPage")) {
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				int page = Integer.parseInt(request.getParameter("page"));
				List<Peripherals> returnPeripherals = new LinkedList<>();
				if (!peripherals.isEmpty()) {
					System.out.println((page * pageLimit) + "  " + ((page * pageLimit) - pageLimit));
					int endRow = page * pageLimit;
					endRow = endRow > peripherals.size() ? peripherals.size() : endRow;
					for (int start = (page * pageLimit) - pageLimit; start < endRow; start++) {
						returnPeripherals.add(peripherals.get(start));
					}

					Gson gson = new Gson();
					String json = gson.toJson(returnPeripherals);
					response.getWriter().write(json);
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");
		String status = request.getParameter("status");
		System.out.println(action);
		System.out.println(status);
		if (action.equals("saveRecord")) {

			HttpSession session = request.getSession();

			// sample auth_user
			session.setAttribute("auth_user", "JB Mapili");

			@SuppressWarnings("unchecked")
			List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral");
			String user = (String) session.getAttribute("auth_user");
			if (sessionPeripheral != null) {
				for (Peripherals peripheral : sessionPeripheral) {
					try {
						peripheral.setUserId(user);
						if (peripheral.getStatus() == "add") {
							service.insertNewPeripherals(peripheral);
						} else {
							// update here
						}

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} else if (action.equals("add")) {
			// get sequence next value
			try {
				System.out.println("here");

				HttpSession session = request.getSession();

				@SuppressWarnings("unchecked")
				List<Peripherals> sessionPeripheral = (List<Peripherals>) session.getAttribute("sessionPeripheral");
				if (sessionPeripheral == null) {
					sessionPeripheral = new LinkedList<>();
				}
				// parse json

				Gson gson = new GsonBuilder().setDateFormat("MM/DD/YYYY").serializeNulls().create();
				String json = request.getParameter("content");
				System.out.println(json);
				Type collectionType = new TypeToken<ArrayList<Peripherals>>() {
				}.getType();
				List<Peripherals> addPeripheral = gson.fromJson(json, collectionType);
				for (Peripherals p : addPeripheral) {
					if (status.equals("Add")) {
						p.setPeripheralNo(service.getPeripheralNo());
					}
					p.setStatus(status);
					sessionPeripheral.add(p);
					session.setAttribute("sessionPeripheral", sessionPeripheral);
				}

				System.out.println("\nprinting session list");

				for (Peripherals p : sessionPeripheral) {
					System.out.println(p.getUnitNo());
					System.out.println(p.getPeripheralNo());
					System.out.println(p.getPeripheralType());
					System.out.println(p.getTagNumber());
					System.out.println(p.getAcquiredDate());
					System.out.println(p.getDescription());
					System.out.println(p.getSerialNo());
					System.out.println(p.getBrand());
					System.out.println(p.getModel());
					System.out.println(p.getColor());
					System.out.println(p.getRemarks());
					System.out.println(p.getUserId());
					System.out.println(p.getLastUpdate());
					System.out.println(p.getStatus());
				}

				response.setContentType("application/json");
				Gson resGson = new Gson();
				String resJson = resGson.toJson(addPeripheral);
				response.getWriter().write(resJson);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				// TODO Auto-generated catch block
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
