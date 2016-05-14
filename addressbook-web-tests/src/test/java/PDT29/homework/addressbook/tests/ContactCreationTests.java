package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homepage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstName("Фёдр")
            .withMiddleName("Петрович")
            .withLastName("Иванов")
            .withNickName("И. П. С.")
            .withTitle("М.")
            .withCompany("Компания №1")
            .withAddress("Светлая ул., д. 1, Светлогорск, Центральная область, Страна")
            .withHomePhone("+08 111-22-33")
            .withMobilePhone("+08 111-22-33")
            .withWorkPhone("+09 222-11-33")
            .withFax("+06 555-66-88")
            .withEmail_3("ivanov@noone.sv")
            .withGroup("Group 1");
    app.contact().create(contact, true);
    app.goTo().homepage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}