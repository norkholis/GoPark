package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by norkholis on 16/02/18.
 */

public class DataPesan {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_pengguna")
    @Expose
    private Integer idPengguna;
    @SerializedName("id_kendaraan")
    @Expose
    private Integer idKendaraan;
    @SerializedName("id_tempat")
    @Expose
    private Integer idTempat;
    @SerializedName("barcode")
    @Expose
    private Object barcode;
    @SerializedName("waktu_masuk")
    @Expose
    private Object waktuMasuk;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("waktu_keluar")
    @Expose
    private Object waktuKeluar;
    @SerializedName("estimasi_waktu")
    @Expose
    private Object estimasiWaktu;
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

    public Integer getIdPengguna() {
        return idPengguna;
    }

    public void setIdPengguna(Integer idPengguna) {
        this.idPengguna = idPengguna;
    }

    public Integer getIdKendaraan() {
        return idKendaraan;
    }

    public void setIdKendaraan(Integer idKendaraan) {
        this.idKendaraan = idKendaraan;
    }

    public Integer getIdTempat() {
        return idTempat;
    }

    public void setIdTempat(Integer idTempat) {
        this.idTempat = idTempat;
    }

    public Object getBarcode() {
        return barcode;
    }

    public void setBarcode(Object barcode) {
        this.barcode = barcode;
    }

    public Object getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Object waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(Object waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    public Object getEstimasiWaktu() {
        return estimasiWaktu;
    }

    public void setEstimasiWaktu(Object estimasiWaktu) {
        this.estimasiWaktu = estimasiWaktu;
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
}
