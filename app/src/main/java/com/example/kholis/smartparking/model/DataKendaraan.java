package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by norkholis on 06/02/18.
 */

public class DataKendaraan {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("id_pengguna")
        @Expose
        private Integer idPengguna;
        @SerializedName("nopol")
        @Expose
        private String nopol;
        @SerializedName("jenis_kendaraan")
        @Expose
        private String jenisKendaraan;
        @SerializedName("merk_kendaraan")
        @Expose
        private String merkKendaraan;
        @SerializedName("foto_depan")
        @Expose
        private String fotoDepan;
        @SerializedName("foto_belakang")
        @Expose
        private String fotoBelakang;
        @SerializedName("disabled_key")
        @Expose
        private String disabledKey;
        @SerializedName("blocked_kendaraan")
        @Expose
        private String blockedKendaraan;
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

        public String getNopol() {
            return nopol;
        }

        public void setNopol(String nopol) {
            this.nopol = nopol;
        }

        public String getJenisKendaraan() {
            return jenisKendaraan;
        }

        public void setJenisKendaraan(String jenisKendaraan) {
            this.jenisKendaraan = jenisKendaraan;
        }

        public String getMerkKendaraan() {
            return merkKendaraan;
        }

        public void setMerkKendaraan(String merkKendaraan) {
            this.merkKendaraan = merkKendaraan;
        }

        public String getFotoDepan() {
            return fotoDepan;
        }

        public void setFotoDepan(String fotoDepan) {
            this.fotoDepan = fotoDepan;
        }

        public String getFotoBelakang() {
            return fotoBelakang;
        }

        public void setFotoBelakang(String fotoBelakang) {
            this.fotoBelakang = fotoBelakang;
        }

        public String getDisabledKey() {
            return disabledKey;
        }

        public void setDisabledKey(String disabledKey) {
            this.disabledKey = disabledKey;
        }

        public String getBlockedKendaraan() {
            return blockedKendaraan;
        }

        public void setBlockedKendaraan(String blockedKendaraan) {
            this.blockedKendaraan = blockedKendaraan;
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
