package com.hoffmannjozsef.feladat;

import java.util.HashSet;

/**
 *
 * @author Hoffmann József
 */
public interface DataStoringRepositoryInterface {
    public void truncateTable(String tableName);
    public void addListingStatus(ListingStatus lst);
    public void addMarketplace(Marketplace mp);
    public void addLocation(Location loc);
    public void addListing(Listing list);
    public HashSet<String> getLocationId();
    public HashSet<Integer> getListingStatusId();
    public HashSet<Integer> getMarketplaceId();
    public String getMarketplaceName(int id);
}
