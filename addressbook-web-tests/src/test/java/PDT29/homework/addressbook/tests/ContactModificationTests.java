package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Name", "N.S.", "Surname", "Nickname", "Mr.", "Company",
            "Black str., Black city, 1. Blackmore",
            "+666991112233", "+666881112233", "+666771112233", "+666771112332",
            "name@domain.bm", "last.name@domain.bm", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
  }
}
