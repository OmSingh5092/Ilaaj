package com.codenite.ilaaj.api.retrofit;

import com.codenite.ilaaj.api.responseModel.appointment.AddAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.appointment.DeleteAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.appointment.GetAllAppointmentsResponse;
import com.codenite.ilaaj.api.responseModel.appointment.GetAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.appointment.GetByUserResponse;
import com.codenite.ilaaj.api.responseModel.appointment.UpdateAppointmentResponse;
import com.codenite.ilaaj.api.responseModel.signin.GoogleSignInResponse;
import com.codenite.ilaaj.api.responseModel.user.AddUserResponse;
import com.codenite.ilaaj.api.responseModel.user.DeleteUserResponse;
import com.codenite.ilaaj.api.responseModel.user.GetAllResponse;
import com.codenite.ilaaj.api.responseModel.user.GetResponse;
import com.codenite.ilaaj.api.responseModel.user.UpdateUserResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface APIInterface {
    //Google SignIn
    @POST("google/signin")
    Call<GoogleSignInResponse> googleSignIn(@Body Map<String,String> body);

    //Users
    @GET("users/get")
    Call<GetResponse> getUser(@Header("token") String token);
    @GET("users/get/all")
    Call<GetAllResponse> getAllUser(@Header("token") String token);
    @POST
    Call<AddUserResponse> addUser(@Header("token") String token,@Body Map<String,String> body);
    @DELETE
    Call<DeleteUserResponse> deleteUser(@Header("token")String token);
    @PUT
    Call<UpdateUserResponse> updateUser(@Header("token")String token,@Body Map<String,String>body);

    //Appointments
    @GET("appointments/get")
    Call<GetAppointmentResponse> getAppointment(@Header("token")String token, @Body Map<String,String> body);
    @GET("appointments/get/all")
    Call<GetAllAppointmentsResponse> getAllAppointments(@Header("token") String token);
    @GET("appointments/get/user")
    Call<GetByUserResponse> getAppointmentByUser(@Header("token")String token,@Body Map<String,String> body);
    @DELETE("appointments/delete")
    Call<DeleteAppointmentResponse> deleteAppointment(@Header("token")String token,@Body Map<String,String>body);
    @POST("appointments/add")
    Call<AddAppointmentResponse> addAppointment(@Header("token") String token,@Body Map<String,String> body);
    @PUT("appointments/update")
    Call<UpdateAppointmentResponse> updateAppointment(@Header("token") String token,@Body Map<String,String> body);




}
