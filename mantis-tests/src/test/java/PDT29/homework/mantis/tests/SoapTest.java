package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.model.Issue;
import PDT29.homework.mantis.model.Project;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTest extends TestBase {

  @Test(enabled = false)
  public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects(); //Получем информацию о проектах из БД
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test(enabled = false)
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("New Issue from test").withDescription("Description this issue").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void someFirstTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(2); //указываем конкретный id бага для которого был написан тест
    System.out.println("Начало выполнения someTest");
    //Далее начинает работать тест
  }

  @Test
  public void someSecondTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(3); //указываем конкретный id бага для которого был написан тест
    System.out.println("Начало выполнения someTest");
    //Далее начинает работать тест
  }

  @Test
  public void someThirdTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(4); //указываем конкретный id бага для которого был написан тест
    System.out.println("Начало выполнения someTest");
    //Далее начинает работать тест
  }
}
