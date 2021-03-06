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
    /**
     * Hibernate session factory.
     */
    private static SessionFactory sessionFactory;

    public HibernateManager() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    /**
     * The method accepts task. Cast the task to a ItemEntity and save in the database.
     * @param task task
     */
    @Override
    public void addTask(Task task) {
        Session session = sessionFactory.openSession();
        ItemEntity item = createItemFromTask(task);
        session.save(item);
        session.close();
    }

    /**
     * The method convert the task to the itemEntity
     * @param task task
     * @return itemEntity
     */
    private ItemEntity createItemFromTask(Task task) {
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setDone(task.isDone());
        item.setCreated(task.getCreatedMilliseconds());
        return item;
    }

    /**
     * The method get tasks list from the database ordered by id.
     * @return tasks list.
     */
    @Override
    public List<Task> getTasksList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity order by id");
        List<Object> list = query.list();
        session.close();
        return convertToTasksList(list);
    }

    /**
     * The method get not completed tasks from the database.
     * @return not completed tasks list.
     */
    @Override
    public List<Task> getNotCompletedTasksList() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity where done = false");
        List<Object> list = query.list();
        session.close();
        return convertToTasksList(list);
    }

    /**
     * The method casts the List<Object> to the List<Tasks>.
     * @param list list with Objects
     * @return list with Task
     */
    private List<Task> convertToTasksList(List<Object> list) {
        List<Task> result = new ArrayList<>();
        for (Object o : list) {
            Task task = createTaskFromItem((ItemEntity) o);
            result.add(task);
        }
        return result;
    }

    /**
     * The method cast itemEntity to task.
     * @param itemEntity itemEntity.
     * @return task.
     */
    private Task createTaskFromItem(ItemEntity itemEntity) {
        Task task = new Task(itemEntity.getDescription());
        task.setId(itemEntity.getId());
        task.setCreatedMilliseconds(itemEntity.getCreated());
        task.setDone(itemEntity.getDone());
        return task;
    }

    /**
     * The method change task status on opposite on the database.
     * @param taskId taskId
     */
    @Override
    public void changeTaskStatus(int taskId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update ItemEntity set done = " + changedTaskStatus(taskId) +
                " where id = " + taskId);
        query.executeUpdate();
        session.close();
    }

    /**
     * change task status
     * @param taskId taskId
     * @return opposite status
     */
    private boolean changedTaskStatus(int taskId) {
        return !getIsDone(taskId);
    }

    /**
     * get current task status from the database.
     * @param taskId taskId
     * @return current task status.
     */
    private boolean getIsDone(int taskId) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from ItemEntity where id = " + taskId);
        ItemEntity itemEntity = (ItemEntity) query.getSingleResult();
        return itemEntity.getDone();
    }

    /**
     * The method shutdown hibernate session factory.
     */
    @Override
    public void close() {
        HibernateSessionFactory.shutdown();
    }
}
