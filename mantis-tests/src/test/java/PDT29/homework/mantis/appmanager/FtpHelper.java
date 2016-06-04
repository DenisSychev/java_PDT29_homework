package PDT29.homework.mantis.appmanager;

import org.apache.commons.net.ftp.FTPClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FtpHelper {

  private final ApplicationManager app;
  private final FTPClient ftp;

  public FtpHelper(ApplicationManager app){
    this.app = app;
    ftp = new FTPClient(); //при вызове FtpHelper создаётся FTPClient()
  }

  /*Загрузка файла. Где,
  file - локальный файл в resources
  target - имя файла на сервере, которые необходимо заменить локальным
  backup - имя резервного (оригинального) файла, который заменили локальным
  */
  public void upload(File file, String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host")); //установка соединения с сервером
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password")); //авторизация
    ftp.deleteFile(backup); //удаляется backup
    ftp.rename(target, backup); //переименовывается файл на сервере (создаётся резервный файл)
    ftp.enterLocalPassiveMode();
    ftp.storeFile(target, new FileInputStream(file)); //локальный файл загружается на сервер
    ftp.disconnect();
  }

  //Восстановление сохранённого файла
  public void restore(String target, String backup) throws IOException {
    ftp.connect(app.getProperty("ftp.host"));
    ftp.login(app.getProperty("ftp.login"), app.getProperty("ftp.password"));
    ftp.deleteFile(target);
    ftp.rename(backup, target);
    ftp.disconnect();
  }
}
