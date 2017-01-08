package my.project.todolist.controller.listeners;

import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.services.Service;
import my.project.todolist.services.ServiceImpl;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by Nikol on 1/7/2017.
 */
public class ServletContextListenerImpl implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Service service = new ServiceImpl();
        servletContextEvent.getServletContext().setAttribute("service", service);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Service service = (Service) servletContextEvent.getServletContext().getAttribute("service");
        service.closeSession();
    }
}
