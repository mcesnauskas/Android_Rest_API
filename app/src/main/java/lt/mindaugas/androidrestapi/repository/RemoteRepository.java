package lt.mindaugas.androidrestapi.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.entity.UsersResponse;
import lt.mindaugas.androidrestapi.network.UserDataService;
import lt.mindaugas.androidrestapi.network.UserServiceClient;
import retrofit2.Response;

public class RemoteRepository {
    private UserDataService service;

    public RemoteRepository() {
        this.service = UserServiceClient.getInstance().create(UserDataService.class);
    }

    public Future<UsersResponse> fetchAllUsers() {
        Map<String, String> requestParam = new HashMap<>();
        requestParam.put("page", "1");
        requestParam.put("per_page", "12");

        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            Response<UsersResponse> response = service.getAllUsers(requestParam).execute();
            return response.isSuccessful() ? response.body() : null;
        });
    }

    public Future<UserResponse> fetchUser(long userId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            Response<UserResponse> response = service.getUser(userId).execute();
            return response.isSuccessful() ? response.body() : null;
        });
    }
}
