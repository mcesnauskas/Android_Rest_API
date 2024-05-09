package lt.mindaugas.androidrestapi.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.entity.UsersResponse;
import lt.mindaugas.androidrestapi.network.UserDataService;
import lt.mindaugas.androidrestapi.network.UserServiceClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private UserDataService service;
    @Getter
    private final MutableLiveData<UsersResponse> usersLiveData = new MutableLiveData<>();
    @Getter
    private final MutableLiveData<UserResponse> userDetailsLiveData = new MutableLiveData<>();

    public RemoteRepository() {
        this.service = UserServiceClient.getInstance().create(UserDataService.class);
    }

    public MutableLiveData<UsersResponse> fetchAllUsers() {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("page", "1");
        requestParam.put("per_page", "12");

        service.getAllUsers(requestParam).enqueue(
                new Callback<UsersResponse>() {
                    @Override
                    public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                        if (response.isSuccessful()) {
                            usersLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<UsersResponse> call, Throwable t) {
                        Log.i("tst_rest_api", "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                }
        );
        return usersLiveData;
    }

    public MutableLiveData<UserResponse> fetchUser(long userId) {
        service.getUser(userId).enqueue(
                new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            userDetailsLiveData.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Log.i("tst_rest_api", "Failed to retrieve data" + t.getMessage());
                        call.cancel();
                    }
                }
        );

        return userDetailsLiveData;
    }

    public Future<UserResponse> fetchUserConcurrent(long userId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            Response<UserResponse> response = service.getUser(userId).execute();
            return response.isSuccessful() ? response.body() : null;
        });
    }

}
