package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Иванов", "Пётр", "Сидорович", "И. П. С.", "М.", "Компания №1",
              "Светлая ул., д. 1, Светлогорск, Центральная область, Страна",
              "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null,
              "ivanov@noone.sv", null, "test2"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectContact();
    app.getContactHelper().deleteSelectedContact();
    app.getContactHelper().closeAlert();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }
}
