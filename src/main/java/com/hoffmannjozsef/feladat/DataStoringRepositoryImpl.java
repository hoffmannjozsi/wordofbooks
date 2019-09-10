package com.hoffmannjozsef.feladat;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Hoffmann József
 */
public class DataStoringRepositoryImpl implements DataStoringRepositoryInterface {

    MyConfig myconf;
    DBConnect conn;
    private final PreparedStatement truncateTable;
    private final PreparedStatement addListingStatus;
    private final PreparedStatement addMarketplace;
    private final PreparedStatement addLocation;
    private final PreparedStatement addListing;
    private final PreparedStatement getLocationId;
    private final PreparedStatement getListingStatusId;
    private final PreparedStatement getMarketplaceId;
    private final PreparedStatement getMarketplaceName;

    public DataStoringRepositoryImpl(MyConfig myconf, DBConnect conn) throws SQLException {
        this.myconf = myconf;
        this.conn = conn;
        this.truncateTable = conn.getConn().prepareStatement("TRUNCATE listing");
        this.addListingStatus = conn.getConn().prepareStatement("INSERT INTO listingstatus(id, status_name) VALUES (?, ?)");
        this.addMarketplace = conn.getConn().prepareStatement("INSERT INTO Marketplace(id, marketplace_name) VALUES (?, ?)");
        this.addLocation = conn.getConn().prepareStatement("INSERT INTO location(id, manager_name, phone, address_primary, "
                + "address_secondary, country, town, postal_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        this.addListing = conn.getConn().prepareStatement("INSERT INTO listing(id, title, description, inventory_item_location_id, "
                + "listing_price, currency, quantity, listing_status, marketplace, upload_time, owner_email_address ) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        this.getLocationId = conn.getConn().prepareStatement("SELECT id FROM location");
        this.getListingStatusId = conn.getConn().prepareStatement("SELECT id FROM listingstatus");
        this.getMarketplaceId = conn.getConn().prepareStatement("SELECT id FROM marketplace");
        this.getMarketplaceName = conn.getConn().prepareStatement("SELECT marketplace_name FROM marketplace WHERE id= ?");
    }

    @Override
    public void truncateTable() {
        try {
            this.truncateTable.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Hiba a 'listing' tábla kiürítésekor. Üzenet: " + ex.getMessage());
        }
    }

    @Override
    public void addListingStatus(ListingStatus lst) {
        try {
            this.addListingStatus.setInt(1, lst.getId());
            this.addListingStatus.setString(2, lst.getStatus_name());         
            this.addListingStatus.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Hiba a ListingStatus táblába való hozzáadáskor. Üzenet: " + ex.getMessage());
        }
    }

    @Override
    public void addMarketplace(Marketplace mp) {
        try {
            this.addMarketplace.setInt(1, mp.getId());
            this.addMarketplace.setString(2, mp.getMarketplace_name());
            this.addMarketplace.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Hiba a Marketplace táblába való hozzáadáskor. Üzenet: " + ex.getMessage());
        }
    }

    @Override
    public void addLocation(Location loc) {
        try {
            this.addLocation.setString(1, loc.getId());
            this.addLocation.setString(2, loc.getManager_name());
            this.addLocation.setString(3, loc.getPhone());
            this.addLocation.setString(4, loc.getAddress_primary());
            this.addLocation.setString(5, loc.getAddress_secondary());
            this.addLocation.setString(6, loc.getCountry());
            this.addLocation.setString(7, loc.getTown());
            this.addLocation.setString(8, loc.getPostal_code());
            this.addLocation.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Hiba a Location táblába való hozzáadáskor. Üzenet: " + ex.getMessage());
        }
    }

    @Override
    public void addListing(Listing list) {
        try {           
            this.addListing.setString(1, list.getId());
            this.addListing.setString(2, list.getTitle());
            this.addListing.setString(3, list.getDescription());
            this.addListing.setString(4, list.getLocation_id());
            this.addListing.setDouble(5, list.getListing_price());
            this.addListing.setString(6, list.getCurrency());
            this.addListing.setInt(7, list.getQuantity());
            this.addListing.setInt(8, list.getListing_status());
            this.addListing.setInt(9, list.getMarketplace());
            //if (list.getUpload_time()!=null)
            this.addListing.setDate(10, Date.valueOf(list.getUpload_time()));
            this.addListing.setString(11, list.getOwner_email_address());
            this.addListing.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Hiba a listing táblába való hozzáadáskor. Üzenet: " + ex.getMessage());
        }
    }

    @Override
    public HashSet<String> getLocationId() {
        HashSet<String> locIds = new HashSet<>();
        ResultSet all;
        try {
            all = this.getLocationId.executeQuery();
            while (all.next()) {
                locIds.add(all.getString("id"));
            }

        } catch (SQLException ex) {
            System.out.println("Hiba a location tábla id-inek kiolvasásakor: " + ex.getMessage());
        }
        return locIds;
    }

    @Override
    public HashSet<Integer> getListingStatusId() {
        HashSet<Integer> lstIds = new HashSet<>();
        ResultSet all;
        try {
            all = this.getListingStatusId.executeQuery();
            while (all.next()) {
                lstIds.add(all.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println("Hiba a listingstatus tábla id-inek kiolvasásakor: " + ex.getMessage());
        }
        return lstIds;
    }

    @Override
    public HashSet<Integer> getMarketplaceId() {
        HashSet<Integer> mplIds = new HashSet<>();
        ResultSet all;
        try {
            all = this.getMarketplaceId.executeQuery();
            while (all.next()) {
                mplIds.add(all.getInt("id"));
            }

        } catch (SQLException ex) {
            System.out.println("Hiba a listingstatus tábla id-inek kiolvasásakor: " + ex.getMessage());
        }
        return mplIds;
    }

    @Override
    public String getMarketplaceName(int id) {
        ResultSet all;
        String mplName = "";
        try {
            this.getMarketplaceName.setInt(1, id);
            all = this.getMarketplaceName.executeQuery();
            all.next();
            mplName = all.getString("marketplace_name");
        } catch (SQLException ex) {
            System.out.println("Hiba a marketplace_name lekérdezésekor. Üzenet: " + ex.getMessage());
        }
        return mplName;
    }

}
