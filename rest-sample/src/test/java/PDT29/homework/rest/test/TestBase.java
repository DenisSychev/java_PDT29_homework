package PDT29.homework.rest.test;

import PDT29.homework.rest.model.Issue;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  @BeforeClass
  private void init() {
    RestAssured.authentication = RestAssured.basic("LSGjeU4yP1X493ud1hNniA==", "");
  }

  private Executor getExecutor() {
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }

  public int createIssueForRestTest(Issue newIssue) throws IOException {
    String jsonIssues = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json")
            .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription())))
            .returnContent().asString();
    JsonElement parsedJsonIssues = new JsonParser().parse(jsonIssues);
    return parsedJsonIssues.getAsJsonObject().get("issue_id").getAsInt();
  }

  public Set<Issue> getIssuesForRestTest() throws IOException {
    String jsonIssues = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).returnContent().asString();
    JsonElement parsedJsonIssues = new JsonParser().parse(jsonIssues);
    JsonElement issues = parsedJsonIssues.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public Set<Issue> getIssuesForAssuredTest() throws IOException {
    String jsonIssues = RestAssured.get("http://demo.bugify.com/api/issues.json").asString();
    JsonElement parsedJsonIssues = new JsonParser().parse(jsonIssues);
    JsonElement issues = parsedJsonIssues.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());
  }

  public int createIssueForAssuredTest(Issue newIssue) throws IOException {
    String jsonIssues = RestAssured.given()
            .parameter("subject", newIssue.getSubject())
            .parameter("description", newIssue.getDescription())
            .post("http://demo.bugify.com/api/issues.json")
            .asString();
    JsonElement parsedJsonIssues = new JsonParser().parse(jsonIssues);
    return parsedJsonIssues.getAsJsonObject().get("issue_id").getAsInt();
  }

  /*private boolean isIssueOpen(int issueId) {

  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }*/
}