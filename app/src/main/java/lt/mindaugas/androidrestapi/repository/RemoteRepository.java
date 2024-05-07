package lt.mindaugas.androidrestapi.repository;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.entity.UsersResponse;
import lt.mindaugas.androidrestapi.network.UserDataService;
import lt.mindaugas.androidrestapi.network.UserServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private UserDataService service;

    public RemoteRepository() {
        this.service = UserServiceClient.getInstance().create(UserDataService.class);
    }

    public void fetchAllUsers() {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("page", "1");
        requestParam.put("per_page", "12");

        service.getAllUsers(requestParam).enqueue(
                new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        Log.i("tst_rest_api", "onResponse: " + response.body());
                        Log.i("tst_rest_api", "onResponse: " + response.body().getUsers());
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        Log.i("tst_rest_api", "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                }
        );

    }

    public void fetchUserId(long userId){
        service.getUser(userId).enqueue(
                new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        Log.i("tst_rest_api", "onResponse: " + response.body());
                        Log.i("tst_rest_api", "onResponse: " + response.body().getUser());
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.i("tst_rest_api", "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                }
        );
    }


}
