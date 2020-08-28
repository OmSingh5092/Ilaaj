package com.codenite.ilaaj.api.responseModel.appointment;

import com.codenite.ilaaj.api.dataModels.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllDoctorsResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("doctors")
    @Expose
    List<User> doctors;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<User> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<User> doctors) {
        this.doctors = doctors;
    }
}
