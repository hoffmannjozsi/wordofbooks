package com.hoffmannjozsef.feladat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.HashSet;

/**
 *
 * @author Hoffmann József
 */
public class JsonToDB {

    MyConfig myconf;
    DataStoringRepositoryImpl db;

    public JsonToDB(DataStoringRepositoryImpl db) {
        this.db = db;
        this.myconf = db.myconf;
    }

    public void listingStatusToDB() {
        try {
            URL url = new URL("https://my.api.mockaroo.com/listingStatus?key=63304c70");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(url);
            if (root.isObject()) {
                System.out.println("Object");
            }
            if (root.isArray()) {
                //db.truncateTable("listingStatus");
                for (int i = 0; i < root.size(); i++) {
                    ListingStatus listingStatus = new ListingStatus();
                    listingStatus.setId(root.get(i).get("id").intValue());
                    listingStatus.setStatus_name(root.get(i).get("status_name").asText());
                    db.addListingStatus(listingStatus);
                }                
            }
        } catch (IOException ex) {
            System.out.println("Error reading listingStatus" + ex.getMessage());
        }
        System.out.println("The listingStatus table is loaded");
    }

    public void marketplaceToDB() {
        try {
            URL url = new URL("https://my.api.mockaroo.com/marketplace?key=63304c70");
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(url);
            if (root.isObject()) {
                System.out.println("Object");
            }
            if (root.isArray()) {
                //db.truncateTable("marketplace");
                for (int i = 0; i < root.size(); i++) {
                    Marketplace mp = new Marketplace();
                    mp.setId(root.get(i).get("id").intValue());
                    mp.setMarketplace_name(root.get(i).get("marketplace_name").asText());
                    db.addMarketplace(mp);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading marketplace" + ex.getMessage());
        }
        System.out.println("The marketplace table is loaded");
    }

    public void locationToDB() {
        try {
            URL urlLocation = new URL("https://my.api.mockaroo.com/location?key=63304c70");
            ObjectMapper mapperLocation = new ObjectMapper();
            JsonNode root = mapperLocation.readTree(urlLocation);
            if (root.isObject()) {
                System.out.println("Object");
            }
            if (root.isArray()) {
                //db.truncateTable("location");
                for (int i = 0; i < root.size(); i++) {
                    Location lc = new Location();
                    lc.setId(root.get(i).get("id").asText());
                    lc.setManager_name(root.get(i).get("manager_name").asText());
                    lc.setPhone(root.get(i).get("phone").asText());
                    lc.setAddress_primary(root.get(i).get("address_primary").asText());
                    lc.setAddress_secondary(root.get(i).get("address_secondary").asText());
                    lc.setCountry(root.get(i).get("country").asText());
                    lc.setTown(root.get(i).get("town").asText());
                    lc.setPostal_code(root.get(i).get("postal_code").asText());
                    db.addLocation(lc);
                }
            }
        } catch (IOException ex) {
            System.out.println("Error reading location" + ex.getMessage());
        }
        System.out.println("The location table is loaded");
    }

    public void listingToDB() {
        try {
            URL urlLocation = new URL("https://my.api.mockaroo.com/listing?key=63304c70");
            ObjectMapper mapperLocation = new ObjectMapper();
            JsonNode root = mapperLocation.readTree(urlLocation);
            if (root.isObject()) {
                System.out.println("Object");
            }
            HashSet<String> locids = db.getLocationId();
            HashSet<Integer> lstids = db.getListingStatusId();
            HashSet<Integer> mplIds = db.getMarketplaceId();
            if (root.isArray()) {
                try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("importLog.csv")))) {
                    pw.print("ListingId;MarketplaceName;InvalidField");
                    pw.println();
                    //Listing tábla kiürítése
                    db.truncateTable("listing");
                    for (int i = 0; i < root.size(); i++) {
                        
                        Listing list = new Listing(myconf, locids, lstids, mplIds);
                        //list.setMyConfig(myconf);
                        list.setId(root.get(i).get("id").asText());
                        list.setTitle(root.get(i).get("title").asText());
                        list.setDescription(root.get(i).get("description").asText());
                        list.setLocation_id(root.get(i).get("location_id").asText());
                        list.setListing_price(root.get(i).get("listing_price").asDouble());
                        list.setCurrency(root.get(i).get("currency").asText());
                        list.setQuantity(root.get(i).get("quantity").asInt());
                        list.setListing_status(root.get(i).get("listing_status").asInt());
                        list.setMarketplace(root.get(i).get("marketplace").asInt());
                        list.setUpload_time(root.get(i).get("upload_time").asText());
                        list.setOwner_email_address(root.get(i).get("owner_email_address").asText());

                        if (list.validate()) {
                            db.addListing(list);
                        } else {
                            pw.print(list.getId());
                            pw.print(";");
                            pw.print(db.getMarketplaceName(list.getMarketplace()));
                            pw.print(";");
                            pw.print(list.getInvalidFiled());
                            pw.println();
                        }
                       
                    }
                    
                } catch (IOException ex) {
                    System.out.println("Hiba az importLog.csv fájl írása közben.");
                }
                System.out.println("The importLog.csv is ready.");
            }
        } catch (IOException ex) {
            System.out.println("Error reading listing" + ex.getMessage());
        }
        System.out.println("The listing table is loaded");
    }

}
