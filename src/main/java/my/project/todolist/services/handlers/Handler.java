package my.project.todolist.services.handlers;

import my.project.todolist.model.Task;

import java.util.List;

/**
 * Created by Nikol on 1/25/2017.
 */
public interface Handler {
    String handle (List<Task> tasks);
    String getContentType();
}
