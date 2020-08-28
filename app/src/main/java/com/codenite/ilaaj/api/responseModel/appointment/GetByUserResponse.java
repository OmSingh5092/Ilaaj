package com.codenite.ilaaj.api.responseModel.appointment;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetByUserResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("appointments")
    @Expose
    List<Appointment> appointments;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}
