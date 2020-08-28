package com.codenite.ilaaj.api.responseModel.appointment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateAppointmentResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("message")
    @Expose
    String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
