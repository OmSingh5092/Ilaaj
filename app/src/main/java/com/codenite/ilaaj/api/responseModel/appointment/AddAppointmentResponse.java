package com.codenite.ilaaj.api.responseModel.appointment;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddAppointmentResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("appointment")
    @Expose
    Appointment appointment;

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

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
