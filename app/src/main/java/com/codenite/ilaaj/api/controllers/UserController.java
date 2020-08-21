package com.codenite.ilaaj.api.controllers;

import com.codenite.ilaaj.api.dataModels.User;

public class UserController {

    public interface userDatabaseHandler{
        void onSuccess(User user);
        void onFailure(Exception e);
    }

    public interface tokenHandler{
        void onSuccess(String token, boolean newUser);
        void onFailure(Exception e);
    }

    public static void create(String token,tokenHandler handler){

    }

    public static void delete(User user, userDatabaseHandler handler){

    }

    public static void update(User user, userDatabaseHandler handler){

    }
}
