package my.project.todolist;

import my.project.todolist.dao.ItemEntity;
import my.project.todolist.model.Task;
import org.hibernate.Session;

/**
 * Created by Nikol on 1/5/2017.
 */
public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ItemEntity item = new ItemEntity();
        item.setDescription("test");
        item.setCreated(123456);
        item.setDone(false);

        session.save(item);
        session.getTransaction().commit();
        System.out.println("done");
        session.close();
    }
}