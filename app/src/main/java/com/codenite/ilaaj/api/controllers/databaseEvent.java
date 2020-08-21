package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.models.Prescription;
import com.codenite.ilaaj.api.models.Record;
import com.codenite.ilaaj.api.models.User;

public interface databaseEvent {
    void onUpdate(Record record);
    void onUpdate(Prescription prescription);
    void onUpdate(User user);

    void onDelete();

    void onCreate(Record record);
    void onCreate(Prescription prescription);
    void onCreate(User user);
}
