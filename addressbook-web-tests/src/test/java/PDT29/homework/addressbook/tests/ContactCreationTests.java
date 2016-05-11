package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("Фёдр", "Петрович", "Иванов", "И. П. С.", "М.", "Компания №1",
            "Светлая ул., д. 1, Светлогорск, Центральная область, Страна",
            "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null,
            "ivanov@noone.sv", null, "test2");
    app.contact().create(contact, true);
    app.goTo().homepage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}