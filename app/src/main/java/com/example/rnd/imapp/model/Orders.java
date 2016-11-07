package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/1/2016.
 */

public class Orders {
    private String kode_barang, nama_barang, satuan_pack, img_url;
    private int qty;

    public Orders() {
    }

    public Orders(String kode_barang, String nama_barang, String satuan_pack, String img_url, int qty) {
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.satuan_pack = satuan_pack;
        this.img_url = img_url;
        this.qty = qty;
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

    public String getSatuan_pack() {
        return satuan_pack;
    }

    public void setSatuan_pack(String satuan_pack) {
        this.satuan_pack = satuan_pack;
    }

    public int getQty() {
        return qty;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
