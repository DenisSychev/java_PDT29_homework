package PDT29.homework.addressbook.appmanager;

import PDT29.homework.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends BaseHelper{

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@type='submit']"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstName());
    type(By.name("middlename"), contactData.getMiddleName());
    type(By.name("lastname"), contactData.getLastName());
    type(By.name("nickname"), contactData.getNickName());
    type(By.name("title"), contactData.getTitle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email2"), contactData.getEmail_2());
    type(By.name("email3"), contactData.getEmail_3());
    }

  public void gotoEditContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContact() {
    if (!wd.findElement(By.name("selected[]")).isSelected()) {
      click(By.name("selected[]"));
    }
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@type='button' and @value='Delete']"));
  }

  public void initContactModification() {
    click(By.xpath("//*[@name=\"entry\"]//a[contains(@href,'edit.php')]"));
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }
}
