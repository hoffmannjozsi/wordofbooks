package com.hoffmannjozsef.feladat;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class ReportObject {
	private int Total_listing_count;
	private int Total_eBay_listing_count;
	private double Total_eBay_listing_price;
	private double Average_eBay_listing_price;
	private int Total_Amazon_listing_count;
	private double Total_Amazon_listing_price;
	private double Average_Amazon_listing_price;
	private String Best_lister_email_address;

    //Monthly reports:
    
	private Map<String, Integer> Total_eBay_listing_count_per_month = new LinkedHashMap<>();
	private Map<String, Double> Total_eBay_listing_price_per_month  = new LinkedHashMap<>();
	private Map<String, Double> Average_eBay_listing_price_per_month = new LinkedHashMap<>();
	private Map<String, Double> Average_Amazon_listing_price_per_month = new LinkedHashMap<>();
	private Map<String, Integer> Total_Amazon_listing_count_per_month = new LinkedHashMap<>();
	private Map<String, Double> Total_Amazon_listing_price_per_month = new LinkedHashMap<>();
	private Map<String, String> Best_lister_email_address_of_the_month = new LinkedHashMap<>();
	
	public ReportObject() {}
        
        public ReportObject(int Total_listing_count, int Total_eBay_listing_count, double Total_eBay_listing_price, double Average_eBay_listing_price, int Total_Amazon_listing_count, double Total_Amazon_listing_price, double Average_Amazon_listing_price, String Best_lister_email_address) {
        this.Total_listing_count = Total_listing_count;
        this.Total_eBay_listing_count = Total_eBay_listing_count;
        this.Total_eBay_listing_price = Total_eBay_listing_price;
        this.Average_eBay_listing_price = Average_eBay_listing_price;
        this.Total_Amazon_listing_count = Total_Amazon_listing_count;
        this.Total_Amazon_listing_price = Total_Amazon_listing_price;
        this.Average_Amazon_listing_price = Average_Amazon_listing_price;
        this.Best_lister_email_address = Best_lister_email_address;
    }

    public int getTotal_listing_count() {
        return Total_listing_count;
    }

    public void setTotal_listing_count(int Total_listing_count) {
        this.Total_listing_count = Total_listing_count;
    }

    public int getTotal_eBay_listing_count() {
        return Total_eBay_listing_count;
    }

    public void setTotal_eBay_listing_count(int Total_eBay_listing_count) {
        this.Total_eBay_listing_count = Total_eBay_listing_count;
    }

    public double getTotal_eBay_listing_price() {
        return Total_eBay_listing_price;
    }

    public void setTotal_eBay_listing_price(double Total_eBay_listing_price) {
        this.Total_eBay_listing_price = Total_eBay_listing_price;
    }

    public double getAverage_eBay_listing_price() {
        return Average_eBay_listing_price;
    }

    public void setAverage_eBay_listing_price(double Average_eBay_listing_price) {
        this.Average_eBay_listing_price = Average_eBay_listing_price;
    }

    public int getTotal_Amazon_listing_count() {
        return Total_Amazon_listing_count;
    }

    public void setTotal_Amazon_listing_count(int Total_Amazon_listing_count) {
        this.Total_Amazon_listing_count = Total_Amazon_listing_count;
    }

    public double getTotal_Amazon_listing_price() {
        return Total_Amazon_listing_price;
    }

    public void setTotal_Amazon_listing_price(double Total_Amazon_listing_price) {
        this.Total_Amazon_listing_price = Total_Amazon_listing_price;
    }

    public double getAverage_Amazon_listing_price() {
        return Average_Amazon_listing_price;
    }

    public void setAverage_Amazon_listing_price(double Average_Amazon_listing_price) {
        this.Average_Amazon_listing_price = Average_Amazon_listing_price;
    }

    public String getBest_lister_email_address() {
        return Best_lister_email_address;
    }

    public void setBest_lister_email_address(String Best_lister_email_address) {
        this.Best_lister_email_address = Best_lister_email_address;
    }

    public Map<String, Integer> getTotal_eBay_listing_count_per_month() {
        return Total_eBay_listing_count_per_month;
    }

    public void setTotal_eBay_listing_count_per_month(Map<String, Integer> Total_eBay_listing_count_per_month) {
        this.Total_eBay_listing_count_per_month = Total_eBay_listing_count_per_month;
    }

    public Map<String, Double> getTotal_eBay_listing_price_per_month() {
        return Total_eBay_listing_price_per_month;
    }

    public void setTotal_eBay_listing_price_per_month(Map<String, Double> Total_eBay_listing_price_per_month) {
        this.Total_eBay_listing_price_per_month = Total_eBay_listing_price_per_month;
    }

    public Map<String, Double> getAverage_eBay_listing_price_per_month() {
        return Average_eBay_listing_price_per_month;
    }

    public void setAverage_eBay_listing_price_per_month(Map<String, Double> Average_eBay_listing_price_per_month) {
        this.Average_eBay_listing_price_per_month = Average_eBay_listing_price_per_month;
    }

    public Map<String, Double> getAverage_Amazon_listing_price_per_month() {
        return Average_Amazon_listing_price_per_month;
    }

    public void setAverage_Amazon_listing_price_per_month(Map<String, Double> Average_Amazon_listing_price_per_month) {
        this.Average_Amazon_listing_price_per_month = Average_Amazon_listing_price_per_month;
    }

    public Map<String, Integer> getTotal_Amazon_listing_count_per_month() {
        return Total_Amazon_listing_count_per_month;
    }

    public void setTotal_Amazon_listing_count_per_month(Map<String, Integer> Total_Amazon_listing_count_per_month) {
        this.Total_Amazon_listing_count_per_month = Total_Amazon_listing_count_per_month;
    }

    public Map<String, Double> getTotal_Amazon_listing_price_per_month() {
        return Total_Amazon_listing_price_per_month;
    }

    public void setTotal_Amazon_listing_price_per_month(Map<String, Double> Total_Amazon_listing_price_per_month) {
        this.Total_Amazon_listing_price_per_month = Total_Amazon_listing_price_per_month;
    }

    public Map<String, String> getBest_lister_email_address_of_the_month() {
        return Best_lister_email_address_of_the_month;
    }

    public void setBest_lister_email_address_of_the_month(Map<String, String> Best_lister_email_address_of_the_month) {
        this.Best_lister_email_address_of_the_month = Best_lister_email_address_of_the_month;
    }
        
        
}