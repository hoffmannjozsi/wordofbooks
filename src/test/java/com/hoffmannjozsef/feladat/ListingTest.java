package com.hoffmannjozsef.feladat;

import java.util.HashSet;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
//import com.hoffmannjozsef.feladat.Listing;

/**
 *
 * @author Hoffmann József
 */
@RunWith(MockitoJUnitRunner.class)
public class ListingTest {
	
	HashSet<String> locids = new HashSet<>();
	HashSet<Integer> lstids = new HashSet<>();
	HashSet<Integer> mplids = new HashSet<>();
        
	//Listing lst;
        
        @Mock
        MyConfig conf;
    
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
        
        
        
        locids.add("0fe479bb-fe39-4265-b59f-bb4ac5a060d4"); //first
        locids.add("5ae22efb-f875-4134-a03d-6402efa31dc5"); //mid
        locids.add("5249f33c-fadf-44d9-ab70-471df29c20a6"); //last
		
        lstids.add(1);
        lstids.add(2);
        lstids.add(3);
        
        mplids.add(1);
        mplids.add(2);
        
	//lst = new Listing(conf, locids, lstids, mplids);
        //lst.setLogEx(false);
        
    }

    @After
    public void tearDown() {
    }
    
    
    
    @Test
    public void testIsValidId() {
        Listing lst = new Listing(conf, locids, lstids, mplids);
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
        lst.setId(null);
        boolean result = lst.isValidId();
        assertFalse("Nem lett hamis null id esetén", result);
        assertEquals("Hamis eredmény esetén az invalidField értéke nem lett beállítva", "id", lst.getInvalidFiled());

        lst.setId("");
        result = lst.isValidId();
        assertFalse("Nem lett hamis üres id esetén", result);

        lst.setId("e4366199-61fe-4d85-9ee6-330dded66ca7");
        result = lst.isValidId();
        assertTrue("Nem lett igaz jó id esetén", result);
    }

    @Test
    public void testIsValidTitle() {
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
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
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
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
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
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
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
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

        lst.setListing_price(2.7);
        result = lst.isValidListingPrice();
        assertFalse("Nem lett hamis 2.7 - 1 tizedesjegy - listing_price esetén", result);

        lst.setListing_price(23.873);
        result = lst.isValidListingPrice();
        assertFalse("Nem lett hamis 23.373 - 3 tizedesjegy - listing_price esetén", result);

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
        when(conf.getImportLogEx()).thenReturn("0");
        when(conf.getUploadTimeValidation()).thenReturn("1");
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

}
