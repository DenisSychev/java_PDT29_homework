package PDT29.homework.mantis.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import PDT29.homework.mantis.model.Users;
import PDT29.homework.mantis.model.UserData;

import java.util.List;

public class DBHelper {

  private final SessionFactory sessionFactory;

  public DBHelper(){
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // берёт параметры из hibernate.cfg.xml
            .build();
    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
  }

  public Users users(){
    Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData where username != 'administrator'").list();
    session.getTransaction().commit();
    session.close();
    return new Users(result);
  }
}
