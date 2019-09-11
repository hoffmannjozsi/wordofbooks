package com.hoffmannjozsef.feladat;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Main Class
 *
 */
public class App {

    public static void main(String[] args) throws SQLException, IOException {

        MyConfig myconf = new MyConfig("config.ini");

        //Adatbáziskapcsolat felépítése
        //Connect or create MySQL database
        DBConnect conn = new DBConnect(myconf);

        //Adatárroló osztály példányának létrehozása
        DataStoringRepositoryImpl db = new DataStoringRepositoryImpl(myconf, conn);

        //A REST API adatainak adatbázisba írása
        //Synchronize data from a REST API to MySQL database
        JsonToDB jsonToDB = new JsonToDB(db);
        //A három kapcsoló táblát csak új adatbázis készítésekor töltjük fel
        if (myconf.getNewDatabase().equals("1")) {
            jsonToDB.listingStatusToDB();
            jsonToDB.marketplaceToDB();
            jsonToDB.locationToDB();
        }
        jsonToDB.listingToDB();

        //Riportok elkészítése
        //Create Report
        ReportToJson rj = new ReportToJson(myconf, conn);
        System.out.println("The report.json is ready");

        //Riport feltöltése ftp-vel
        //Upload report with ftp
        FTPUpload ftpUp = new FTPUpload(myconf);
        System.out.println("Everything is ready");
        System.out.println("Thank you for the opportunity to introduce yourself");
        System.out.println("Best regards: Hoffmann József");

    }
}
