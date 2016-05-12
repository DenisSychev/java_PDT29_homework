package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions() {
    app.goTo().homepage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .thisFirstName("Пётр")
              .thisMiddleName("Сидорович")
              .thisLastName("Базанов")
              .thisNickName("П. С. Б.")
              .thisTitle("П.")
              .thisCompany("Компания №2")
              .thisAddress("Светлая ул., д. 1, Светлогорск, Центральная область, Страна")
              .thisHomePhone("+08 111-22-33")
              .thisMobilePhone("+08 111-22-33")
              .thisWorkPhone("+09 222-11-33")
              .thisFax("+06 555-66-88")
              .thisEmail_3("ivanov@noone.sv")
              .thisGroup("Group 1"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData()
            .thisId(before.get(index).getId())
            .thisFirstName("Name")
            .thisMiddleName("N.S.")
            .thisLastName("Surname")
            .thisNickName("Who")
            .thisTitle("Mr.")
            .thisCompany("This Company")
            .thisAddress("Black str., Black city, 1. Blackmore")
            .thisHomePhone("+666 99 111 22 33")
            .thisMobilePhone("+6 66 88 111 22 33")
            .thisWorkPhone("+66 677 111 2 33 2")
            .thisFax("+666771112233")
            .thisEmail_2("name@domain.bm")
            .thisEmail_3("last.name@domain.bm");
    app.contact().modify(index, contact);
    app.goTo().homepage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
