package lt.mindaugas.androidrestapi.user_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.mindaugas.androidrestapi.databinding.ActivityUserDetailsBinding;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}