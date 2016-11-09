package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/9/2016.
 */

public class StockOpname {
    private String kode_barang, nama_barang, quantity;

    public StockOpname() {
    }

    public StockOpname(String kode_barang, String nama_barang, String quantity) {
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.quantity = quantity;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
