package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/17/2016.
 */

public class HistoryOrder {
    private String id_jenis_barang, kode_barang, nama_barang, quantity, satuan;

    public HistoryOrder() {
    }

    public HistoryOrder(String id_jenis_barang, String kode_barang, String nama_barang, String quantity, String satuan) {
        this.id_jenis_barang = id_jenis_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.quantity = quantity;
        this.satuan = satuan;
    }

    public String getId_jenis_barang() {
        return id_jenis_barang;
    }

    public void setId_jenis_barang(String id_jenis_barang) {
        this.id_jenis_barang = id_jenis_barang;
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
