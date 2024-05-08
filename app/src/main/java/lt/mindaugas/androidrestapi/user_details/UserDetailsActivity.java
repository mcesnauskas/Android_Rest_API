package lt.mindaugas.androidrestapi.user_details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import lt.mindaugas.androidrestapi.databinding.ActivityUserDetailsBinding;
import lt.mindaugas.androidrestapi.users.ui.MainActivity;

public class UserDetailsActivity extends AppCompatActivity {
    private ActivityUserDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        long userId = getIntent().getLongExtra(MainActivity.MAIN_ACTIVITY_USER_ID, -1);
        binding.infoTextView.setText(String.valueOf(userId));
    }
}