package my.project.todolist.controller.listeners;

import my.project.todolist.dao.HibernateManager;
import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.services.Service;
import my.project.todolist.services.ServiceImpl;
import my.project.todolist.services.handlers.JsonHandler;
import my.project.todolist.services.handlers.XMLHandler;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Nikol on 1/7/2017.
 */
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Service service = new ServiceImpl(new HibernateManager(), new JsonHandler());
        servletContextEvent.getServletContext().setAttribute("service", service);
        //todo
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Service service = (Service) servletContextEvent.getServletContext().getAttribute("service");
        service.closeSession();
    }
}
