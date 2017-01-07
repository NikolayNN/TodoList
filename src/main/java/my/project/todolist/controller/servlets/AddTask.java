package my.project.todolist.controller.servlets;

import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.dao.ItemEntity;
import my.project.todolist.model.Task;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Nikol on 1/7/2017.
 */
public class AddTask extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.setContentType("text/html");
        Task task = new Task(request.getParameter("taskDescription"));
        Session session = HibernateSessionFactory.getSessionFactory().openSession(); //save session in servlet context
        session.beginTransaction();
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setCreated(task.getCreated());
        item.setDone(task.isDone());
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
}
