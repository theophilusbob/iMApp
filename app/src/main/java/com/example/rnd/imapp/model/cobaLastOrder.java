package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/16/2016.
 */

public class cobaLastOrder {
    private String kode_barang, nama_barang, quantity, satuan;

    public cobaLastOrder() {
    }

    public cobaLastOrder(String satuan, String quantity, String nama_barang, String kode_barang) {
        this.satuan = satuan;
        this.quantity = quantity;
        this.nama_barang = nama_barang;
        this.kode_barang = kode_barang;
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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
