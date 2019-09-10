package com.hoffmannjozsef.feladat;

import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Hoffmann József
 */
public class DBConnect {

    MyConfig conf;
    private Connection conn = null;

    public DBConnect(MyConfig conf) {
        this.conf = conf;
        //config.ini beállítás. 1=Új adatbázis és táblák létrehozása
        if (conf.getNewDatabase().equals("1")) {
            try {
                this.conn = DriverManager.getConnection("jdbc:mysql://" + conf.getHostname() + ":" + conf.getPort()
                        + "?serverTimezone=" + conf.getServerTimezone() + "&useSSL=" + conf.getUseSSL(), conf.getUsername(), conf.getPassword());
                System.out.println("A híd a MySQL adatbáziskezelővel létrejött (Create)");
                PreparedStatement pst = conn.prepareStatement("CREATE DATABASE  IF NOT EXISTS " + conf.getDbName());
                if (pst.executeUpdate()==1) 
                    System.out.println(conf.getDbName() + " adatbázis létrehozva");
                this.conn.close();
                this.conn = DriverManager.getConnection("jdbc:mysql://" + conf.getHostname() + ":" + conf.getPort() + "/" + conf.getDbName()
                        + "?serverTimezone=" + conf.getServerTimezone() + "&useSSL=" + conf.getUseSSL(), conf.getUsername(), conf.getPassword());

                pst = conn.prepareStatement("DROP TABLE IF EXISTS listingstatus");
                pst.executeUpdate();
                pst = conn.prepareStatement("DROP TABLE IF EXISTS marketplace");
                pst.executeUpdate();
                pst = conn.prepareStatement("DROP TABLE IF EXISTS location");
                pst.executeUpdate();
                pst = conn.prepareStatement("DROP TABLE IF EXISTS listing");
                pst.executeUpdate();
                System.out.println("A listingstatus tábla létrehozva");
                
                pst = conn.prepareStatement("CREATE TABLE listingstatus ("
                        + "  id int(11) NOT NULL,"
                        + "  status_name varchar(45) DEFAULT NULL,"
                        + "  PRIMARY KEY (id),"
                        + "  UNIQUE KEY id_UNIQUE (id))");
                pst.executeUpdate();
                System.out.println("A listingstatus tábla létrehozva");
                
                pst = conn.prepareStatement("CREATE TABLE location ("
                        + "  id varchar(50) NOT NULL,"
                        + "  manager_name varchar(45) DEFAULT NULL,"
                        + "  phone varchar(45) DEFAULT NULL,"
                        + "  address_primary varchar(45) DEFAULT NULL,"
                        + "  address_secondary varchar(45) DEFAULT NULL,"
                        + "  country varchar(45) DEFAULT NULL,"
                        + "  town varchar(45) DEFAULT NULL,"
                        + "  postal_code varchar(45) DEFAULT NULL,"
                        + "  PRIMARY KEY (id),"
                        + "  UNIQUE KEY id_UNIQUE (id))");
                pst.executeUpdate();
                System.out.println("A location tábla létrehozva");

                pst = conn.prepareStatement("CREATE TABLE marketplace ("
                        + "  id int(11) NOT NULL,"
                        + "  marketplace_name varchar(45) DEFAULT NULL,"
                        + "  PRIMARY KEY (id),"
                        + "  UNIQUE KEY id_UNIQUE (id))");
                pst.executeUpdate();
                System.out.println("A marketplace tábla létrehozva");

                pst = conn.prepareStatement("CREATE TABLE listing ("
                        + "  id varchar(60) NOT NULL,"
                        + "  title varchar(100) NOT NULL,"
                        + "  description varchar(500) NOT NULL,"
                        + "  inventory_item_location_id varchar(45) NOT NULL,"
                        + "  listing_price double NOT NULL,"
                        + "  currency varchar(3) NOT NULL,"
                        + "  quantity int(11) NOT NULL,"
                        + "  listing_status int(11) NOT NULL,"
                        + "  marketplace int(11) NOT NULL,"
                        + "  upload_time date DEFAULT NULL,"
                        + "  owner_email_address varchar(100) NOT NULL,"
                        + "  PRIMARY KEY (id),"
                        + "  UNIQUE KEY id_UNIQUE (id),"
                        + "  KEY location_idx (inventory_item_location_id),"
                        + "  KEY listing_status_idx (listing_status),"
                        + "  KEY market_place_idx (marketplace),"
                        + "  CONSTRAINT listing_status FOREIGN KEY (listing_status) REFERENCES listingstatus (id),"
                        + "  CONSTRAINT location FOREIGN KEY (inventory_item_location_id) REFERENCES location (id),"
                        + "  CONSTRAINT market_place FOREIGN KEY (marketplace) REFERENCES marketplace (id))");
                pst.executeUpdate();
                System.out.println("A llisting tábla létrehozva");

            } catch (SQLException ex) {
                System.out.println("Nem sikerült az adatbázis és tábláinak létrehozása." + ex.getMessage());
                exit(0);
            }

        } else { // Csatlakozás létező adatbázishoz, létrehozás nélkül
            try {
                this.conn = DriverManager.getConnection("jdbc:mysql://" + conf.getHostname() + ":" + conf.getPort() + "/" + conf.getDbName()
                        + "?serverTimezone=" + conf.getServerTimezone() + "&useSSL=" + conf.getUseSSL(), conf.getUsername(), conf.getPassword());
                System.out.println("A híd a MySQL adatbáziskezelővel létrejött. (Connect)");

            } catch (SQLException ex) {
                System.out.println("Valami baj van a MySQL Connection (híd) létrehozásakor.");
                exit(0);
            }
        }
    }

    public Connection getConn() {
        return conn;
    }

}
