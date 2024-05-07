package lt.mindaugas.androidrestapi.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UsersResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("per_page")
    private int perPge;
    @SerializedName("total")
    private int total;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("data")
    private List<User> users;
    @SerializedName("support")
    private Support support;
}
