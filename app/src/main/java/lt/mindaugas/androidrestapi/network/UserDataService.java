package lt.mindaugas.androidrestapi.network;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface UserDataService {
    @GET("/api/users")
    Call<UsersResponse> getAllUsers(@QueryMap Map<String, String> options);

    @GET("/api/users/{id}")
    Call<UserResponse> getUser(@Path("id") long id);
}
