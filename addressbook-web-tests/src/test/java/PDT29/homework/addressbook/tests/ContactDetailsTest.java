package PDT29.homework.addressbook.tests;


import PDT29.homework.addressbook.model.ContactData;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTest extends TestBase {

  @Test
  public void testContactDetails() {
    app.goTo().homepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
    ContactData contactInfoFromDetaisForm = app.contact().infoFromDetailsForm(contact);

    assertThat(merge(contactInfoFromDetaisForm), equalTo(mergeDetailsContact(contactInfoFromEditForm)));
  }

  private String merge(ContactData contact){
    return Arrays.asList(contact.getAllInfo()).stream()
            .filter((s) -> !s.equals(""))
            .map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeDetailsContact(ContactData contact) {
    return Arrays.asList(contact.getFirstName(),
            contact.getMiddleName(),
            contact.getLastName(),
            contact.getNickName(),
            contact.getTitle(),
            contact.getCompany(),
            contact.getAddress(),
            contact.getHomePhone(),
            contact.getMobilePhone(),
            contact.getWorkPhone(),
            contact.getFax(),
            contact.getEmail(),
            contact.getEmail_2(),
            contact.getEmail_3())
            .stream().map(ContactPhoneTests::cleaned).collect(Collectors.joining("\n"));
  }
}
