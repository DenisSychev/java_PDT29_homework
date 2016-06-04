package PDT29.homework.mantis.appmanager;

import PDT29.homework.mantis.model.DBHelper;
import PDT29.homework.mantis.model.UserData;
import PDT29.homework.mantis.model.Users;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  private WebDriver wd; //Браузер можно запустить только через getDriver()
  private String browser;
  private RegistrationHelper registrationHelper;
  private FtpHelper ftp;
  private MailHelper mailHelper;
  private LoginHelper loginHelper;
  private NavigationHelper navigationHelper;
  private DBHelper dBHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src\\test\\resources\\%s.properties", target))));
  }

  //Запуск браузера
  public WebDriver getDriver() {
    if (wd == null) { //если браузер уже не запущен, то запускаем
      if (Objects.equals(browser, BrowserType.FIREFOX)) {
        wd = new FirefoxDriver();
      } else if (Objects.equals(browser, BrowserType.CHROME)) {
        wd = new ChromeDriver();
      } else if (Objects.equals(browser, BrowserType.IE)) {
        wd = new InternetExplorerDriver();
      }
      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));
    }
    return wd;
  }

  //Остановка браузера
  public void stop() {
    if (wd != null) { //Если он запущен,
      wd.quit(); //то его надо остановить
    } //иначе ничего не надо делать
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }

  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  //Инициализация RegistrationHelper только в случае обращения
  public RegistrationHelper registration() { //в тестах вызывается app.registration()
    if (registrationHelper == null) { //если уже был инициализирован RegistrationHelper (запущен браузер)
      registrationHelper = new RegistrationHelper(this); //то запускаем его (this - это ссылка на ApplicationManager)
    }
    return registrationHelper;
  }

  //Инициализация LoginHelper только в случае обращения
  public LoginHelper login() { //в тестах вызывается app.login()
    if (loginHelper == null) { //если уже был инициализирован RegistrationHelper (запущен браузер)
      loginHelper = new LoginHelper(this); //то запускаем его (this - это ссылка на ApplicationManager)
    }
    return loginHelper;
  }

  public NavigationHelper goTo() { //в тестах вызывается app.goTo()
    if (navigationHelper == null) {
      navigationHelper = new NavigationHelper(this);
    }
    return navigationHelper;
  }

  //Инициализация FtpHelper только в случае обращения
  public FtpHelper ftp() {
    if (ftp == null) { //если уже был инициализирован FtpHelper (запущен браузер)
      ftp = new FtpHelper(this); //то запускаем его (this - это ссылка на ApplicationManager)
    }
    return ftp;
  }

  public MailHelper mail() {
    if (mailHelper == null) {
      mailHelper = new MailHelper(this);
    }
    return mailHelper;
  }

  public UserHelper user() {
    return new UserHelper(this);
  }

  public DBHelper db() {
    if (dBHelper == null) {
      dBHelper = new DBHelper();
    }
    return dBHelper;
  }
}
