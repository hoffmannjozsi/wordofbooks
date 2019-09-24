package com.hoffmannjozsef.feladat;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import static org.mockito.Mockito.when;
//import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Hoffmann József
 */
//@RunWith(MockitoJUnitRunner.class)
public class ListingTest {

    //Listing lst;
    public ListingTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        HashSet<String> locids = new HashSet<>();
        HashSet<Integer> lstids = new HashSet<>();
        HashSet<Integer> mplids = new HashSet<>();

        locids.add("0fe479bb-fe39-4265-b59f-bb4ac5a060d4"); //first
        locids.add("5ae22efb-f875-4134-a03d-6402efa31dc5"); //mid
        locids.add("5249f33c-fadf-44d9-ab70-471df29c20a6"); //last

        lstids.add(1);
        lstids.add(2);
        lstids.add(3);

        mplids.add(1);
        mplids.add(2);

        MyConfig conf = MyConfig.instance();

        //lst = new Listing();
        lst = new Listing(conf, locids, lstids, mplids);
        //lst.setLogEx(false);

    }

    @After
    public void tearDown() {
    }

    Listing lst;

    @Test
    public void IsValidIdtest() {

//        when(conf.getImportLogEx()).thenReturn("0");
//        when(conf.getUploadTimeValidation()).thenReturn("1");
        lst.setId(null);
        boolean result = lst.isValidId();
        assertFalse("Nem lett hamis null id esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "id", lst.getInvalidFiled());

        lst.setId("");
        result = lst.isValidId();
        assertFalse("Nem lett hamis üres id esetén", result);

        lst.setId("5ae22efb-f875-4134-a03d-6402efa31dc5");
        result = lst.isValidId();
        assertTrue("Nem lett igaz jó id esetén", result);
    }

    @Test
    public void testIsValidTitle() {
        lst.setTitle(null);
        boolean result = lst.isValidTitle();
        assertFalse("Nem lett hamis null title esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "title", lst.getInvalidFiled());

        lst.setTitle("");
        result = lst.isValidTitle();
        assertFalse("Nem lett hamis üres title esetén", result);

        lst.setTitle("Oahu Sandmat");
        result = lst.isValidTitle();
        assertTrue("Nem lett igaz jó title érték esetén", result);
    }

    @Test
    public void testIsValidDescription() {
        lst.setDescription(null);
        boolean result = lst.isValidDescription();
        assertFalse("Nem lett hamis null description esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "description", lst.getInvalidFiled());

        lst.setDescription("");
        result = lst.isValidDescription();
        assertFalse("Nem lett hamis üres description esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "description", lst.getInvalidFiled());

        lst.setDescription("Chamaesyce deppeana (Boiss.) Millsp.");
        result = lst.isValidDescription();
        assertTrue("Nem lett igaz jó description érték esetén", result);
    }

    @Test
    public void testIsValidLocationId() {
        lst.setLocation_id(null);
        boolean result = lst.isValidLocationId();
        assertFalse("Nem lett hamis null location_id esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "location_id", lst.getInvalidFiled());

        lst.setLocation_id("");
        result = lst.isValidLocationId();
        assertFalse("Nem lett hamis üres location_id esetén", result);

        lst.setLocation_id("invalid-location-id-value");
        result = lst.isValidLocationId();
        assertFalse("Nem lett hamis érvénytelen - nem létező - location_id esetén", result);

        lst.setLocation_id("0fe479bb-fe39-4265-b59f-bb4ac5a060d4");
        result = lst.isValidLocationId();
        assertTrue("Nem lett igaz érvényes - létező - location_id esetén: első id)", result);

        lst.setLocation_id("5249f33c-fadf-44d9-ab70-471df29c20a6");
        result = lst.isValidLocationId();
        assertTrue("Nem lett igaz érvényes - létező - location_id esetén: utolsó id", result);
    }

    @Test
    public void testIsValidListingPrice() {
        lst.setListing_price(0);
        boolean result = lst.isValidListingPrice();
        assertFalse("Nem lett hamis 0 listing_price esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "listing_price", lst.getInvalidFiled());

        lst.setListing_price(-14.23);
        result = lst.isValidListingPrice();
        assertFalse("Nem lett hamis -14.23 - negatív - listing_price esetén", result);

        lst.setListing_price(-0.01);
        result = lst.isValidListingPrice();
        assertFalse("Nem lett hamis -0.01 - negatív - listing_price esetén", result);

        lst.setListing_price(1.07);
        result = lst.isValidListingPrice();
        assertTrue("Nem lett igaz jó listing_price esetén", result);

        lst.setListing_price(23.99);
        result = lst.isValidListingPrice();
        assertTrue("Nem lett igaz jó listing_price esetén", result);

        lst.setListing_price(1000.01);
        result = lst.isValidListingPrice();
        assertTrue("Nem lett igaz jó listing_price esetén", result);

        lst.setListing_price(0.01);
        result = lst.isValidListingPrice();
        assertTrue("Nem lett igaz jó listing_price esetén", result);
    }

    @Test
    public void testIsValidCurrency() {
        lst.setCurrency(null);
        boolean result = lst.isValidCurrency();
        assertFalse("Nem lett hamis null currency esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "currency", lst.getInvalidFiled());

        lst.setCurrency("");
        result = lst.isValidCurrency();
        assertFalse("Nem lett hamis üres currency esetén", result);

        lst.setCurrency("H");
        result = lst.isValidCurrency();
        assertFalse("Nem lett hamis 1 hosszú currency esetén", result);

        lst.setCurrency("FT");
        result = lst.isValidCurrency();
        assertFalse("Nem lett hamis 2 hosszú currency esetén", result);

        lst.setCurrency("USDD");
        result = lst.isValidCurrency();
        assertFalse("Nem lett hamis 4 hosszú currency esetén", result);

        lst.setCurrency("HUF");
        result = lst.isValidCurrency();
        assertTrue("Nem lett igaz 3 hosszú currency esetén", result);
    }

    @Test
    public void testIsValidQuantity() {
        lst.setQuantity(0);
        assertFalse("Nem lett hamis 0 quantity esetén", lst.isValidQuantity());
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "quantity", lst.getInvalidFiled());

        lst.setQuantity(-5);
        assertFalse("Nem lett hamis negatív quantity esetén", lst.isValidQuantity());
    }

    @Test
    public void testIsValidListingStatus() {
        //Hibás, ha 0, vagy nincs referenciája listing_status táblában
        lst.setListing_status(0);
        assertFalse("Nem lett hamis 0 ListingStatus esetén", lst.isValidListingStatus());
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "listing_status", lst.getInvalidFiled());

        lst.setListing_status(-1);
        assertFalse("Nem lett hamis negatív ListingStatus esetén", lst.isValidListingStatus());

        lst.setListing_status(4);
        assertFalse("Nem lett hamis érvénytelen (4) ListingStatus esetén", lst.isValidListingStatus());

        lst.setListing_status(12);
        assertFalse("Nem lett hamis érvénytelen (12) ListingStatus esetén", lst.isValidListingStatus());

        lst.setListing_status(1);
        assertTrue("Nem lett igaz érvényes (1) ListingStatus esetén", lst.isValidListingStatus());

        lst.setListing_status(3);
        assertTrue("Nem lett igaz érvényes (3) ListingStatus esetén", lst.isValidListingStatus());
    }

    @Test
    public void testIsValidMarketplace() {
        //Hibás, ha 0, vagy nincs referenciája marketplace táblában
        lst.setMarketplace(0);
        assertFalse("Nem lett hamis 0 marketplace esetén", lst.isValidMarketplace());
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "marketplace", lst.getInvalidFiled());

        lst.setMarketplace(-1);
        assertFalse("Nem lett hamis negatív marketplace esetén", lst.isValidMarketplace());

        lst.setMarketplace(3);
        assertFalse("Nem lett hamis érvénytelen (3) marketplace esetén", lst.isValidMarketplace());

        lst.setMarketplace(1254);
        assertFalse("Nem lett hamis érvénytelen (1254) marketplace esetén", lst.isValidMarketplace());

        lst.setMarketplace(1);
        assertTrue("Nem lett igaz érvényes (1) marketplace esetén", lst.isValidMarketplace());

        lst.setMarketplace(2);
        assertTrue("Nem lett igaz érvényes (2) marketplace esetén", lst.isValidMarketplace());
    }

    @Test
    public void testIsValidEmail() {
        lst.setOwner_email_address("valami.com");
        assertFalse("Nem lett hamis helytelen email (valami.com) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@.com");
        assertFalse("Nem lett hamis helytelen email (valami@.com) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol..com");
        assertFalse("Nem lett hamis helytelen email (valami@valahol..com) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol.com.");
        assertFalse("Nem lett hamis helytelen email (valami@valahol.com.) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol");
        assertFalse("Nem lett hamis helytelen email (valami@valahol) esetén", lst.isValidEmail());

        lst.setOwner_email_address("val@ami@valahol.hu");
        assertFalse("Nem lett hamis helytelen email (val@ami@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("val!ami@valahol.hu");
        assertFalse("Nem lett hamis helytelen email (val!ami@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("val#ami@valahol.hu");
        assertFalse("Nem lett hamis helytelen email (val#ami@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol.hu");
        assertTrue("Hamis lett helyes email (valami@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami12@valahol.hu");
        assertTrue("Hamis lett helyes email (valami12@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("vala_mi@valahol.hu");
        assertTrue("Hamis lett helyes email (vala_mi@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol.mashol.hu");
        assertTrue("Hamis lett helyes email (valami@valahol.mashol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("vALami@valahol.hu");
        assertTrue("Hamis lett helyes email (vALami@valahol.hu) esetén", lst.isValidEmail());

        lst.setOwner_email_address("valami@valahol.HU");
        assertTrue("Hamis lett helyes email (valami@valahol.HU) esetén", lst.isValidEmail());
    }
    
    @Test
    public void testSetUpload_time() {
        lst.setUpload_time("12/20/2018");
        assertEquals(12, lst.getUpload_time().getMonthValue());
        assertEquals(20, lst.getUpload_time().getDayOfMonth());
        assertEquals(2018, lst.getUpload_time().getYear());
        
        lst.setUpload_time("1/2/2019");
        assertEquals(1, lst.getUpload_time().getMonthValue());
        assertEquals(2, lst.getUpload_time().getDayOfMonth());
        assertEquals(2019, lst.getUpload_time().getYear());
        
         lst.setUpload_time("01/05/2018");
        assertEquals(1, lst.getUpload_time().getMonthValue());
        assertEquals(5, lst.getUpload_time().getDayOfMonth());
        assertEquals(2018, lst.getUpload_time().getYear());
        
        lst.setUpload_time("");
        assertEquals(1, lst.getUpload_time().getMonthValue());
        assertEquals(2, lst.getUpload_time().getDayOfMonth());
        assertEquals(1000, lst.getUpload_time().getYear());
        
        lst.setUpload_time("01052018");
        assertEquals(1, lst.getUpload_time().getMonthValue());
        assertEquals(2, lst.getUpload_time().getDayOfMonth());
        assertEquals(1000, lst.getUpload_time().getYear());
                
    }

}
