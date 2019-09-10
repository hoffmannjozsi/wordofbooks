/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hoffmannjozsef.feladat;

/**
 *
 * @author Hoffmann József
 */
public class Marketplace {
    private int id = 0;
    private String marketplace_name ="";

    public Marketplace() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarketplace_name() {
        return marketplace_name;
    }

    public void setMarketplace_name(String marketplace_name) {
        this.marketplace_name = marketplace_name;
    }
    
    
}
