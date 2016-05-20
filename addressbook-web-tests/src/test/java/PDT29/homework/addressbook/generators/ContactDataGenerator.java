package PDT29.homework.addressbook.generators;


import PDT29.homework.addressbook.model.ContactData;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
  }

  private void save(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
              contact.getFirstName(),
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
              contact.getEmail_2(),
              contact.getEmail_3(),
              contact.getGroup()));
    }
    writer.close();
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData()
              .withFirstName(String.format("Фёдр %s", i))
              .withMiddleName(String.format("Петрович %s", i))
              .withLastName(String.format("Иванов %s", i))
              .withNickName(String.format("\"И. П. С. %s", i))
              .withTitle(String.format("гражданин %s", i))
              .withCompany(String.format("Компания №1"))
              .withAddress(String.format("Светлая ул., д. 1, Светлогорск, Центральная область, Страна"))
              .withHomePhone(String.format("+08 111-22-3%s", i))
              .withMobilePhone(String.format("+08 111-22-%s3", i))
              .withWorkPhone(String.format("+09 222-1%s-33", i))
              .withFax(String.format("+06 555-%s6-88", i))
              .withEmail_2(String.format("fedor.%s@noone.sv", i))
              .withEmail_3(String.format("fedor.%s.ivanov@noone.sv", i))
              .withGroup(String.format("Group 1")));
    }
    return contacts;
  }
}
