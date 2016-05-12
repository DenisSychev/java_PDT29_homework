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
    ContactData contact = new ContactData()
            .thisFirstName("Фёдр").thisMiddleName("Петрович").thisLastName("Иванов").thisNickName("И. П. С.")
            .thisTitle("М.").thisCompany("Компания №1").thisAddress("Светлая ул., д. 1, Светлогорск, Центральная область, Страна")
            .thisHomePhone("+08 111-22-33").thisMobilePhone("+08 111-22-33").thisWorkPhone("+09 222-11-33").thisFax("+06 555-66-88")
            .thisEmail_3("ivanov@noone.sv").thisGroup("Group 1");
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