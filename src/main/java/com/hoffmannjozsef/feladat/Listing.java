package com.hoffmannjozsef.feladat;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hoffmann József
 */
public class Listing {

    private MyConfig conf;

    private String id = "";
    private String title = "";
    private String description = "";
    private String location_id = "";
    private double listing_price = 0;
    private String currency = "";
    private int quantity = 0;
    private int listing_status = 0;
    private int marketplace = 0; //Marketplace id  
    private LocalDate upload_time = LocalDate.of(1000,1,2);
    private String owner_email_address = "";

    private boolean validUploadTime = true;
    private HashSet<String> locids = new HashSet<>(); //location id-k halmaza
    private HashSet<Integer> lstids = new HashSet<>(); //listing_status id-k halmaza
    private HashSet<Integer> mplids = new HashSet<>(); //marketplace id-k halmaza
    private static final Pattern VALID_EMAIL_REGEX
            = Pattern.compile("^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$",
                    Pattern.CASE_INSENSITIVE);

    private String invalidFiled = "";
    private boolean logEx = true; //importLog.csv tartalmazza-e a hibás mező értékét is?

    public Listing() {
    }

    public Listing(MyConfig conf, HashSet<String> locids, HashSet<Integer> lstids, HashSet<Integer> mplids) {
        this.conf = conf;
		if (conf.getImportLogEx().equals("0")) {
            this.logEx = false; 
        }
        this.locids = locids;
        this.lstids = lstids;
        this.mplids = mplids;

    }

    public void setMyConfig(MyConfig conf) {
        this.conf = conf;
        if (conf.getImportLogEx().equals("0")) {
            this.logEx = false; 
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public double getListing_price() {
        return listing_price;
    }

    public void setListing_price(double listing_price) {
        this.listing_price = listing_price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getListing_status() {
        return listing_status;
    }

    public void setListing_status(int listing_status) {
        this.listing_status = listing_status;
    }

    public int getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(int marketplace) {
        this.marketplace = marketplace;
    }

    public LocalDate getUpload_time() {
        return upload_time;
    }

    public void setUpload_time(String upload_time_Str) {
        
        if (upload_time_Str != null && !upload_time_Str.isEmpty()) {
            try {
                
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
                this.upload_time = LocalDate.parse(upload_time_Str, formatter);
               
            } catch (DateTimeParseException ex) {
               
                //A config fájl beállítása szerint validáljuk vagy sem az upload_time -ot
                if (conf.getUploadTimeValidation().equals("1")) {
                    validUploadTime = false;
                }
                this.invalidFiled = "upload_time";
                if (logEx) {
                    this.invalidFiled += ": " + upload_time_Str;
                }
            }
        } else {
            if (conf.getUploadTimeValidation().equals("1")) {
                validUploadTime = false;
            }
            this.invalidFiled = "upload_time";
            if (logEx) {
                this.invalidFiled += ": " + upload_time_Str;
            }

        }
    }

    public String getOwner_email_address() {
        return owner_email_address;
    }

    public void setOwner_email_address(String owner_email_address) {
        this.owner_email_address = owner_email_address;
    }

    public String getInvalidFiled() {
        return invalidFiled;
    }

    public void setLogEx(boolean logEx) {
        this.logEx = logEx;
    }

    @Override
    public String toString() {
        return "Listing{" + "id=" + id + ", title=" + title + ", description="
                + description + ", location_id=" + location_id + ", listing_price="
                + listing_price + ", currency=" + currency + ", quantity="
                + quantity + ", listing_status=" + listing_status + ", marketplace="
                + marketplace + ", upload_time=" + upload_time + ", owner_email_address="
                + owner_email_address + '}';
    }

    public boolean isValidId() {
        if (id == null || "".equals(id)) {
            this.invalidFiled = "id";
            if (logEx) {
                this.invalidFiled += ": " + id;
            }
            return false;
        }
        return true;
    }

    public boolean isValidTitle() {
        if (title == null || "".equals(title)) {
            this.invalidFiled = "title";
            if (logEx) {
                this.invalidFiled += ": " + title;
            }
            return false;
        }
        return true;
    }

    public boolean isValidDescription() {
        if (description == null || "".equals(description)) {
            this.invalidFiled = "description";
            if (logEx) {
                this.invalidFiled += ": " + description;
            }
            return false;
        }
        return true;
    }

    public boolean isValidLocationId() {
        //Hibás, ha null vagy ha nincs referenciája a location táblában
        if (location_id == null || "".equals(location_id) || !locids.contains(location_id)) {
            this.invalidFiled = "location_id";
            if (logEx) {
                this.invalidFiled += ": " + location_id;
            }
            return false;
        }
        return true;
    }

    public boolean isValidListingPrice() {
        //Hibás, ha 0, vagy ha nem két decimális formátumú 
        DecimalFormat df = new DecimalFormat("#.00");
        int decimalLength = (df.format(listing_price % 1).length() - 1);

        if (listing_price <= 0 || decimalLength != 2) {
            this.invalidFiled = "listing_price";
            if (logEx) {
                this.invalidFiled += ": " + listing_price;
            }
            return false;
        }
        return true;
    }

    public boolean isValidCurrency() {
        if (currency == null || currency.length() != 3) {
            this.invalidFiled = "currency";
            if (logEx) {
                this.invalidFiled += ": " + currency;
            }
            return false;
        }
        return true;
    }

    public boolean isValidQquantity() {
        if (quantity <= 0) {
            this.invalidFiled = "quantity";
            if (logEx) {
                this.invalidFiled += ": " + quantity;
            }
            return false;
        }
        return true;
    }

    public boolean isValidListingStatus() {
        //Hibás, ha 0, vagy nincs referenciája listing_status táblában
        if (listing_status <= 0 || !lstids.contains(listing_status)) {
            this.invalidFiled = "listing_status";
            if (logEx) {
                this.invalidFiled += ": " + listing_status;
            }
            return false;
        }
        return true;
    }

    public boolean isValidMarketplace() {
        //Hibás, ha 0, vagy nincs referenciája listing_status táblában
        if (marketplace <= 0 || !mplids.contains(marketplace)) {
            this.invalidFiled = "marketplace";
            if (logEx) {
                this.invalidFiled += ": " + marketplace;
            }
            return false;
        }
        return true;
    }

    public boolean isValidEmail() {
        Matcher matcher = VALID_EMAIL_REGEX.matcher(owner_email_address);
        if (!matcher.find()) {
            this.invalidFiled = "owner_email_address";
            if (logEx) {
                this.invalidFiled += ": " + owner_email_address;
            }
            return false;
        }
        return true;
    }

    public boolean validate() {
        return isValidId() && isValidTitle() && isValidDescription()
                && isValidLocationId() && isValidListingPrice()
                && isValidCurrency() && isValidQquantity() && isValidListingStatus()
                && validUploadTime && isValidMarketplace() && isValidEmail();
    }

}
