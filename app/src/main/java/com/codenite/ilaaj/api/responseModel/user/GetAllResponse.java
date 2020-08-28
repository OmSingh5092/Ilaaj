package com.codenite.ilaaj.api.responseModel.user;

import com.codenite.ilaaj.api.dataModels.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetAllResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("users")
    @Expose
    List<User> users;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
