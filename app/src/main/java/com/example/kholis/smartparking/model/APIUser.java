package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class APIUser {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("alamat")
    @Expose
    private Object alamat;
    @SerializedName("no_telp")
    @Expose
    private Object noTelp;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("foto")
    @Expose
    private Object foto;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("verifikasi_pengguna")
    @Expose
    private String verifikasiPengguna;
    @SerializedName("disabled_key")
    @Expose
    private String disabledKey;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public Object getAlamat() {
        return alamat;
    }

    public void setAlamat(Object alamat) {
        this.alamat = alamat;
    }

    public Object getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(Object noTelp) {
        this.noTelp = noTelp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getFoto() {
        return foto;
    }

    public void setFoto(Object foto) {
        this.foto = foto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", namaLengkap='" + namaLengkap + '\'' +
                ", alamat=" + alamat +
                ", noTelp=" + noTelp +
                ", email='" + email + '\'' +
                ", foto=" + foto +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                ", verifikasiPengguna='" + verifikasiPengguna + '\'' +
                ", disabledKey='" + disabledKey + '\'' +
                ", deletedAt=" + deletedAt +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }

}
