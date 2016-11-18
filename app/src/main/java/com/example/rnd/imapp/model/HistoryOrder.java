package com.example.rnd.imapp.model;

/**
 * Created by RND on 11/17/2016.
 */

public class HistoryOrder {
    private String kode_barang, no_order, satuan_besar, tgl_order;
    private int qty;

    public HistoryOrder() {
    }

    public HistoryOrder(String kode_barang, String no_order, String satuan_besar, String tgl_order, int qty) {
        this.kode_barang = kode_barang;
        this.no_order = no_order;
        this.satuan_besar = satuan_besar;
        this.tgl_order = tgl_order;
        this.qty = qty;
    }

    public String getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(String kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNo_order() {
        return no_order;
    }

    public void setNo_order(String no_order) {
        this.no_order = no_order;
    }

    public String getSatuan_besar() {
        return satuan_besar;
    }

    public void setSatuan_besar(String satuan_besar) {
        this.satuan_besar = satuan_besar;
    }

    public String getTgl_order() {
        return tgl_order;
    }

    public void setTgl_order(String tgl_order) {
        this.tgl_order = tgl_order;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }
}
