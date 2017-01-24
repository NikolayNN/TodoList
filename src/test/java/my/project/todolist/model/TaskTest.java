package my.project.todolist.model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Nikol on 1/24/2017.
 */
public class TaskTest {
    Task task;
    String taskDescription = "testTask";

    @Before
    public void setup(){
        task = new Task(taskDescription);
    }

    @Test
    public void testCreatedInstanceTimeMs(){
        assertEquals(task.getCreatedMilliseconds(), new Date().getTime()/1000);
    }

    @Test
    public void testCreatedInstanseForIsDone(){
        assertEquals(task.isDone(), false);
    }

    @Test
    public void setDone() throws Exception {
        task.setDone(true);
        assertEquals(task.isDone(), true);
    }

    @Test
    public void setId() throws Exception {
        task.setId(123);
        assertEquals(task.getId(), 123);
    }

    @Test
    public void getDescription() throws Exception {
        assertEquals(task.getDescription(), taskDescription);
    }

    @Test
    public void setCreatedMilliseconds(){
        task.setCreatedMilliseconds(123456789);
        assertEquals(task.getCreatedMilliseconds(), 123456789);
    }
}