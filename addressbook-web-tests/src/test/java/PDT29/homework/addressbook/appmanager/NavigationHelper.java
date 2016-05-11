package PDT29.homework.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends BaseHelper {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {
    if (isElementPresent(By.xpath(".//*[@id='content']/h1"))
            && wd.findElement(By.xpath(".//*[@id='content']/h1")).getText().equals("Groups")
            && isElementPresent(By.xpath(".//input[@value='New group']"))) {
      return;
    }
    click(By.linkText("groups"));
  }

  public void homepage() {
    if (isElementPresent(By.xpath(".//table[@id='maintable']"))) {
      return;
    }
    click(By.linkText("home"));
  }
}
