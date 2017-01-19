package my.project.todolist.dao;

import my.project.todolist.model.Task;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikol on 1/17/2017.
 */
public class HibernateManager implements DatabaseManager {

    @Override
    public void addTask(Task task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ItemEntity item = createItemFromTask(task);
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    private ItemEntity createItemFromTask(Task task){
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setDone(task.isDone());
        item.setCreated(task.getCreated());
        return item;
    }

    public List<Task> getTasksList(){
        List<Task> result = new ArrayList<>();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemEntity");
        session.getTransaction().commit();
        List list = query.list();
        for (Object o : list) {
            Task task = createTaskFromItem((ItemEntity) o);
            result.add(task);
        }
        session.close();
        return result;
    }

    @Override
    public List<Task> getNotCompletedTasksList() {
        List<Task> result = new ArrayList<>();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemEntity where done = false");
        session.getTransaction().commit();
        List list = query.list();
        for (Object o : list) {
            Task task = createTaskFromItem((ItemEntity) o);
            result.add(task);
        }
        session.close();
        return result;
    }

    private Task createTaskFromItem(ItemEntity itemEntity){
        Task task = new Task(itemEntity.getDescription());
        task.setId(itemEntity.getId());
        task.setCreated(itemEntity.getCreated());
        task.setDone(itemEntity.getDone());
        return task;
    }

    public void close(){
        HibernateSessionFactory.shutdown();
    }

}
