package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions(){
    app.getNavigationHelper().gotoHomepage();
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Иванов", "Пётр", "Сидорович", "И. П. С.", "М.", "Компания №1",
              "Светлая ул., д. 1, Светлогорск, Центральная область, Страна",
              "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null,
              "ivanov@noone.sv", null, "test2"), true);
    }
  }

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(index).getId(), "Name", "N.S.", "Surname", "Nickname", "Mr.", "Company",
            "Black str., Black city, 1. Blackmore",
            "+666991112233", "+666881112233", "+666771112233", "+666771112332",
            "name@domain.bm", "last.name@domain.bm", null);

    app.getContactHelper().modifyContact(index, contact);
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
