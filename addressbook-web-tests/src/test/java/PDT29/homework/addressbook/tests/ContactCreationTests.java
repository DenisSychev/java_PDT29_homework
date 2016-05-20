package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader("src\\test\\resources\\contacts.csv"));
    String line = reader.readLine();

    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData()
              .withFirstName(split[0])
              .withMiddleName(split[1])
              .withLastName(split[2])
              .withNickName(split[3])
              .withTitle(split[4])
              .withCompany(split[5])
              .withAddress(split[6])
              .withHomePhone(split[7])
              .withMobilePhone(split[8])
              .withWorkPhone(split[9])
              .withFax(split[10])
              .withEmail_2(split[11])
              .withEmail_3(split[12])
              .withGroup(split[13])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) {
    app.goTo().homepage();
    Contacts before = app.contact().all();
   /* File photo = new File("src/test/resources/foto.jpg");
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
            .withPhoto(photo);*/
    app.contact().create(contact, true);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
  }
}