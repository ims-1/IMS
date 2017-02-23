package com.ims.controllers.unitassignment;

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
import com.ims.controllers.unitassignment.GetSize;
import com.ims.model.unitassignment.UnitAssignment;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;
import com.ims.service.unitassignment.UnitAssignmentService;
import com.ims.service.unitassignmenthist.UnitAssignmentHistService;


@WebServlet("/UnitAssignmentController")
public class UnitAssignmentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<UnitAssignmentHist> unitHist = null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UnitAssignmentHistService serviceHist = (UnitAssignmentHistService) context.getBean("serviceUnitAssignmentHistBean");
		
		int pageLimit = 5;
		String action = request.getParameter("action");
		if (action.equals("pagination")) {
			System.out.println("here1");
			int page = Integer.parseInt(request.getParameter("page"));
			try {
				List<UnitAssignmentHist> returnUnitHist = new LinkedList<>();
				unitHist = serviceHist.getUnitAssignmentHist();

				if (!unitHist.isEmpty()) {

					for (int start = 0; start < pageLimit; start++) {
						returnUnitHist.add(unitHist.get(start));
					}
					Gson gson = new Gson();
					String json = gson.toJson(returnUnitHist);
					response.getWriter().write(json);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (action.equals("getSize")) {
			List<GetSize> getSize = new ArrayList<>();
			getSize.add(new GetSize(unitHist.size()));
			System.out.println(unitHist.size());

			Gson gson = new Gson();
			String json = gson.toJson(getSize);
			System.out.println(json);
			response.getWriter().write(json);
		} else if (action.equals("getRecordPage")) {
			int page = Integer.parseInt(request.getParameter("page"));
			List<UnitAssignmentHist> returnUnitHist = new LinkedList<>();
			if (!unitHist.isEmpty()) {
				System.out.println((page * pageLimit) + "  " + ((page * pageLimit) - pageLimit));

				for (int start = (page * pageLimit) - pageLimit; start < (page * pageLimit); start++) {
					returnUnitHist.add(unitHist.get(start));
				}

				Gson gson = new Gson();
				String json = gson.toJson(returnUnitHist);
				response.getWriter().write(json);
			}
		}

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
		UnitAssignmentService service = (UnitAssignmentService) context.getBean("serviceUnitAssignmentBean");
		
		String action = request.getParameter("action");
		
		if(action.equals("actions"))
		{
			Gson gson = new Gson();
			String json = request.getParameter("unitassignment");
			Type collectionType = new TypeToken<ArrayList<UnitAssignment>>() {}.getType();
			List<UnitAssignment> unitassignment = new Gson().fromJson(json, collectionType);
		
			for(UnitAssignment unit : unitassignment){
				System.out.println(unit.getUnitNo());
				System.out.println(unit.getAssigneeNo());
				System.out.println(unit.getLocation());
				System.out.println(unit.getStatus());
				System.out.println(unit.getIpAddress());
				System.out.println(unit.getAssignedBy());
				System.out.println(unit.getAssignedDate());
				System.out.println(unit.getUserId());
				System.out.println(unit.getLastUpdate());

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
