package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.model.UserData;
import PDT29.homework.mantis.model.Users;
import org.testng.annotations.Test;

import java.io.IOException;

public class ChangePasswordUserTest extends TestBase {

  @Test
  public void testChangePasswordUser() throws IOException {
    /* Авторизуемся в браузере
    app.newSession().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));*/
    Users user = app.db().users();
    app.login().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manageUsers();
    app.goTo().logout();
  }

}