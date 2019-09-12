package com.hoffmannjozsef.feladat;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hoffmann József
 */
public class MarketplaceTest {

    public MarketplaceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        mp = new Marketplace();
    }

    @After
    public void tearDown() {
    }

    Marketplace mp;

    @Test
    public void testGetId() {
        mp.setId(2);
        assertEquals("Nem adja visssa a beálított értéket", 2, mp.getId());
    }

    @Test
    public void testGetMarketplace_name() {
        mp.setMarketplace_name("AMAZON");
        assertEquals("Nem adja visssa a beálított értéket", "AMAZON", mp.getMarketplace_name());
    }

}
