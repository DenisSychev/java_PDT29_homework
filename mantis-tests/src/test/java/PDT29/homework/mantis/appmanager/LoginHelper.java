package PDT29.homework.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends BaseHelper{

  public LoginHelper(ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.xpath(".//input[@value='Login']"));
  }
}
