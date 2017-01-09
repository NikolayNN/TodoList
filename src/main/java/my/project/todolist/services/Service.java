package my.project.todolist.services;

import my.project.todolist.model.Task;

/**
 * Created by Nikol on 1/8/2017.
 */
public interface Service {

    void addTask(Task task);

    String getTasksXML();

    void closeSession();
}
