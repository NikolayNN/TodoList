package my.project.todolist;

import my.project.todolist.dao.HibernateSessionFactory;
import my.project.todolist.dao.ItemEntity;
import org.hibernate.Session;

/**
 * Created by Nikol on 1/5/2017.
 */
public class AppMain {

    public static void main(String[] args) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();
        ItemEntity item = new ItemEntity();
        item.setDescription("testLong");
        item.setCreated(123456l);
        item.setDone(false);

        session.save(item);
        session.getTransaction().commit();
        session.close();
    }
}