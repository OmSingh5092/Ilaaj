package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.dataModels.Prescription;

public class PrescriptionController {
    public interface prescriptionDatabaseHandler{
        void onSuccess(Prescription prescription);
        void onFailure(Exception e);
    }

    public  static void create(Prescription prescription, prescriptionDatabaseHandler handler){

    }

    public static void update(Prescription prescription, prescriptionDatabaseHandler handler){

    }

    public static void delete(Prescription prescription, prescriptionDatabaseHandler handler){

    }

}
