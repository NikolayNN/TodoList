package my.project.todolist.dao;

import my.project.todolist.model.Task;

import java.util.List;

/**
 * Created by Nikol on 1/17/2017.
 */
public interface DatabaseManager {
    void addTask(Task task);
    List<Task> getTasksList();
    List<Task> getNotCompletedTasksList();
    void close();
    void changeTaskStatus(int id);
}
