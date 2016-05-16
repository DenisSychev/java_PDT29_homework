package PDT29.homework.addressbook.tests;


import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {

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
              .withEmail_2("bazanov@noone.sv")
              .withEmail_3("ivanov@noone.sv")
              .withGroup("Group 1"), true);
    }
  }

  @Test
  public void testContactEmail() {
    app.goTo().homepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmail(), equalTo(mergeEmail(contactInfoFromEditForm)));
  }

  private String mergeEmail(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail_2(), contact.getEmail_3())
            .stream().filter((s -> s.equals("")))
            .collect(Collectors.joining("\n"));
  }
}