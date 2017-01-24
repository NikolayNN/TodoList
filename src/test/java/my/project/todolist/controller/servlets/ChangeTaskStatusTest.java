package my.project.todolist.controller.servlets;

import my.project.todolist.services.Service;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Nikol on 1/24/2017.
 */
public class ChangeTaskStatusTest {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletContext servletContext;
    Service serviceImpl;
    ChangeTaskStatus changeTaskStatus;

    @Before
    public void setup(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        serviceImpl = mock(Service.class);
        changeTaskStatus = new ChangeTaskStatus();
    }

    @Test
    public void addTask() throws ServletException {
        final String taskIdParameterName = "taskId";
        final String serviceParameterName = "service";
        when(request.getParameter(taskIdParameterName)).thenReturn("1");
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(serviceParameterName)).thenReturn(serviceImpl);
        changeTaskStatus.doPost(request, response);
        verify(request, atLeastOnce()).getParameter(taskIdParameterName);
        verify(request, atLeastOnce()).getServletContext();
        verify(servletContext, atLeastOnce()).getAttribute(serviceParameterName);
        verify(serviceImpl, atLeastOnce()).changeTaskStatus(1);
    }

}