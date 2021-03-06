package PDT29.homework.mantis.appmanager;

import PDT29.homework.mantis.model.UserData;
import org.openqa.selenium.By;

public class UserHelper extends BaseHelper{

  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public void resetPassword(UserData user) {
    selectUserById(user.getId());
    resetPassword();
  }

  private void resetPassword() {
    click(By.xpath("//input[@value='Reset Password']"));
  }

  private void selectUserById(int id) {
    wd.findElement(By.xpath("//a[@href=\"manage_user_edit_page.php?user_id="+ id +"\"]")).click();
  }

  public void changePassword(String changePasswordLink, String newPassword) {
    wd.get(changePasswordLink);
    type(By.name("password"), newPassword);
    type(By.name("password_confirm"), newPassword);
    click(By.xpath(".//input[@value='Update User']"));
  }
}
