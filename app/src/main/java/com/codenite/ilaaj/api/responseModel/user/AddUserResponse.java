package com.codenite.ilaaj.api.responseModel.user;

import com.codenite.ilaaj.api.dataModels.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddUserResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("message")
    @Expose
    String message;
    @SerializedName("user")
    @Expose
    User user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
