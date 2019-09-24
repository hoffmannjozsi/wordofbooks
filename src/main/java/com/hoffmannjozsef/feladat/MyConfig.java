package com.hoffmannjozsef.feladat;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Hoffmann József
 */

/*MyConfig is a Singleton!*/
public class MyConfig {

    Properties prop;
    //Default settings
    private String hostname = "localhost";
    private String port = "3306";
    private String dbName = "wobDBdef";
    private String serverTimezone = "UTC";
    private String useSSL = "false";
    private String username = "root";
    private String password = "1234";

    private String uploadTimeValidation = "0";
    private String importLogEx = "0";

    private String newDatabase = "1";

    private int ftpport = 21;
    private String ftpserver = "webseeder.hu";
    private String ftpuser = "jozsefhoffmann71@gmail.com";
    private String ftppass = "srpii";

    private static MyConfig instance = null;

    private MyConfig() {
    }

    static public MyConfig instance() {
        if (instance == null) {
            instance = new MyConfig();
        }
        return instance;
    }

    @Override
    public void finalize() {
        instance = null;
    }

    //public MyConfig() {
    //}
    static public MyConfig instance(String configFile) {
        if (instance == null) {
            instance = new MyConfig(configFile);
        } else {
            System.out.println("MyConfig egy Singleton, és ezekkel a paraméterekkel már létre lett hozva");
        }
        return instance;
    }

    private MyConfig(String configFile) {

        try {
            prop = new Properties();
            prop.load(new FileReader(configFile));
            this.hostname = prop.getProperty("hostname");
            this.port = prop.getProperty("port");
            this.dbName = prop.getProperty("dbName");
            this.serverTimezone = prop.getProperty("serverTimezone");
            this.useSSL = prop.getProperty("useSSL");
            this.uploadTimeValidation = prop.getProperty("uploadTimeValidation");
            this.importLogEx = prop.getProperty("importLogEx");
            this.newDatabase = prop.getProperty("newDatabase");
            this.ftpport = Integer.parseInt(prop.getProperty("ftpport"));
            this.ftpserver = prop.getProperty("ftpserver");
            this.ftpuser = prop.getProperty("ftpuser");
            this.ftppass = prop.getProperty("ftppass");

        } catch (IOException ex) {
            System.out.println("Hiba a config.ini fájl beolvasásakor. Az adatbázis kapcsolati adatok alapértelmezettre állítva");
            System.out.println("Hibaüzenet : " + ex.getMessage());
        }
    }

    public void setProp(String configFile) {
        try {
            this.prop = new Properties();
            prop.load(new FileReader(configFile));
        } catch (IOException ex) {
            System.out.println("Hiba a config.ini fájl beolvasásakor. Az adatbázis kapcsolati adatok alapértelmezettre állítva");
            System.out.println("Hibaüzenet : " + ex.getMessage());
        }
    }

    public Properties getProp() {
        return prop;
    }

    public String getHostname() {
        return hostname;
    }

    public String getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

    public String getServerTimezone() {
        return serverTimezone;
    }

    public String getUseSSL() {
        return useSSL;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUploadTimeValidation() {
        return uploadTimeValidation;
    }

    public String getImportLogEx() {
        return importLogEx;
    }

    public String getNewDatabase() {
        return newDatabase;
    }

    public int getFtpport() {
        return ftpport;
    }

    public String getFtpserver() {
        return ftpserver;
    }

    public String getFtpuser() {
        return ftpuser;
    }

    public String getFtppass() {
        return ftppass;
    }

}
