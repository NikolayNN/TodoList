package my.project.todolist.controller.servlets;

import my.project.todolist.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nikol on 1/8/2017.
 */
public class AllTasks extends HttpServlet {
    /**
     * The method read all tasks in the database and send to the web in XML.
     * @param request
     * @param response 
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service service = (Service) request.getServletContext().getAttribute("service");
        response.setContentType(service.getContentType() + "; charset=UTF-8");
        response.getWriter().write(service.getTasksWithHandler());
    }
}
