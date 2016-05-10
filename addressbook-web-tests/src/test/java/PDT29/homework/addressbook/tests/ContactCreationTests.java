package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().createContact(new ContactData("Пётр", "Сидорович", "Иванов", "И. П. С.", "М.", "Компания №1",
            "Светлая ул., д. 1, Светлогорск, Центральная область, Страна",
            "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null,
            "ivanov@noone.sv", null, "test2"), true);
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
  }

}