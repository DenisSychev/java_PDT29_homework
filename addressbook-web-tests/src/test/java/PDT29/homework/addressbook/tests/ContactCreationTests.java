package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/foto.jpg");
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
            .withGroup("Group 1")
            .withPhoto(photo);
    app.contact().create(contact, true);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}