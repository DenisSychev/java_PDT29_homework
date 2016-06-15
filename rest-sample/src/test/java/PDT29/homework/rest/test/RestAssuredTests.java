package PDT29.homework.rest.test;

import PDT29.homework.rest.model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestAssuredTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssuesForAssuredTest();
    Issue newIssue = new Issue().withSubject("TestSubject").withDescription("TestDescription");
    int issueId = createIssueForAssuredTest(newIssue);
    Set<Issue> newIssues = getIssuesForAssuredTest();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
