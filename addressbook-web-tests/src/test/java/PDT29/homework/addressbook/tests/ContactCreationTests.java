package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.getContactHelper().gotoEditContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Иванов", "Пётр", "Сидорович", "И. П. С.", "М.", "Компания №1", "Светлая ул., д. 1, Светлогорск, Центральная область, Страна", "+08 111-22-33", "+09 222-11-33", "+06 555-66-88", null, "ivanov@noone.sv", null, "test1"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomepage();
  }

}
