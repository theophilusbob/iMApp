package com.example.rnd.imapp.model;

/**
 * Created by RnD on 24/11/2016.
 */

public class Barang {

    private String id_jenis_barang, kode_barang, nama_barang, satuan, url_gambar_barang;

    public Barang() {
    }

    public Barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public Barang(String id_jenis_barang, String kode_barang, String nama_barang, String satuan, String url_gambar_barang) {
        this.id_jenis_barang = id_jenis_barang;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.satuan = satuan;
        this.url_gambar_barang = url_gambar_barang;
    }

    public String getUrl_gambar_barang() {
        return url_gambar_barang;
    }

    public void setUrl_gambar_barang(String url_gambar_barang) {
        this.url_gambar_barang = url_gambar_barang;
    }

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getId_jenis_barang() {
        return id_jenis_barang;
    }

    public void setId_jenis_barang(String id_jenis_barang) {
        this.id_jenis_barang = id_jenis_barang;
    }
}
