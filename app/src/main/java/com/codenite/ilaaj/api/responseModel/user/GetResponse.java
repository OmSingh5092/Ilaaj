package com.codenite.ilaaj.api.responseModel.user;

import com.codenite.ilaaj.api.dataModels.User;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetResponse {
    @SerializedName("success")
    @Expose
    boolean success;
    @SerializedName("user")
    @Expose
    User user;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
