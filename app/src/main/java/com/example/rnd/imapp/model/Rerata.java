package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/16/2016.
 */

public class Rerata {
    private String kode_barang, nama_barang, rerata;

    public Rerata() {
    }

    public Rerata(String kode_barang, String nama_barang, String rerata) {
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.rerata = rerata;
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

    public String getrerata() {
        return rerata;
    }

    public void setrerata(String rerata) {
        this.rerata = rerata;
    }
}
