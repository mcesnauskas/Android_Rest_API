package lt.mindaugas.androidrestapi.users.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.databinding.ActivityMainBinding;
import lt.mindaugas.androidrestapi.entity.User;
import lt.mindaugas.androidrestapi.users.MainViewModel;

public class MainActivity extends AppCompatActivity {
    private MainViewModel mainViewModel = null;
    private ActivityMainBinding binding;
    private RecyclerAdapter recyclerAdapter;
    private final List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        binding.usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(this, users);
        binding.usersRecycleView.setAdapter(recyclerAdapter);
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                users.addAll(usersResponse.getUsers());
                recyclerAdapter.addList(users);
            }
        });
    }

}