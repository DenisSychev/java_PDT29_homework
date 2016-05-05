package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification(){
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()){
      app.getContactHelper().createContact(new ContactData("Иванов", "Пётр", "Сидорович", "И. П. С.", "М.", "Компания №1",
              "Светлая ул., д. 1, Светлогорск, Центральная область, Страна",
              "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null,
              "ivanov@noone.sv", null, "test2"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectContact();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Name", "N.S.", "Surname", "Nickname", "Mr.", "Company",
            "Black str., Black city, 1. Blackmore",
            "+666991112233", "+666881112233", "+666771112233", "+666771112332",
            "name@domain.bm", "last.name@domain.bm", null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
  }
}
