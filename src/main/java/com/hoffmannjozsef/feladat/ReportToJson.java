package com.hoffmannjozsef.feladat;

import java.sql.SQLException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hoffmann József
 */
public class ReportToJson {

    private MyConfig myconf;
    private DBConnect conn;

    private ReportObject reportObject = new ReportObject();

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public ReportToJson(MyConfig myconf, DBConnect conn) throws SQLException, IOException {
        this.myconf = myconf;
        this.conn = conn;

        ReportRepositoryImpl rp = new ReportRepositoryImpl(myconf, conn);
        reportObject.setTotal_listing_count(rp.getTotallistingcount());
        reportObject.setTotal_eBay_listing_count(rp.getTotaleBaylistingcount());
        reportObject.setTotal_eBay_listing_price(rp.getTotaleBaylistingprice());
        reportObject.setAverage_eBay_listing_price(rp.getAverageeBaylistingprice());
        reportObject.setTotal_Amazon_listing_count(rp.getTotalAmazonlistingcount());
        reportObject.setTotal_Amazon_listing_price(rp.getTotalAmazonlistingprice());
        reportObject.setAverage_Amazon_listing_price(rp.getAverageAmazonlistingprice());
        reportObject.setBest_lister_email_address(rp.getBestlisteremailaddress());

        reportObject.setTotal_eBay_listing_count_per_month(rp.getTotaleBaylistingcountpermonth());
        reportObject.setTotal_eBay_listing_price_per_month(rp.getTotaleBaylistingpricepermonth());
        reportObject.setAverage_eBay_listing_price_per_month(rp.getAverageeBaylistingpricepermonth());
        reportObject.setAverage_Amazon_listing_price_per_month(rp.getAverageAmazonlistingpricepermonth());
        reportObject.setTotal_Amazon_listing_count_per_month(rp.getTotalAmazonlistingcountpermonth());
        reportObject.setTotal_Amazon_listing_price_per_month(rp.getTotalAmazonlistingpricepermonth());
        reportObject.setBest_lister_email_address_of_the_month(rp.getBestlisteremailaddressofthemonth());

        try (FileWriter writer = new FileWriter("report.json")) {
            gson.toJson(reportObject, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
