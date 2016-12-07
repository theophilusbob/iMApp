package com.example.rnd.imapp.model;

/**
 * Created by RnD on 07/12/2016.
 */

public class User {
    private String email_cabang, nama_cabang;

    public User() {
    }

    public User(String email_cabang, String nama_cabang) {
        this.email_cabang = email_cabang;
        this.nama_cabang = nama_cabang;
    }

    public String getEmail_cabang() {
        return email_cabang;
    }

    public void setEmail_cabang(String email_cabang) {
        this.email_cabang = email_cabang;
    }

    public String getNama_cabang() {
        return nama_cabang;
    }

    public void setNama_cabang(String nama_cabang) {
        this.nama_cabang = nama_cabang;
    }
}
