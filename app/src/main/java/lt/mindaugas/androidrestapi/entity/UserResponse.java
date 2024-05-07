package lt.mindaugas.androidrestapi.entity;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @SerializedName("data")
    private User user;
    @SerializedName("support")
    private Support support;
}
