package com.codenite.ilaaj.api.dataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prescription {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("type")
    @Expose
    int type;
    @SerializedName("verified")
    @Expose
    boolean isVerified;
    @SerializedName("link")
    @Expose
    String link;
    @SerializedName("patient_id")
    @Expose
    int patientId;
    @SerializedName("doctor_id")
    @Expose
    int doctorId;
    @SerializedName("hash")
    @Expose
    String hash;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
