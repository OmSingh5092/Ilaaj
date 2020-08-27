package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.dataModels.Appointment;

public class AppointmentController {
    public interface AppointmentDatabaseHandler{
        void onSuccess(Appointment appointment);
        void onFailure(Exception e);
    }

    public static void create(Appointment appointment,AppointmentDatabaseHandler handler){

    }

    public static void delete(Appointment appointment, AppointmentDatabaseHandler handler){

    }

    public static void update(Appointment appointment, AppointmentDatabaseHandler handler){

    }


}
