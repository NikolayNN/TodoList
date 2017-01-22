package my.project.todolist.controller.servlets;

import my.project.todolist.model.Task;
import my.project.todolist.services.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nikol on 1/7/2017.
 */
public class AddTask extends HttpServlet {
    /**
     * Accept from request parameter taskDescription check for empty and create
     * new task in the database by service.
     * if taskDescription is empty, will throw IllegalArgumentException.
     * @param request
     * @param response
     * @throws ServletException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String taskDescription = request.getParameter("taskDescription");
        if(taskDescription.length() == 0){
            throw new IllegalArgumentException("Description can not be empty.");
        }
        Service service = (Service) request.getServletContext().getAttribute("service");
        service.addTask(new Task(taskDescription));
    }


}
