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
    attach(By.name("photo"), contactData.getPhoto());

    if (creation) {
      /*new Select(wd.findElement(By.xpath(".//select[@name='new_group']"))).selectByVisibleText(contactData.getGroup());*/
    } else {
      Assert.assertFalse(isElementPresent(By.xpath(".//select[@name='new_group']")));
    }
  }

  public void gotoEditContactPage() {
    click(By.linkText("add new"));
  }

  public void gotoContactDetails(int id){
    wd.findElement(By.xpath(".//*[@name='entry'][descendant::input[@id='" + id + "']]//a[contains(@href,'view.php')]")).click();
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

  public void create(ContactData contact) {
    gotoEditContactPage();
    fillContactForm(contact, true);
    submitContactCreation();
    contactCache = null;
  }

  public void modify(ContactData contact) {
    selectContactById(contact.getId());
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache = null;
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlert();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.xpath(".//*[@id='maintable']//*[@name='entry']")).size();
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      int id = Integer.parseInt(element.findElement(By.xpath(".//td[@class='center']//input")).getAttribute("id"));
      String lastName = element.findElement(By.xpath("(.//td[not(child::*)])[1]")).getText();
      String firstName = element.findElement(By.xpath("(.//td[not(child::*)])[2]")).getText();
      String address = element.findElement(By.xpath("(.//td[not(child::*)])[3]")).getText();
      String allPhones = element.findElement(By.xpath(".//td[6]")).getText();
      String allEmail = element.findElement(By.xpath(".//td[5]")).getText();
      contactCache.add(new ContactData()
              .withId(id)
              .withFirstName(firstName)
              .withLastName(lastName)
              .withAddress(address)
              .withAllPhones(allPhones)
              .withAllEmail(allEmail));
    }
    return new Contacts(contactCache);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
    String middleName = wd.findElement(By.name("middlename")).getAttribute("value");
    String nickName = wd.findElement(By.name("nickname")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String homePhone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workPhone = wd.findElement(By.name("work")).getAttribute("value");
    String fax = wd.findElement(By.name("fax")).getAttribute("value");
    String address = wd.findElement(By.xpath("//textarea[@name='address']")).getText();
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email_2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email_3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
            .withId(contact.getId())
            .withFirstName(firstname)
            .withLastName(lastName)
            .withMiddleName(middleName)
            .withNickName(nickName)
            .withTitle(title)
            .withCompany(company)
            .withHomePhone(homePhone)
            .withMobilePhone(mobilePhone)
            .withWorkPhone(workPhone)
            .withFax(fax)
            .withAddress(address)
            .withEmail(email)
            .withEmail_2(email_2)
            .withEmail_3(email_3);
  }

  public ContactData infoFromDetailsForm(ContactData contact) {
    gotoContactDetails(contact.getId());
    String textInfo = wd.findElement(By.xpath(".//*[@id='content']")).getText();
    return new ContactData().withAllInfo(textInfo);
  }
}
