/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class MyConfigTest {

    public MyConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        confblank = new MyConfig();
        confgood = new MyConfig("config.ini");
        confwrong = new MyConfig("wrongfilename.ini");
    }

    @After
    public void tearDown() {
    }

    MyConfig confblank;
    MyConfig confgood;
    MyConfig confwrong;

    @Test
    public void testSetProp() {
        confblank.setProp("config.ini");
        assertNotNull(confblank.getProp());
    }

    @Test
    public void testBlank() {
        assertEquals("localhost", confblank.getHostname());
        assertEquals("3306", confblank.getPort());
        assertEquals("wobDBdef", confblank.getDbName());
        assertEquals("0", confblank.getImportLogEx());
    }

    @Test
    public void testGood() {
        
        assertEquals("localhost", confgood.getHostname());
        assertEquals("3306", confgood.getPort());
        assertEquals("wobDBnew", confgood.getDbName());
        assertEquals("1", confgood.getNewDatabase());
    }

    @Test
    public void testWrong() {
        assertEquals("localhost", confwrong.getHostname());
        assertEquals("3306", confwrong.getPort());
        assertEquals("wobDBdef", confwrong.getDbName());
        assertEquals("0", confwrong.getImportLogEx());
    }

    }
