package my.project.todolist.controller.servlets;


import my.project.todolist.services.Service;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

/**
 * Created by Nikol on 1/24/2017.
 */
public class AllTasksTest {
    HttpServletRequest request;
    HttpServletResponse response;
    ServletContext servletContext;
    Service serviceImpl;
    AllTasks allTasks;
    PrintWriter writer;


    @Before
    public void setup(){
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        servletContext = mock(ServletContext.class);
        serviceImpl = mock(Service.class);
        writer = mock(PrintWriter.class);
        allTasks = new AllTasks();
    }

    @Test
    public void getAllTasksTest() throws ServletException, IOException {
        final String serviceParameterName = "service";
        final String testString = "<task>testTasks</task>";
        when(request.getServletContext()).thenReturn(servletContext);
        when(servletContext.getAttribute(serviceParameterName)).thenReturn(serviceImpl);
        when(response.getWriter()).thenReturn(writer);
        when(serviceImpl.getTasksInXML()).thenReturn(testString);

        allTasks.doPost(request, response);

        verify(request, atLeastOnce()).getServletContext();
        verify(servletContext, atLeastOnce()).getAttribute(serviceParameterName);
        verify(serviceImpl, atLeastOnce()).getTasksInXML();
        verify(writer, atLeastOnce()).write(testString);
    }
}