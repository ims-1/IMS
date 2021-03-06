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
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ims.controllers.unitassignment.GetSize;
import com.ims.entity.computerunitsinventory.ComputerUnits;
import com.ims.model.unitassignment.Assignee;
import com.ims.model.unitassignment.UnitAssignment;
import com.ims.model.unitassignmenthist.UnitAssignmentHist;
import com.ims.service.computerunitsinventory.ComputerUnitsInventoryService;
import com.ims.service.unitassignment.UnitAssignmentService;
import com.ims.service.unitassignmenthist.UnitAssignmentHistService;
import com.ims.utilities.FilterRecord;
import com.ims.utilities.PaginationHelper;

@WebServlet("/UnitAssignmentController")
public class UnitAssignmentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    List<Assignee> assigneeList = null;

    @SuppressWarnings({ "unchecked", "resource", "unused" })
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
        UnitAssignmentHistService serviceHist = (UnitAssignmentHistService) context
                .getBean("serviceUnitAssignmentHistBean");
        UnitAssignmentService service = (UnitAssignmentService) context.getBean("serviceUnitAssignmentBean");

        ComputerUnitsInventoryService computerUnitService = (ComputerUnitsInventoryService) context
                .getBean("serviceComputerUnitsInventoryBean");

        int pageLimit = 5;
        String action = request.getParameter("action");

        System.out.println(action);

        HttpSession session = request.getSession();

        if (action.equals("pagination")) {

            // String no = request.getParameter("num");
            // if (no != "") {
            try {

                List<UnitAssignmentHist> unitHist = new LinkedList<>();

                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                System.out.println("here1");
                unitHist = serviceHist.getUnitAssignmentHist();
                System.out.println(unitHist);
                session.setAttribute("list", unitHist);

                System.out.println("here2");
                String json = PaginationHelper.getPageUnitAssignmentHist(unitHist, 0, pageLimit, unitHist.size());
                System.out.println(unitHist.isEmpty());
                System.out.println("here1");
                response.getWriter().write(json);

            } catch (SQLException e) {

                e.printStackTrace();
                response.sendError(400);
                return;
            }

        } else if (action.equals("getSize")) {

            List<UnitAssignmentHist> unitHist = (List<UnitAssignmentHist>) session.getAttribute("list");

            List<GetSize> getSize = new ArrayList<>();
            System.out.println(getSize);

            getSize.add(new GetSize(unitHist.size()));
            System.out.println(unitHist.size());

            Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
            String json = gson.toJson(getSize);
            System.out.println(json);
            response.getWriter().write(json);

        } else if (action.equals("getRecordPage")) {

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            int page = Integer.parseInt(request.getParameter("page"));
            List<UnitAssignmentHist> unitHistList = (List<UnitAssignmentHist>) session.getAttribute("list");
            int size = ((List<UnitAssignmentHist>) session.getAttribute("list")).size();

            String json = PaginationHelper.getPageUnitAssignmentHist(unitHistList, page, 5, size);

            response.getWriter().write(json);

        } else if (action.equals("getFilteredRecord")) {
            String words = request.getParameter("filterText").toUpperCase();
            List<UnitAssignmentHist> unitHist = null;
            List<UnitAssignmentHist> filteredUnitHist = null;
            unitHist = (List<UnitAssignmentHist>) session.getAttribute("list");

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            String json = "";
            if (!unitHist.isEmpty()) {
                filteredUnitHist = FilterRecord.getFilterUnitAssignmentHist(unitHist, words);
            }

            session.setAttribute("filteredList", filteredUnitHist);

            if (!filteredUnitHist.isEmpty()) {
                json = PaginationHelper.getPageUnitAssignmentHist(filteredUnitHist, 0, pageLimit, unitHist.size());
                System.out.println(filteredUnitHist.isEmpty());
                System.out.println("here1");
            }
            response.getWriter().write(json);

        } else if (action.equals("getFilteredUnitAssignmentSize")) {
            List<UnitAssignmentHist> filteredUnitHist = null;
            filteredUnitHist = (List<UnitAssignmentHist>) session.getAttribute("filteredList");

            List<GetSize> getSize = new ArrayList<>();
            getSize.add(new GetSize(filteredUnitHist.size()));
            System.out.println(filteredUnitHist.size());

            Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
            String json = gson.toJson(getSize);
            System.out.println(json);
            response.getWriter().write(json);

        } else if (action.equals("getFilteredUnitAssignmentPage")) {

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            int page = Integer.parseInt(request.getParameter("page"));
            List<UnitAssignmentHist> unitHistList = (List<UnitAssignmentHist>) session.getAttribute("filteredList");
            String json = "";
            if (!unitHistList.isEmpty()) {
                json = PaginationHelper.getPageUnitAssignmentHist(unitHistList, page, 5, unitHistList.size());
            }
            response.getWriter().write(json);
        } else if (action.equals("findName")) {
            System.out.println("start");
            List<Assignee> assigneeList = new LinkedList<>();
            try {
                assigneeList = service.getAssigneeList();
                Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
                String json = gson.toJson(assigneeList);
                System.out.println(json);
                response.getWriter().write(json);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response.sendError(400);
                return;
            }

        } else if (action.equals("fetchUnits")) {
            System.out.println("start");
            List<ComputerUnits> compUnit = new LinkedList<>();
            try {
                compUnit = computerUnitService.getComputerUnits();

                Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
                String json = gson.toJson(compUnit);
                System.out.println(json);
                response.getWriter().write(json);

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response.sendError(400);
                return;
            }

        } else if (action.equals("populate")) {

            String selectUnit = request.getParameter("selectUnits");
            List<ComputerUnits> compUnits = null;

            System.out.println(selectUnit);

            try {
                compUnits = computerUnitService.getComputerUnitByUnitNo(Integer.parseInt(selectUnit));
                if (!compUnits.isEmpty()) {
                    System.out.println("huh");

                    Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();

                    String json = gson.toJson(compUnits);
                    System.out.println(json);
                    response.getWriter().write(json);

                    System.out.println("huhe");
                } else {
                    response.setStatus(201);
                    return;
                }

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        else if (action.equals("populateAssignee")) {
            System.out.println("here");
            String selectAssignee = request.getParameter("selectAssignee");

            List<Assignee> assigneeList = null;

            System.out.println(assigneeList);

            try {
                assigneeList = service.getAssignee(selectAssignee);

                System.out.println("foundAssignee");

                Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();
                String json = gson.toJson(assigneeList);
                System.out.println(json);
                response.getWriter().write(json);
                System.out.println("fetched Assignee");

            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                response.sendError(400);
                return;
            }

        }

        else if (action.equals("fetch")) {

            String assigneeNum = request.getParameter("selectUnits");
            System.out.println(assigneeNum);
            int page = Integer.parseInt(request.getParameter("page"));
            try {
                List<UnitAssignmentHist> unitHist = new LinkedList<>();
                // unitHist = (List<UnitAssignmentHist>)
                // session.getAttribute("list");

                unitHist = serviceHist.getUnitHist(assigneeNum);
                if (!unitHist.isEmpty()) {
                    session.setAttribute("list", unitHist);
                    String json = PaginationHelper.getPageUnitAssignmentHist(unitHist, 0, 5, unitHist.size());
                    response.getWriter().write(json);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(400);
                return;
            }
        } else if (action.equals("fromCompUnit")) {
            String assigneeNum = request.getParameter("selectUnits");
            System.out.println(assigneeNum);
            int page = Integer.parseInt(request.getParameter("page"));
            try {
                List<UnitAssignmentHist> unitHist = null;
                unitHist = (List<UnitAssignmentHist>) session.getAttribute("list");
                unitHist = serviceHist.getUnitHist(assigneeNum);
                String json = PaginationHelper.getPageUnitAssignmentHist(unitHist, 0, 10, unitHist.size());
                response.getWriter().write(json);

            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(400);
                return;
            }
        }
    }

    @SuppressWarnings("resource")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ApplicationContext context = new ClassPathXmlApplicationContext("/com/ims/resource/beans.xml");
        UnitAssignmentService service = (UnitAssignmentService) context.getBean("serviceUnitAssignmentBean");
        UnitAssignmentHistService serviceHist = (UnitAssignmentHistService) context
                .getBean("serviceUnitAssignmentHistBean");
        String action = request.getParameter("action");
        String actionTwo = request.getParameter("actionTwo");

        System.out.println(actionTwo);
        System.out.println(action);
        if (actionTwo.equals("assignToHistData")) {
            HttpSession sessionUser = request.getSession();
            String user = (String) sessionUser.getAttribute("user_auth");
            String json = request.getParameter("unitassignmenthist");
            System.out.println(json);
            Type collectionType = new TypeToken<ArrayList<UnitAssignmentHist>>() {
            }.getType();

            Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();

            List<UnitAssignmentHist> unitAssignmentHist = gson.fromJson(json, collectionType);

            for (UnitAssignmentHist unitHist : unitAssignmentHist) {
                try {
                    unitHist.setUserId(user);
                    unitHist.setAssignedBy(user);

                    serviceHist.updateUnit(unitHist); // update assign_tag to
                                                        // 'N'
                    serviceHist.insertNewAssigneeHist(unitHist); // insert new
                                                                    // unit in
                                                                    // history
                                                                    // table
                    System.out.println("tapos na din sa history");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    response.sendError(400);
                    return;
                }
            }
        }

        if (action.equals("assignToDatabase")) {
            HttpSession sessionUser = request.getSession();
            String user = (String) sessionUser.getAttribute("user_auth");

            String json = request.getParameter("unitassignment");

            Gson gson = new GsonBuilder().setDateFormat("MM/dd/YYYY").serializeNulls().create();

            Type collectionType = new TypeToken<ArrayList<UnitAssignment>>() {
            }.getType();
            List<UnitAssignment> unitassignment = gson.fromJson(json, collectionType);

            for (UnitAssignment unit : unitassignment) {
                try {
                    unit.setAssignedBy(user);
                    unit.setUserId(user);
                    service.deleteUnit(unit); // delete existing unit
                    service.insertNewAssignee(unit); // insert new unit
                    System.out.println("tapos sa unitassignment");
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    response.sendError(400);
                    return;
                }
            }
            /*
             * Type collectionTypeHist = new
             * TypeToken<ArrayList<UnitAssignment>>() { }.getType();
             *
             * List<UnitAssignmentHist> unitassignmenthist = new
             * Gson().fromJson(json, collectionTypeHist); for(UnitAssignmentHist
             * unitHist : unitassignmenthist) { try {
             * serviceHist.insertNewAssigneeHist(unitHist); } catch
             * (SQLException e) { // TODO Auto-generated catch block
             * e.printStackTrace(); } }
             */
        }

    }
}

class GetSize {
    public int listSize;

    public GetSize(int listSize) {
        this.listSize = listSize;
    }

}