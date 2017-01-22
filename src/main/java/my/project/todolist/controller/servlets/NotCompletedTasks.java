package my.project.todolist.controller.servlets;

import my.project.todolist.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nikol on 1/19/2017.
 */
public class NotCompletedTasks extends HttpServlet {
    /**
     * The method write to response all not completed tasks from the database.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = (Service) request.getServletContext().getAttribute("service");
        response.setContentType("text/html; charset=UTF-8");
        response.getWriter().write(service.getNotCompletedTasksInXML());
    }

}
