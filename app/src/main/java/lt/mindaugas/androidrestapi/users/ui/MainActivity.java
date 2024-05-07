package lt.mindaugas.androidrestapi.users.ui;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.databinding.ActivityMainBinding;
import lt.mindaugas.androidrestapi.entity.User;
import lt.mindaugas.androidrestapi.users.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel = null;
    private ActivityMainBinding binding;
    private ArrayAdapter<User> arrayAdapter;
    private final List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();

        setUpListView();
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                users.addAll(usersResponse.getUsers());
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpListView() {
        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                users
        );
        binding.usersListView.setAdapter(arrayAdapter);
    }
}