package my.project.todolist.services;

import com.thoughtworks.xstream.XStream;
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


    @Override
    public void addTask(Task task){
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ItemEntity item = new ItemEntity();
        item.setDescription(task.getDescription());
        item.setCreated(task.getCreated());
        item.setDone(task.isDone());
        session.save(item);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public String getTasksXML(){
        List<Task> result = new ArrayList<>();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("from ItemEntity");
        List list = query.list();
        session.getTransaction().commit();
        for (Object o : list) {
            ItemEntity itemEntity = (ItemEntity) o;
            Task task = new Task(itemEntity.getDescription());
            task.setId(itemEntity.getId());
            task.setCreated(itemEntity.getCreated());
            task.setDone(itemEntity.getDone());
            result.add(task);
        }
        session.close();
        return toXML(result);
    }

    public String toXML(List<Task> tasks) {
        XStream xStream = new XStream();
        xStream.alias("task", Task.class);
        return xStream.toXML(tasks);
    }


    @Override
    public void closeSession() {

    }
}
