package my.project.todolist.services;

import com.thoughtworks.xstream.XStream;
import my.project.todolist.dao.DatabaseManager;
import my.project.todolist.model.Task;
import my.project.todolist.services.handlers.Handler;

import java.util.List;

/**
 * Created by Nikol on 1/8/2017.
 */
public class ServiceImpl implements Service {
    /**
     * injected database manager.
     */
    private DatabaseManager databaseManager;
    private Handler handler;

    public ServiceImpl(DatabaseManager databaseManager, Handler handler) {
        this.databaseManager = databaseManager;
        this.handler = handler;
    }

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    /**
     * The method add task to the database
     * @param task task
     */
    @Override
    public void addTask(Task task) {
        databaseManager.addTask(task);
    }

    /**
     * The method get tasks list from the database and convert the list to XML.
     * @return task list in xml.
     */
    @Override
    public String getTasksWithHandler() {
        return handler.handle(databaseManager.getTasksList());
    }

    /**
     * The method get not completed tasks list from the database and convert the list to XML.
     * @return task list in xml.
     */
    @Override
    public String getNotCompletedTasksWithHandler() {
        return handler.handle(databaseManager.getNotCompletedTasksList());
    }

    /**
     * change task status with id,  to opposite.
     * @param id task id.
     */
    @Override
    public void changeTaskStatus(int id) {
        databaseManager.changeTaskStatus(id);
    }

    @Override
    public String getContentType() {
        return handler.getContentType();
    }

    /**
     * close session.
     */
    @Override
    public void closeSession() {
        databaseManager.close();
    }
}
