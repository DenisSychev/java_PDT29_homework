package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.appmanager.HttpSession;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

public class LoginTests extends TestBase {

  @Test
  public void testLogin() throws IOException {
    HttpSession session = app.newSession(); //Создаётся новая сессия
    assertTrue(session.login("administrator", "root")); //Авторизуется пользователь administrator, с паролем root
    assertTrue(session.isLoggedInAs("administrator")); //Проверяется, что авторизован пользователь administrator
  }
}
