package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions() {
    app.goTo().homepage();
    if (app.contact().all().size() == 0) {
      app.contact().create(new ContactData()
              .withFirstName("Пётр")
              .withMiddleName("Сидорович")
              .withLastName("Базанов")
              .withNickName("П. С. Б.")
              .withTitle("П.")
              .withCompany("Компания №2")
              .withAddress("Светлая ул., д. 1, Светлогорск, Центральная область, Страна")
              .withHomePhone("+08 111-22-33")
              .withMobilePhone("+08 111-22-33")
              .withWorkPhone("+09 222-11-33")
              .withFax("+06 555-66-88")
              .withEmail_3("ivanov@noone.sv")
              .withGroup("Group 1"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData()
            .withId(modifiedContact.getId())
            .withFirstName("Name")
            .withMiddleName("N.S.")
            .withLastName("Surname")
            .withNickName("Who")
            .withTitle("Mr.")
            .withCompany("This Company")
            .withAddress("Black str., Black city, 1. Blackmore")
            .withHomePhone("+666 99 111 22 33")
            .withMobilePhone("+6 66 88 111 22 33")
            .withWorkPhone("+66 677 111 2 33 2")
            .withFax("+666771112233")
            .withEmail_2("name@domain.bm")
            .withEmail_3("last.name@domain.bm");
    app.contact().modify(contact);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

}
