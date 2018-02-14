package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by norkholis on 12/02/18.
 */

public class ResponseRegistrasi {
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("verifikasi_pengguna")
    @Expose
    private String verifikasiPengguna;
    @SerializedName("disabled_key")
    @Expose
    private String disabledKey;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVerifikasiPengguna() {
        return verifikasiPengguna;
    }

    public void setVerifikasiPengguna(String verifikasiPengguna) {
        this.verifikasiPengguna = verifikasiPengguna;
    }

    public String getDisabledKey() {
        return disabledKey;
    }

    public void setDisabledKey(String disabledKey) {
        this.disabledKey = disabledKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
