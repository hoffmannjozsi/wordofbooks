package com.hoffmannjozsef.feladat;

/**
 *
 * @author Hoffmann József
 */
public class ListingStatus {
    //marketplace https://my.api.mockaroo.com/marketplace?key=63304c70  
    //number  Marketplace id
    private int id = 0;
    private String status_name = "";

    public ListingStatus() {
    }
    
     public ListingStatus(int id, String status_name) {
         this.id = id;
         this.status_name = status_name;
     }
         
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
    
    
    
    
}
