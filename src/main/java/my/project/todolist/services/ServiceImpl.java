package my.project.todolist.services;

import com.thoughtworks.xstream.XStream;
import my.project.todolist.dao.DatabaseManager;
import my.project.todolist.dao.HibernateManager;
import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.dao.ItemEntity;
import my.project.todolist.model.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 1/8/2017.
 */
public class ServiceImpl implements Service {
    private DatabaseManager databaseManager;

    public void setDatabaseManager(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public void addTask(Task task){
        databaseManager.addTask(task);
    }

    @Override
    public String getTasksInXML(){
        return toXML(databaseManager.getTasksList());
    }

    private String toXML(List<Task> tasks) {
        XStream xStream = new XStream();
        xStream.alias("task", Task.class);
        return xStream.toXML(tasks);
    }

    @Override
    public void closeSession() {
        databaseManager.close();
    }
}
