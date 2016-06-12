package PDT29.homework.mantis.tests;

import PDT29.homework.mantis.appmanager.ApplicationManager;
import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import javax.xml.rpc.ServiceException;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

public class TestBase {

  protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
    //Заменяет файл конфишурации на сервере (работа описана в FtpHelper)
    app.ftp().upload(new File("src\\test\\resources\\config_inc.php"), "config_inc.php", "config_inc.php.bak");
  }

  private MantisConnectPortType getMantisConnect() throws ServiceException, MalformedURLException {
    return new MantisConnectLocator()
            .getMantisConnectPort(new URL(app.getProperty("web.apiSOAP") + "/mantisconnect.php"));
  }

  public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException {
    MantisConnectPortType mcp = getMantisConnect();
    IssueData issueData = mcp.mc_issue_get(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"), BigInteger.valueOf(issueId));
    if (issueData.getResolution().getName().equals("open") ||
            issueData.getResolution().getName().equals("reopened")) {
      return true;
    } else {
      return false;
    }
  }

  public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws IOException {
    //Восстанавливает резервный (оригинальный) файл конфишурации на сервере (работа описана в FtpHelper)
    app.ftp().restore("config_inc.php", "config_inc.php.bak");
    app.stop();
  }

}