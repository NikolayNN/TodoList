package my.project.todolist.dao;

import my.project.todolist.model.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 1/17/2017.
 */
public class HibernateManager implements DatabaseManager {
    private SessionFactory sessionFactory;

    public HibernateManager() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    @Override
    public void addTask(Task task) {
        Session session = sessionFactory.openSession();
        ItemEntity item = createItemFromTask(task);
        session.save(item);
        session.close();
    }

    private ItemEntity createItemFromTask(Task task) {
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setDone(task.isDone());
        item.setCreated(task.getCreated());
        return item;
    }

    @Override
    public List<Task> getTasksList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity order by id");
        List<Object> list = query.list();
        session.close();
        return convertToTasksList(list);
    }

    @Override
    public List<Task> getNotCompletedTasksList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity where done = false");
        List<Object> list = query.list();
        session.close();
        return convertToTasksList(list);
    }

    private List<Task> convertToTasksList(List list) {
        List<Task> result = new ArrayList<>();
        for (Object o : list) {
            Task task = createTaskFromItem((ItemEntity) o);
            result.add(task);
        }
        return result;
    }

    private Task createTaskFromItem(ItemEntity itemEntity) {
        Task task = new Task(itemEntity.getDescription());
        task.setId(itemEntity.getId());
        task.setCreated(itemEntity.getCreated());
        task.setDone(itemEntity.getDone());
        return task;
    }

    @Override
    public void changeTaskStatus(int taskId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update ItemEntity set done = " + changedTaskStatus(taskId) +
                " where id = " + taskId);
        query.executeUpdate();
        session.close();
    }

    private boolean changedTaskStatus(int taskId) {
        return !getIsDone(taskId);
    }

    private boolean getIsDone(int taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity where id = " + taskId);
        ItemEntity itemEntity = (ItemEntity) query.getSingleResult();
        return itemEntity.getDone();
    }

    @Override
    public void close() {
        HibernateSessionFactory.shutdown();
    }
}
