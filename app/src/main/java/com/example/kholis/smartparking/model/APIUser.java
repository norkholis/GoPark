package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIUser {

    @SerializedName("id_pengguna")
    @Expose
    private String idPengguna;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("no_telp")
    @Expose
    private String noTelp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("foto")
    @Expose
    private String foto;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("verifikasi_pengguna")
    @Expose
    private String verifikasiPengguna;
    @SerializedName("disabled_key")
    @Expose
    private String disabledKey;
    @SerializedName("blocked_pengguna")
    @Expose
    private String blockedPengguna;
    @SerializedName("tgl_update")
    @Expose
    private String tglUpdate;

    public String getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
    }

    public APIUser withIdPengguna(String idPengguna) {
        this.idPengguna = idPengguna;
        return this;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public APIUser withNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        return this;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public APIUser withAlamat(String alamat) {
        this.alamat = alamat;
        return this;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public APIUser withNoTelp(String noTelp) {
        this.noTelp = noTelp;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public APIUser withEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public APIUser withFoto(String foto) {
        this.foto = foto;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public APIUser withUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public APIUser withPassword(String password) {
        this.password = password;
        return this;
    }

    public String getVerifikasiPengguna() {
        return verifikasiPengguna;
    }

    public void setVerifikasiPengguna(String verifikasiPengguna) {
        this.verifikasiPengguna = verifikasiPengguna;
    }

    public APIUser withVerifikasiPengguna(String verifikasiPengguna) {
        this.verifikasiPengguna = verifikasiPengguna;
        return this;
    }

    public String getDisabledKey() {
        return disabledKey;
    }

    public void setDisabledKey(String disabledKey) {
        this.disabledKey = disabledKey;
    }

    public APIUser withDisabledKey(String disabledKey) {
        this.disabledKey = disabledKey;
        return this;
    }

    public String getBlockedPengguna() {
        return blockedPengguna;
    }

    public void setBlockedPengguna(String blockedPengguna) {
        this.blockedPengguna = blockedPengguna;
    }

    public APIUser withBlockedPengguna(String blockedPengguna) {
        this.blockedPengguna = blockedPengguna;
        return this;
    }

    public String getTglUpdate() {
        return tglUpdate;
    }

    public void setTglUpdate(String tglUpdate) {
        this.tglUpdate = tglUpdate;
    }

    public APIUser withTglUpdate(String tglUpdate) {
        this.tglUpdate = tglUpdate;
        return this;
    }

    public APIUser (String idPengguna, String namaLengkap, String alamat, String foto, String email, String password, String noTelp){
        this.idPengguna = idPengguna;
        this.namaLengkap = namaLengkap;
        this.alamat = alamat;
        this.foto = foto;
        this.email = email;
        this.password = password;
        this.noTelp = noTelp;
    }

}
