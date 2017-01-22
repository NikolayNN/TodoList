package my.project.todolist.controller.servlets;

import my.project.todolist.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Nikol on 1/21/2017.
 */
public class ChangeTaskStatus extends HttpServlet {
    /**
     * The method accepts parameter taskId from request and change
     * task status in the database on opposite.
     * @param request
     * @param response
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        Service service = (Service) request.getServletContext().getAttribute("service");
        String parameter = request.getParameter("taskId");
        service.changeTaskStatus(Integer.parseInt(parameter));
    }
}
