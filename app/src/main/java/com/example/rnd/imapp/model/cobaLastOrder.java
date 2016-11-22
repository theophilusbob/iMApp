package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/16/2016.
 */

public class cobaLastOrder {
    private String email_cabang, estimasi_terima, tgl_order, kode_barang, nama_barang, qty, status, satuan;

    public cobaLastOrder() {
    }

    public cobaLastOrder(String email_cabang, String estimasi_terima, String tgl_order, String kode_barang, String nama_barang, String qty, String status, String satuan) {
        this.email_cabang = email_cabang;
        this.estimasi_terima = estimasi_terima;
        this.tgl_order = tgl_order;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.qty = qty;
        this.status = status;
        this.satuan = satuan;
    }

    public String getEmail_cabang() {
        return email_cabang;
    }

    public void setEmail_cabang(String email_cabang) {
        this.email_cabang = email_cabang;
    }

    public String getEstimasi_terima() {
        return estimasi_terima;
    }

    public void setEstimasi_terima(String estimasi_terima) {
        this.estimasi_terima = estimasi_terima;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public void setTgl_order(String tgl_order) {
        this.tgl_order = tgl_order;
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

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }
}
