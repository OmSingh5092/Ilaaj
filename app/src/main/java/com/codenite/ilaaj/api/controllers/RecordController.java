package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.dataModels.Record;

public class RecordController {
    public interface recordDatabaseHandler{
        void onSuccess(Record record);
        void onFailure(Exception e);
    }
    public static void create(Record record, recordDatabaseHandler handler){

    }

    public static void delete(Record record, recordDatabaseHandler handler){

    }

    public static void update (Record record, recordDatabaseHandler handler){

    }
}
