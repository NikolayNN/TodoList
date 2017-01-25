package my.project.todolist.services;

import my.project.todolist.dao.DatabaseManager;
import my.project.todolist.model.Task;
import my.project.todolist.services.handlers.Handler;

/**
 * Created by Nikol on 1/8/2017.
 */
public interface Service {

    void addTask(Task task);

    String getTasksWithHandler();

    String getNotCompletedTasksWithHandler();

    void changeTaskStatus(int id);

    String getContentType();

    void closeSession();
    
}
