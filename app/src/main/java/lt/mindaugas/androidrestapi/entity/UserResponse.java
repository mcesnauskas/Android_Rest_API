package lt.mindaugas.androidrestapi.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserResponse {
    @SerializedName("data")
    private User user;
    @SerializedName("support")
    private Support support;
}
