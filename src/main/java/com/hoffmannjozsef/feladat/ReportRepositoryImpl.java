package com.hoffmannjozsef.feladat;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.math3.util.Precision;
import org.json.simple.JSONObject;

/**
 * A riporthoz szükséges adatbázis lekérdezések osztálya
 *
 * @author Hoffmann József
 */
public class ReportRepositoryImpl implements ReportRepositoryInterface {

    MyConfig myconf;
    DBConnect conn;
    private final PreparedStatement getTotallistingcount;
    private final PreparedStatement getTotaleBaylistingcount;
    private final PreparedStatement getTotaleBaylistingprice;
    private final PreparedStatement getAverageeBaylistingprice;
    private final PreparedStatement getTotalAmazonlistingcount;
    private final PreparedStatement getTotalAmazonlistingprice;
    private final PreparedStatement getAverageAmazonlistingprice;
    private final PreparedStatement getBestlisteremailaddress;
    private final PreparedStatement getTotaleBaylistingcountpermonth;
    private final PreparedStatement getTotaleBaylistingpricepermonth;
    private final PreparedStatement getAverageeBaylistingpricepermonth;
    private final PreparedStatement getAverageAmazonlistingpricepermonth;
    private final PreparedStatement getTotalAmazonlistingcountpermonth;
    private final PreparedStatement getTotalAmazonlistingpricepermonth;
    private final PreparedStatement createAllListeremailaddressofthemonth;
    private final PreparedStatement getBestlisteremailaddressofthemonth;

    
    
