package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.model.MailMessage;
import PDT29.homework.mantis.model.UserData;
import PDT29.homework.mantis.model.Users;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;

import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordUserTest extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testChangePasswordUser() throws IOException {
    /* Авторизуемся в браузере
    app.newSession().login(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));*/

    //Администратор авторизуется и сбрасывает пароль пользователю
    Users user = app.db().users();
    UserData selectedUser = user.iterator().next();
    app.login().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manageUsers();
    app.user().resetPassword(selectedUser);
    app.goTo().logout();
    // Пользователь получает письмо, переходит по ссылке и задаёт новый пароль
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    String changePasswordLink = findChangePasswordLink(mailMessages, selectedUser.getEmail()); //Пользователь получает письмо со ссылкой
    String newPassword = "newPassword"; //Новый пароль
    app.user().changePassword(changePasswordLink, newPassword); //Пользователь переходит по ссылке и указывает новый пароль
    //Проверяем, что пользователь может авторизоваться по новому паролю
    assertTrue(app.newSession().login(selectedUser.getUsername(), newPassword));
  }

  private String findChangePasswordLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }
}