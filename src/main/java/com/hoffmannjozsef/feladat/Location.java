package com.hoffmannjozsef.feladat;

/**
 *
 * @author Hoffmann József
 */
public class Location {

    private String id = ""; // UUID  Location identifier  
    private String manager_name = "";
    private String phone = "";
    private String address_primary = "";
    private String address_secondary = "";
    private String country = "";
    private String town = "";
    private String postal_code = "";

    public Location() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getManager_name() {
        return manager_name;
    }

    public void setManager_name(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress_primary() {
        return address_primary;
    }

    public void setAddress_primary(String address_primary) {
        this.address_primary = address_primary;
    }

    public String getAddress_secondary() {
        return address_secondary;
    }

    public void setAddress_secondary(String address_secondary) {
        this.address_secondary = address_secondary;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    @Override
    public String toString() {
        return "Location{" + "id=" + id + ", manager_name=" + manager_name + ", phone=" + phone + ", address_primary=" + address_primary + ", address_secondary=" + address_secondary + ", country=" + country + ", town=" + town + ", postal_code=" + postal_code + '}';
    }
    
    
    
}
