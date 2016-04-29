package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoEditContactPage();
    app.getContactHelper().fillContactForm(new ContactData("По умолчанию", null, "Пустой", null, null, null, null, null, null, null, null, null, null));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomepage();
  }

}
