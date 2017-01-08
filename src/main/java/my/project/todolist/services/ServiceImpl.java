package my.project.todolist.services;

import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.dao.ItemEntity;
import my.project.todolist.model.Task;
import org.hibernate.Session;

/**
 * Created by Nikol on 1/8/2017.
 */
public class ServiceImpl implements Service {
    Session session;

    @Override
    public void addTask(Task task){
        openSession();
        session.beginTransaction();
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setCreated(task.getCreated());
        item.setDone(task.isDone());
        session.save(item);
        session.getTransaction().commit();
    }

    private void openSession(){
        if (session == null){
            session = HibernateSessionFactory.getSessionFactory().openSession();
        }
    }

    @Override
    public void closeSession(){
        session.close();
    }


}
