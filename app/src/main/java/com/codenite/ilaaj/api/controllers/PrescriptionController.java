package com.codenite.ilaaj.api.controllers;

public class PrescriptionController {
    public interface prescriptionEvent{
        void onUpdate();
        void onDelete();
        void onCreate();
    }

}
