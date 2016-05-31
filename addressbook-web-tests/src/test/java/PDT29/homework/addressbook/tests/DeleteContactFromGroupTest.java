package PDT29.homework.addressbook.tests;

import PDT29.homework.addressbook.model.ContactData;
import PDT29.homework.addressbook.model.Contacts;
import PDT29.homework.addressbook.model.GroupData;
import PDT29.homework.addressbook.model.Groups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroupTest extends TestBase{

  @BeforeMethod
  public void checkPreconditions() {
    if (app.db().groups().size() == 0) {
      Groups before = app.db().groups();
      app.goTo().groupPage();
      GroupData newGroup = new GroupData()
              .withName("test1")
              .withHeader("test_1.2")
              .withFooter("test_1.3");
      app.group().create(newGroup);
      Groups after = app.db().groups();
      assertThat(after, equalTo(before.withAdded(newGroup.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    }

    if (app.db().contacts().size() == 0) {
      Contacts before = app.db().contacts();
      Groups groups = app.db().groups();
      File photo = new File("src/test/resources/foto.jpg");
      app.goTo().homepage();
      ContactData newContact = new ContactData()
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
              .withPhoto(photo)
              .inGroup(groups.iterator().next());
      app.contact().create(newContact);
      Contacts after = app.db().contacts();
      assertThat(after, equalTo(before.withAdded(newContact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }
  }

  @Test
  public void testDeleteContactFromGroup(){
    ContactData contact = app.db().contacts().iterator().next();
    GroupData group = app.db().groups().iterator().next();
    app.goTo().homepage();
    app.group().selectSortAvailableGroup(group);
    app.goTo().homepage();
  }
}
