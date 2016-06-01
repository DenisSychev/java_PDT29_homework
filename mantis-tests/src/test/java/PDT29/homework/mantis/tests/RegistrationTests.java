package PDT29.homework.mantis.tests;

import org.testng.annotations.Test;

public class RegistrationTests extends TestBase {

  @Test
  public void testRegistration(){
    app.registration().start("user_1", "user_1@localhost.localdomain");
  }
}
