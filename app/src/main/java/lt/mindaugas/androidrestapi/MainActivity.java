package lt.mindaugas.androidrestapi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.fetchAllUsers();
        remoteRepository.fetchUserId(1);
    }
}