package PDT29.homework.addressbook.appmanager;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ContactHelper extends BaseHelper {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("//input[@type='submit']"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

    if (creation) {
      new Select(wd.findElement(By.xpath(".//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.xpath(".//select[@name='new_group']")));
    }

  }

  public void gotoEditContactPage() {
    click(By.linkText("add new"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.xpath(".//td[@class='center']//input[@id='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath(".//input[@type='button' and @value='Delete']"));
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath(".//*[@name='entry'][descendant::input[@id='" + id + "']]//a[contains(@href,'edit.php')]")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//input[@value='Update']"));
  }

  public void create(ContactData contact, boolean b) {
    gotoEditContactPage();
    fillContactForm(contact, b);
    submitContactCreation();
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlert();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.xpath(".//*[@id='maintable']//*[@name='entry']")).size();
  }

  public Contacts all() {
    Contacts contacts = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//td[@class='center']//input")).getAttribute("id"));
      String lastName = element.findElement(By.xpath("(.//td[not(child::*)])[1]")).getText();
      String firstName = element.findElement(By.xpath("(.//td[not(child::*)])[2]")).getText();
      String address = element.findElement(By.xpath("(.//td[not(child::*)])[3]")).getText();
      contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName).withAddress(address));
    }
    return contacts;
  }

}
