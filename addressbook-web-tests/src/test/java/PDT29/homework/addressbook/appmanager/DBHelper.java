package PDT29.homework.addressbook.appmanager;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import PDT29.homework.addressbook.model.GroupData;
import PDT29.homework.addressbook.model.Groups;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

  public DBHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // берёт параметры из hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Groups groups(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<GroupData> result = session.createQuery("from GroupData").list();
    session.getTransaction().commit();
    session.close();
    return new Groups(result);
  }

  public Contacts contacts(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<ContactData> result = session.createQuery("from ContactData where deprecated = '0000-00-00'").list();
    session.getTransaction().commit();
    session.close();
    return new Contacts(result);
  }

}
