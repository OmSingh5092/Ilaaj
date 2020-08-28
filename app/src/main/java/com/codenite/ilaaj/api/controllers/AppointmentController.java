package com.codenite.ilaaj.api.controllers;

import android.content.Context;

import com.codenite.ilaaj.api.dataModels.Appointment;
import com.codenite.ilaaj.api.responseModel.appointment.AddAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.appointment.GetAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.appointment.GetByUserResponse;
import com.codenite.ilaaj.api.retrofit.RetrofitClient;
import com.codenite.ilaaj.utils.SharedPrefs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentController {
    SharedPrefs prefs;
    Context context;

    public AppointmentController(Context context) {
        this.context = context;
        prefs = new SharedPrefs(context);
    }

    public interface getAppointmentHandler{
        void onSuccess(Appointment appointment);
        void onFailure(Throwable t);
    }
    public interface addAppointmentHandler{
        void onSuccess(Appointment appointment);
        void onFailure(Throwable t);
    }

    public interface getByUserHandler{
        void onSuccess(List<Appointment> appointments);
        void onFailure(Throwable t);
    }
    public void getAppointment(int id, getAppointmentHandler handler){
        Map<String,String> body = new HashMap<>();
        body.put("id",String.valueOf(id));

        Call<GetAppointmentResponse>call = RetrofitClient.getClient().getAppointment(prefs.getToken(),body);
        call.enqueue(new Callback<GetAppointmentResponse>() {
            @Override
            public void onResponse(Call<GetAppointmentResponse> call, Response<GetAppointmentResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getAppointment());
                }
            }

            @Override
            public void onFailure(Call<GetAppointmentResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void addAppointment(Appointment appointment, addAppointmentHandler handler){
        Map<String,String> map = new HashMap<>();
        map.put("user_id",String.valueOf(appointment.getUserId()));
        map.put("doctor_id",String.valueOf(appointment.getDoctorId()));
        map.put("dateTime",appointment.getDateTime());

        Call<AddAppointmentResponse> call = RetrofitClient.getClient().addAppointment(prefs.getToken(),map);
        call.enqueue(new Callback<AddAppointmentResponse>() {
            @Override
            public void onResponse(Call<AddAppointmentResponse> call, Response<AddAppointmentResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getAppointment());
                }
            }

            @Override
            public void onFailure(Call<AddAppointmentResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }

    public void getByUser(int userId, getByUserHandler handler){
        Map<String,String> body = new HashMap<>();
        body.put("user_id",String.valueOf(userId));

        Call<GetByUserResponse> call = RetrofitClient.getClient().getAppointmentByUser(prefs.getToken(),body);
        call.enqueue(new Callback<GetByUserResponse>() {
            @Override
            public void onResponse(Call<GetByUserResponse> call, Response<GetByUserResponse> response) {
                if(response.isSuccessful()){
                    handler.onSuccess(response.body().getAppointments());
                }
            }

            @Override
            public void onFailure(Call<GetByUserResponse> call, Throwable t) {
                handler.onFailure(t);
            }
        });
    }


}
