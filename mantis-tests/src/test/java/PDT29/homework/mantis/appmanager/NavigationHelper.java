package PDT29.homework.mantis.appmanager;


import org.openqa.selenium.By;

public class NavigationHelper extends BaseHelper{

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageUsers() {
    click(By.xpath(".//*[@class='bracket-link']//a[contains(@href,'manage_user_page.php')]"));
  }
}
