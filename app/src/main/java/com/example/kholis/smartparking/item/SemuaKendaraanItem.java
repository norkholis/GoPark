package com.example.kholis.smartparking.item;

/**
 * Created by norkholis on 26/01/18.
 */

public class SemuaKendaraanItem {
    private int id;
    private int id_pengguna;

    private String nopol;
    private String jenis_kendaraan;
    private String merk_kendaraan;

    private String foto_depan;

    private String foto_belakang;
    private String disabled_key;
    private String blocked_kendaraan;
    private String created_at;
    private String updated_at;


    public SemuaKendaraanItem(int id, int id_pengguna, String nopol, String jenis_kendaraan, String merk_kendaraan, String foto_depan, String foto_belakang, String disabled_key, String blocked_kendaraan, String created_at, String updated_at) {
        this.id = id;
        this.id_pengguna = id_pengguna;
        this.nopol = nopol;
        this.jenis_kendaraan = jenis_kendaraan;
        this.merk_kendaraan = merk_kendaraan;
        this.foto_depan = foto_depan;
        this.foto_belakang = foto_belakang;
        this.disabled_key = disabled_key;
        this.blocked_kendaraan = blocked_kendaraan;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_pengguna() {
        return id_pengguna;
    }

    public void setId_pengguna(int id_pengguna) {
        this.id_pengguna = id_pengguna;
    }

    public String getNopol() {
        return nopol;
    }

    public void setNopol(String nopol) {
        this.nopol = nopol;
    }

    public String getJenis_kendaraan() {
        return jenis_kendaraan;
    }

    public void setJenis_kendaraan(String jenis_kendaraan) {
        this.jenis_kendaraan = jenis_kendaraan;
    }

    public String getMerk_kendaraan() {
        return merk_kendaraan;
    }

    public void setMerk_kendaraan(String merk_kendaraan) {
        this.merk_kendaraan = merk_kendaraan;
    }

    public String getFoto_depan() {
        return foto_depan;
    }

    public void setFoto_depan(String foto_depan) {
        this.foto_depan = foto_depan;
    }

    public String getFoto_belakang() {
        return foto_belakang;
    }

    public void setFoto_belakang(String foto_belakang) {
        this.foto_belakang = foto_belakang;
    }

    public String getDisabled_key() {
        return disabled_key;
    }

    public void setDisabled_key(String disabled_key) {
        this.disabled_key = disabled_key;
    }

    public String getBlocked_kendaraan() {
        return blocked_kendaraan;
    }

    public void setBlocked_kendaraan(String blocked_kendaraan) {
        this.blocked_kendaraan = blocked_kendaraan;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
