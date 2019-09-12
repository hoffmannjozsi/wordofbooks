package com.hoffmannjozsef.feladat;

import java.util.Map;

/**
 *
 * @author Hoffmann József
 */
public interface ReportRepositoryInterface {
    
    public int getTotallistingcount();
    public int getTotaleBaylistingcount();
    public double getTotaleBaylistingprice();
    public double getAverageeBaylistingprice();
    public int getTotalAmazonlistingcount();
    public double getTotalAmazonlistingprice();
    public double getAverageAmazonlistingprice();
    public String getBestlisteremailaddress();
    
    public Map<String, Integer> getTotaleBaylistingcountpermonth();
    public Map<String, Double> getTotaleBaylistingpricepermonth();
    public Map<String, Double> getAverageeBaylistingpricepermonth();
    public Map<String, Double> getAverageAmazonlistingpricepermonth();
    public Map<String, Integer> getTotalAmazonlistingcountpermonth();
    public Map<String, Double> getTotalAmazonlistingpricepermonth();
    public Map<String, String> getBestlisteremailaddressofthemonth();
}
