package PDT29.homework.rest.test;

import PDT29.homework.rest.model.Issue;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase{

  @Test
  public void testCreateIssue() throws IOException {
    Set<Issue> oldIssues = getIssuesForRestTest();
    Issue newIssue = new Issue().withSubject("TestSubject").withDescription("TestDescription");
    int issueId = createIssueForRestTest(newIssue);
    Set<Issue> newIssues = getIssuesForRestTest();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }
}