    public ReportRepositoryImpl(MyConfig myconf, DBConnect conn) throws SQLException {
        this.myconf = myconf;
        this.conn = conn;

        this.getTotallistingcount = conn.getConn().prepareStatement("SELECT count(*) as tcount FROM listing");
        
        this.getTotaleBaylistingcount = conn.getConn().prepareStatement("SELECT count(*) as countebay FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"EBAY\"");
        
        this.getTotaleBaylistingprice = conn.getConn().prepareStatement("SELECT sum(listing_price) as sumebay FROM listing LEFT JOIN marketplace "
                + "ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"EBAY\"");
        
        this.getAverageeBaylistingprice = conn.getConn().prepareStatement("SELECT sum(listing_price)/count(*) avgebay "
                + "FROM listing LEFT JOIN marketplace ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"EBAY\"");
        
        this.getTotalAmazonlistingcount = conn.getConn().prepareStatement("SELECT count(*) as amazoncount "
                + "FROM listing LEFT JOIN marketplace ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"AMAZON\"");
       
        this.getTotalAmazonlistingprice = conn.getConn().prepareStatement("SELECT sum(listing_price) as sumamazon "
                + "FROM listing LEFT JOIN marketplace ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"AMAZON\"");
        
        this.getAverageAmazonlistingprice = conn.getConn().prepareStatement("SELECT sum(listing_price)/count(*) avgamazon "
                + "FROM listing LEFT JOIN marketplace ON listing.marketplace = marketplace.id WHERE marketplace.marketplace_name=\"AMAZON\"");
        
        this.getBestlisteremailaddress = conn.getConn().prepareStatement("SELECT owner_email_address FROM listing GROUP BY owner_email_address ORDER BY count(*) desc limit 1");
        
    //Monthly reports
        this.getTotaleBaylistingcountpermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) AS ListingYear, MONTH(upload_time) AS ListingMonth, "
                + "COUNT(*) AS result  "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"EBAY\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth ORDER BY ListingYear");

        this.getTotaleBaylistingpricepermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) as ListingYear, "
                + "MONTH(upload_time) as ListingMonth, SUM(listing_price) as result "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"EBAY\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth ORDER BY  ListingYear");

        this.getAverageeBaylistingpricepermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) as ListingYear, MONTH(upload_time) as ListingMonth, "
                + "SUM(listing_price)/COUNT(*) as result "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"EBAY\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth "
                + "ORDER BY ListingYear");

        this.getAverageAmazonlistingpricepermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) as ListingYear, "
                + "MONTH(upload_time) as ListingMonth, SUM(listing_price)/COUNT(*) as result "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"AMAZON\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth "
                + "ORDER BY ListingYear");

        this.getTotalAmazonlistingpricepermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) as ListingYear, "
                + "MONTH(upload_time) as ListingMonth, SUM(listing_price) as result "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"AMAZON\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth ORDER BY  ListingYear");

        this.getTotalAmazonlistingcountpermonth = conn.getConn().prepareStatement("SELECT YEAR(upload_time) AS ListingYear, MONTH(upload_time) AS ListingMonth, "
                + "COUNT(*) AS result  "
                + "FROM listing "
                + "LEFT JOIN marketplace ON listing.marketplace = marketplace.id "
                + "WHERE marketplace.marketplace_name=\"AMAZON\" AND upload_time > \'2000-01-01\' "
                + "GROUP BY ListingYear, ListingMonth ORDER BY ListingYear");

        this.createAllListeremailaddressofthemonth = conn.getConn().prepareStatement("CREATE TABLE bestemailspermonth AS " 
                + "(SELECT YEAR(upload_time) AS ListingYear, MONTH(upload_time) AS ListingMonth, owner_email_address, COUNT(1) AS emails_count " 
                + "FROM listing " 
                + "GROUP BY ListingYear, ListingMonth, owner_email_address " 
                + "ORDER BY 1,2,4 desc)");
        
         this.getBestlisteremailaddressofthemonth = conn.getConn().prepareStatement("SELECT ListingYear, ListingMonth, owner_email_address, "
                + "MAX(emails_count) as result "
                + "FROM bestemailspermonth GROUP BY ListingYear, ListingMonth");

    }

    @Override
    public int getTotallistingcount() {
        ResultSet all;
        int res = -1;
        try {
            all = this.getTotallistingcount.executeQuery();
            all.next();
            res = all.getInt("tcount");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 1: " + ex.getMessage());
        }
        return res;
    }

    @Override
    public int getTotaleBaylistingcount() {
        ResultSet all;
        int res = -1;
        try {
            all = this.getTotaleBaylistingcount.executeQuery();
            all.next();
            res = all.getInt("countebay");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 2: " + ex.getMessage());
        }
        return res;
    }

    @Override
    public double getTotaleBaylistingprice() {
        ResultSet all;
        double res = -1;
        try {
            all = this.getTotaleBaylistingprice.executeQuery();
            all.next();
            res = all.getDouble("sumebay");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 3: " + ex.getMessage());
        }
        return Precision.round(res,2);
    }

    @Override
    public double getAverageeBaylistingprice() {
        ResultSet all;
        double res = -1;
        try {
            all = this.getAverageeBaylistingprice.executeQuery();
            all.next();
            res = all.getDouble("avgebay");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 4: " + ex.getMessage());
        }
        return Precision.round(res,2);
    }

    @Override
    public int getTotalAmazonlistingcount() {
        ResultSet all;
        int res = -1;
        try {
            all = this.getTotalAmazonlistingcount.executeQuery();
            all.next();
            res = all.getInt("amazoncount");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 5: " + ex.getMessage());
        }
        return res;
    }

    @Override
    public double getTotalAmazonlistingprice() {
        ResultSet all;
        double res = -1;
        try {
            all = this.getTotalAmazonlistingprice.executeQuery();
            all.next();
            res = all.getDouble("sumamazon");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 6: " + ex.getMessage());
        }
        return Precision.round(res,2);
    }

    @Override
    public double getAverageAmazonlistingprice() {
        ResultSet all;
        double res = -1;
        try {
            all = this.getAverageAmazonlistingprice.executeQuery();
            all.next();
            res = all.getDouble("avgamazon");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 7: " + ex.getMessage());
        }
        return Precision.round(res,2);
    }

    @Override
    public String getBestlisteremailaddress() {
        ResultSet all;
        String res = "";
        try {
            all = this.getBestlisteremailaddress.executeQuery();
            all.next();
            res = all.getString("owner_email_address");
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 8: " + ex.getMessage());
        }
        return res;
    }

    @Override
    public Map<String, Integer> getTotaleBaylistingcountpermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Integer> resMap = new LinkedHashMap<>();
        int res = -1;
        try {
            all = this.getTotaleBaylistingcountpermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = all.getInt("result");
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 9: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, Double> getTotaleBaylistingpricepermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Double> resMap = new LinkedHashMap<>();
        double res = -1;
        try {
            all = this.getTotaleBaylistingpricepermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = Precision.round(all.getDouble("result"), 2);
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 10: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, Double> getAverageeBaylistingpricepermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Double> resMap = new LinkedHashMap<>();
        double res = -1;
        try {
            all = this.getAverageeBaylistingpricepermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = Precision.round(all.getDouble("result"), 2);
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 11: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, Double> getAverageAmazonlistingpricepermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Double> resMap = new LinkedHashMap<>();
        double res = -1;
        try {
            all = this.getAverageAmazonlistingpricepermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = Precision.round(all.getDouble("result"), 2);
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 12: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, Double> getTotalAmazonlistingpricepermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Double> resMap = new LinkedHashMap<>();
        double res = -1;
        try {
            all = this.getTotalAmazonlistingpricepermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = Precision.round(all.getDouble("result"), 2);
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 13: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, Integer> getTotalAmazonlistingcountpermonth() {
        ResultSet all;
        String yearMonth = "";
        Map<String, Integer> resMap = new LinkedHashMap<>();
        int res = -1;
        try {
            all = this.getTotalAmazonlistingcountpermonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = all.getInt("result");
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 14: " + ex.getMessage());
        }
        return resMap;
    }

    @Override
    public Map<String, String> getBestlisteremailaddressofthemonth() {
        
        ResultSet all;
        String yearMonth = "";
        Map<String, String> resMap = new LinkedHashMap<>();
        String res ="";
        try {
            PreparedStatement pst = conn.getConn().prepareStatement("DROP TABLE IF EXISTS bestemailspermonth");
            pst.executeUpdate();
            createAllListeremailaddressofthemonth.executeUpdate();
            all = this.getBestlisteremailaddressofthemonth.executeQuery();
            while (all.next()) {
                yearMonth = "" + all.getInt("ListingYear") + all.getInt("ListingMonth");
                res = all.getString("owner_email_address");
                resMap.put(yearMonth, res);
            }
        } catch (SQLException ex) {
            System.out.println("Lekérdezés hiba 15: " + ex.getMessage());
        }

        return resMap;
    }
}
