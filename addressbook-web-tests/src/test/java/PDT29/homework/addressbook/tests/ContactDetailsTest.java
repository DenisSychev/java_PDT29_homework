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

    assertThat(mergeContactInfo(contactInfoFromEditForm), equalTo(mergeDetailsContact(contactInfoFromDetaisForm)));
  }

  private String mergeDetailsContact(ContactData contact) {
    return Arrays.asList(contact.getTextInfo(), contact.getEmailsInfo())
            .stream().map(ContactDetailsTest::cleaned).collect(Collectors.joining("\n"));
  }

  private String mergeContactInfo(ContactData contact) {
    return Arrays.asList(contact.getMiddleName(),
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
            .stream().map(ContactDetailsTest::cleaned).collect(Collectors.joining("\n"));
  }

  public static String cleaned(String info) {
    return info.replaceAll("[()]]", "");
  }

  private String mergeContactInfoOld(ContactData contact) {
    String allContactInfo = contact.getFirstName() + '\n' +
            contact.getMiddleName() + '\n' +
            contact.getLastName() + '\n' +
            contact.getNickName() + '\n' +
            contact.getTitle() + '\n' +
            contact.getCompany() + '\n' +
            contact.getAddress() + '\n' +
            contact.getHomePhone() + '\n' +
            contact.getMobilePhone() + '\n' +
            contact.getWorkPhone() + '\n' +
            contact.getFax() + '\n' +
            contact.getEmail() + '\n' +
            contact.getEmail_2() + '\n' +
            contact.getEmail_3();
    return allContactInfo;

  }

}
