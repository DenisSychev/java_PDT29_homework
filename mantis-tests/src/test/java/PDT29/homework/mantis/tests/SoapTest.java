package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.model.Issue;
import PDT29.homework.mantis.model.Project;
import org.testng.annotations.Test;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTest extends TestBase{

  @Test
  public void testGetProject () throws MalformedURLException, ServiceException, RemoteException {
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
}
