package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.models.User;

public class UserController {

    public interface userDatabaseHandler{
        void onSuccess(User user);
        void onFailure(Exception e);
    }

    public static void create(String token,userDatabaseHandler handler){

    }

    public static void delete(User user, userDatabaseHandler handler){

    }

    public static void update(User user, userDatabaseHandler handler){

    }
}
