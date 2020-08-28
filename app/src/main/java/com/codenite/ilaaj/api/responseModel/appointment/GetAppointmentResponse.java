package com.codenite.ilaaj.api.responseModel.appointment;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAppointmentResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("appointment")
    @Expose
    Appointment appointment;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}
