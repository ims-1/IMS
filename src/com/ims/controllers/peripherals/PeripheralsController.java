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

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
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

		int pageLimit = 5;
		String action = request.getParameter("action");
		if (action.equals("pagination")) {
			System.out.println("here1");
			int page = Integer.parseInt(request.getParameter("page"));
			try {
				List<Peripherals> returnPeripherals = new LinkedList<>();
				peripherals = service.getPeripherals();

				if (!peripherals.isEmpty()) {

					for (int start = 0; start < pageLimit; start++) {
						returnPeripherals.add(peripherals.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnPeripherals);
					response.getWriter().write(json);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equals("getSize")) {
			List<GetSize> getSize = new ArrayList<>();
			getSize.add(new GetSize(peripherals.size()));
			System.out.println(peripherals.size());

			Gson gson = new Gson();
			String json = gson.toJson(getSize);
			System.out.println(json);
			response.getWriter().write(json);
		} else if (action.equals("getRecordPage")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<Peripherals> returnPeripherals = new LinkedList<>();
			if (!peripherals.isEmpty()) {
				System.out.println((page * pageLimit) + "  " + ((page * pageLimit) - pageLimit));

				for (int start = (page * pageLimit) - pageLimit; start < (page * pageLimit); start++) {
					returnPeripherals.add(peripherals.get(start));
				}

				Gson gson = new Gson();
				String json = gson.toJson(returnPeripherals);
				response.getWriter().write(json);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		PeripheralsService service = (PeripheralsService) context.getBean("servicePeripheralsBean");

		String action = request.getParameter("action");
		if (action.equals("save")) {
			Gson gson = new Gson();
			String json = request.getParameter("peripherals");
			Type collectionType = new TypeToken<ArrayList<Peripherals>>() {
			}.getType();
			List<Peripherals> peripherals = new Gson().fromJson(json, collectionType);

			for (Peripherals p : peripherals) {
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
			}
		} else if (action.equals("insert")) {
			
		}
	}

}

class GetSize {
	public int listSize;

	public GetSize(int listSize) {
		this.listSize = listSize;
	}

}
