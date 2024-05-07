package lt.mindaugas.androidrestapi.entity;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Support {
    @SerializedName("url")
    private String url;
    @SerializedName("text")
    private String text;
}
