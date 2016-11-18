package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/17/2016.
 */

public class ACK {
    private String jml_order, kode_barang, nama_barang, satuan;
    boolean selected = false;

    public ACK() {
    }

    public ACK(String jml_order, String kode_barang, String nama_barang, String satuan, boolean selected) {
        this.jml_order = jml_order;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.satuan = satuan;
        this.selected = selected;
    }

    public String getJml_order() {
        return jml_order;
    }

    public void setJml_order(String jml_order) {
        this.jml_order = jml_order;
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

    public String getSatuan() {
        return satuan;
    }

    public void setSatuan(String satuan) {
        this.satuan = satuan;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
