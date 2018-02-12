package com.example.kholis.smartparking.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by norkholis on 12/02/18.
 */

public class DataTempat {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("nama_tempat")
        @Expose
        private String namaTempat;
        @SerializedName("latitude")
        @Expose
        private String latitude;
        @SerializedName("longtitude")
        @Expose
        private String longtitude;
        @SerializedName("deskripsi")
        @Expose
        private String deskripsi;
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

        public String getNamaTempat() {
            return namaTempat;
        }

        public void setNamaTempat(String namaTempat) {
            this.namaTempat = namaTempat;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongtitude() {
            return longtitude;
        }

        public void setLongtitude(String longtitude) {
            this.longtitude = longtitude;
        }

        public String getDeskripsi() {
            return deskripsi;
        }

        public void setDeskripsi(String deskripsi) {
            this.deskripsi = deskripsi;
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
