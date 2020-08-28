package com.codenite.ilaaj.api.dataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    @SerializedName("id")
    @Expose
    int id;
    @SerializedName("name")
    @Expose
    String name;
    @SerializedName("phone")
    @Expose
    String phone;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("age")
    @Expose
    int age;
    @SerializedName("type")
    @Expose
    String type;
    @SerializedName("hash")
    @Expose
    String hash;
    @SerializedName("new_user")
    @Expose
    boolean newUser;
    @SerializedName("doctor")
    @Expose
    boolean doctor;
    @SerializedName("documents")
    @Expose
    List<Record> documents;

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public List<Record> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Record> documents) {
        this.documents = documents;
    }

    public boolean isNewUser() {
        return newUser;
    }

    public void setNewUser(boolean newUser) {
        this.newUser = newUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
