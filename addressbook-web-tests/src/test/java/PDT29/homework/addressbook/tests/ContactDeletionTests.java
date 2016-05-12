package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void checkPreconditions() {
    app.goTo().homepage();
    if (app.contact().list().size() == 0) {
      app.contact().create(new ContactData()
              .thisFirstName("Пётр").thisMiddleName("Сидорович").thisLastName("Базанов").thisNickName("П. С. Б.")
              .thisTitle("П.").thisCompany("Компания №2").thisAddress("Светлая ул., д. 1, Светлогорск, Центральная область, Страна")
              .thisHomePhone("+08 111-22-33").thisMobilePhone("+08 111-22-33").thisWorkPhone("+09 222-11-33").thisFax("+06 555-66-88")
              .thisEmail_3("ivanov@noone.sv").thisGroup("Group 1"), true);

    }
  }

  @Test
  public void testContactDeletion() {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().delete(index);
    app.goTo().homepage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() - 1);

    before.remove(index);
    Assert.assertEquals(before, after);
  }

}
