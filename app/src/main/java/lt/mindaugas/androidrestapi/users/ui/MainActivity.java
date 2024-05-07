package lt.mindaugas.androidrestapi.users.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import lt.mindaugas.androidrestapi.R;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;
import lt.mindaugas.androidrestapi.users.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                Log.i("tst_rest_api", "setUpLiveDataObserver: " + usersResponse);
            }
        });
    }
}