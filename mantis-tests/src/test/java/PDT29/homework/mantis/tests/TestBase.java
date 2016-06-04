package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    //Заменяет файл конфишурации на сервере (работа описана в FtpHelper)
    app.ftp().upload(new File("src\\test\\resources\\config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    //Восстанавливает резервный (оригинальный) файл конфишурации на сервере (работа описана в FtpHelper)
    app.ftp().restore("config_inc.php", "config_inc.php.bak");
    app.stop();
  }

}