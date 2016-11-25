package com.example.rnd.imapp.model;

/**
 * Created by RnD on 25/11/2016.
 */

public class StockData {
    private String kode_barang, qty;

    public StockData() {
    }

    public StockData(String kode_barang, String qty) {
        this.kode_barang = kode_barang;
        this.qty = qty;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}
