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

  @Test
  public void testGetProject() throws MalformedURLException, ServiceException, RemoteException {
    Set<Project> projects = app.soap().getProjects(); //Получем информацию о проектах из БД
    System.out.println(projects.size());
    for (Project project : projects) {
      System.out.println(project.getName());
    }
  }

  @Test
  public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("New Issue from test").withDescription("Description this issue").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test(enabled = false)
  public void someTest() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(0001); //указываем конкретный id бага для которого был написан тест
    System.out.println("Начало выполнения someTest");
    //Далее начинает работать тест
  }
}
