package my.project.todolist.controller.servlets;

import my.project.todolist.model.Task;
import my.project.todolist.services.Service;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.mockito.Mockito.*;

/**
 * Created by Nikol on 1/23/2017.
 */
public class AddTaskTest {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletContext servletContext;
    Service serviceImpl;
    AddTask addTask;

    @Before
    public void setup(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        serviceImpl = mock(Service.class);
        addTask = new AddTask();
    }

    @Test
    public void addTask() throws ServletException {
        final String taskDescription = "taskDescription";
        final String service = "service";
        when(request.getParameter(taskDescription)).thenReturn("testDescription");
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(service)).thenReturn(serviceImpl);
        addTask.doPost(request, response);
        verify(request, atLeastOnce()).getParameter(taskDescription);
        verify(request, atLeastOnce()).getServletContext();
        verify(servletContext, atLeastOnce()).getAttribute(service);
        verify(serviceImpl, atLeastOnce()).addTask(anyObject());
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTaskTaskDescription0() throws ServletException {
        final String taskDescription = "taskDescription";
        when(request.getParameter(taskDescription)).thenReturn("");
        addTask.doPost(request, response);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addTaskTaskDescriptionNULL() throws ServletException {
        final String taskDescription = "taskDescription";
        when(request.getParameter(taskDescription)).thenReturn(null);
        addTask.doPost(request, response);
    }
}