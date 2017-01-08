package my.project.todolist.controller.servlets;

import my.project.todolist.model.Task;
import my.project.todolist.services.Service;
import my.project.todolist.services.ServiceImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nikol on 1/7/2017.
 */
public class AddTask extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        Service service = (Service) request.getServletContext().getAttribute("service");
        Task task = new Task(request.getParameter("taskDescription"));
        service.addTask(task);
    }
}
