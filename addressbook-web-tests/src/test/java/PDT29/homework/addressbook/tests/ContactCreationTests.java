package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.gotoEditContactPage();
    app.fillContactForm(new ContactData("Имя", "И. Ф.", "Фамилия", "ФИО", "Мистер", "Компания", "Чёрная ул., Чёрный город, 1. Чёрная страна", "+559991112233", "+558881112233", "+337771112233", "+337771112332", "name@domain.zw", "last.name@domain.zw"));
    app.submitContactCreation();
    app.gotoHomepage();
  }

}
