package com.codenite.ilaaj.api.controllers;

import android.content.Context;

import com.codenite.ilaaj.api.dataModels.User;
import com.codenite.ilaaj.api.responseModel.appointment.GetAllDoctorsResponse;
import com.codenite.ilaaj.api.responseModel.signin.GoogleSignInResponse;
import com.codenite.ilaaj.api.responseModel.user.AddUserResponse;
import com.codenite.ilaaj.api.responseModel.user.GetAllResponse;
import com.codenite.ilaaj.api.responseModel.user.GetResponse;
import com.codenite.ilaaj.api.responseModel.user.UpdateUserResponse;
import com.codenite.ilaaj.api.retrofit.RetrofitClient;
import com.codenite.ilaaj.utils.SharedPrefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserController {
    Context context;
    SharedPrefs prefs;

    public UserController(Context context) {
        this.context = context;
        prefs = new SharedPrefs(context);
    }

    public interface userGetHandler{
        void onSuccess(User user);
        void onFailure(Throwable e);
    }

    public interface userGetAllHandler{
        void onSuccess(List<User> users);
        void onFailure(Throwable t);
    }

    public interface userUpdateHandler{
        void onSuccess();
        void onFailure(Throwable t);
    }


    public interface tokenHandler{
        void onSuccess(String token, boolean newUser);
        void onFailure(Throwable e);
    }

    public interface addUserHandler{
        void onSuccess(User user);
        void onFailure(Throwable t);
    }

    public void create(String token,boolean isDoctor,tokenHandler handler){
        Map<String,String> body = new HashMap<>();
        body.put("idToken",token);
        body.put("doctor",String.valueOf(isDoctor));

        Call<GoogleSignInResponse> call = RetrofitClient.getClient().googleSignIn(body);
        call.enqueue(new Callback<GoogleSignInResponse>() {
            @Override
            public void onResponse(Call<GoogleSignInResponse> call, Response<GoogleSignInResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getAuthToken(), response.body().isNewUser());
                }
            }

            @Override
            public void onFailure(Call<GoogleSignInResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void getAllUser(userGetAllHandler handler){
        Call<GetAllResponse> call = RetrofitClient.getClient().getAllUser(prefs.getToken());
        call.enqueue(new Callback<GetAllResponse>() {
            @Override
            public void onResponse(Call<GetAllResponse> call, Response<GetAllResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getUsers());
                }
            }

            @Override
            public void onFailure(Call<GetAllResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void getUser(userGetHandler handler){
        Call<GetResponse> call = RetrofitClient.getClient().getUser(prefs.getToken());
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void updateUser(User user,userUpdateHandler handler){
        Map<String,String>body = new HashMap<>();
        body.put("name",user.getName());
        body.put("phone",user.getPhone());
        body.put("email",user.getEmail());
        body.put("age",String.valueOf(user.getAge()));
        body.put("type",user.getType());
        body.put("hash",user.getHash());
        body.put("upi_id",user.getUpiId());
        body.put("price",user.getPrice());
        body.put("rating",user.getRating());
        body.put("new_user",String.valueOf(user.isNewUser()));

        Call<UpdateUserResponse> call = RetrofitClient.getClient().updateUser(prefs.getToken(),body);
        call.enqueue(new Callback<UpdateUserResponse>() {
            @Override
            public void onResponse(Call<UpdateUserResponse> call, Response<UpdateUserResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess();
                }
            }

            @Override
            public void onFailure(Call<UpdateUserResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void addUser(User user,addUserHandler handler){
        Map<String,String>body = new HashMap<>();
        body.put("name",user.getName());
        body.put("phone",user.getPhone());
        body.put("email",user.getEmail());
        body.put("age",String.valueOf(user.getAge()));
        body.put("type",user.getType());
        body.put("hash",user.getHash());
        body.put("new_user",String.valueOf(false));
        body.put("upi_id",user.getUpiId());
        body.put("price",user.getPrice());
        body.put("rating",user.getRating());
        Call<AddUserResponse> call = RetrofitClient.getClient().addUser(prefs.getToken(),body);

        call.enqueue(new Callback<AddUserResponse>() {
            @Override
            public void onResponse(Call<AddUserResponse> call, Response<AddUserResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<AddUserResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void getSpecificUser(int id, userGetHandler handler){
        Call<GetResponse> call = RetrofitClient.getClient().getSpecificUser(prefs.getToken(),id);
        call.enqueue(new Callback<GetResponse>() {
            @Override
            public void onResponse(Call<GetResponse> call, Response<GetResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getUser());
                }
            }

            @Override
            public void onFailure(Call<GetResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void getAllDoctors(userGetAllHandler handler){
        Call<GetAllDoctorsResponse> call= RetrofitClient.getClient().getAllDoctors(prefs.getToken());
        call.enqueue(new Callback<GetAllDoctorsResponse>() {
            @Override
            public void onResponse(Call<GetAllDoctorsResponse> call, Response<GetAllDoctorsResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getDoctors());
                }
            }

            @Override
            public void onFailure(Call<GetAllDoctorsResponse> call, Throwable t) {

            }
        });
    }
}
