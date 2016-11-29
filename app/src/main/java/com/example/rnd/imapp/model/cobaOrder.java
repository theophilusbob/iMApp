package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/14/2016.
 */

public class cobaOrder {
    private String email_cabang, estimasi_terima, id_jenis_barang, kode_barang, nama_barang,
    no_order, qty, satuan, status_kirim, tgl_order;

    public cobaOrder() {
    }

    public cobaOrder(String id_jenis_barang, String kode_barang, String nama_barang, String qty) {
        this.id_jenis_barang = id_jenis_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.qty = qty;
    }

    public cobaOrder(String tgl_order, String status_kirim, String estimasi_terima) {
        this.tgl_order = tgl_order;
        this.status_kirim = status_kirim;
        this.estimasi_terima = estimasi_terima;
    }

    public cobaOrder(String email_cabang, String estimasi_terima, String id_jenis_barang, String kode_barang, String nama_barang, String no_order, String qty, String satuan, String status_kirim, String tgl_order) {
        this.email_cabang = email_cabang;
        this.estimasi_terima = estimasi_terima;
        this.id_jenis_barang = id_jenis_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.no_order = no_order;
        this.qty = qty;
        this.satuan = satuan;
        this.status_kirim = status_kirim;
        this.tgl_order = tgl_order;
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

    public String getNo_order() {
        return no_order;
    }

    public void setNo_order(String no_order) {
        this.no_order = no_order;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getStatus_kirim() {
        return status_kirim;
    }

    public void setStatus_kirim(String status_kirim) {
        this.status_kirim = status_kirim;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public void setTgl_order(String tgl_order) {
        this.tgl_order = tgl_order;
    }
}
