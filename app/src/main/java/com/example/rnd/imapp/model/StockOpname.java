package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/9/2016.
 */

public class StockOpname {
    private String nama_cabang, id_jenis_barang, kode_barang, nama_barang, quantity, satuan;

    public StockOpname() {
    }

    public StockOpname(String nama_cabang, String id_jenis_barang, String kode_barang, String nama_barang, String quantity, String satuan) {
        this.nama_cabang = nama_cabang;
        this.id_jenis_barang = id_jenis_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
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

    public String getId_jenis_barang() {
        return id_jenis_barang;
    }

    public void setId_jenis_barang(String id_jenis_barang) {
        this.id_jenis_barang = id_jenis_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
