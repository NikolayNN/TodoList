package my.project.todolist.services;

import com.thoughtworks.xstream.XStream;
import my.project.todolist.dao.DatabaseManager;
import my.project.todolist.model.Task;

import java.util.List;

/**
 * Created by Nikol on 1/8/2017.
 */
public class ServiceImpl implements Service {
    private DatabaseManager databaseManager;

    /**
     * Inject DatabaseManager.
     * @param databaseManager injected DatabaseManager.
     */
    @Override
    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
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
    public String getTasksInXML() {
        return toXML(databaseManager.getTasksList());
    }

    /**
     * The method get not completed tasks list from the database and convert the list to XML.
     * @return task list in xml.
     */
    @Override
    public String getNotCompletedTasksInXML() {
        return toXML(databaseManager.getNotCompletedTasksList());
    }

    /**
     * The method convert task list to xml/
     * @param tasks tasks list
     * @return xml.
     */
    private String toXML(List<Task> tasks) {
        XStream xStream = new XStream();
        xStream.alias("task", Task.class);
        return xStream.toXML(tasks);
    }

    /**
     * change task status with id,  to opposite.
     * @param id task id.
     */
    @Override
    public void changeTaskStatus(int id) {
        databaseManager.changeTaskStatus(id);
    }

    /**
     * close session.
     */
    @Override
    public void closeSession() {
        databaseManager.close();
    }
}
