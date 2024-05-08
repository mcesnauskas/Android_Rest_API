package lt.mindaugas.androidrestapi.users.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import lt.mindaugas.androidrestapi.databinding.ActivityMainBinding;
import lt.mindaugas.androidrestapi.entity.User;
import lt.mindaugas.androidrestapi.user_details.UserDetailsActivity;
import lt.mindaugas.androidrestapi.users.MainViewModel;

public class MainActivity extends AppCompatActivity {
    public static final String MAIN_ACTIVITY_USER_ID = "user_id";
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
        recyclerAdapter = new RecyclerAdapter(
                this, users, this::onItemClick, this::onItemLongClick)
        ;
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

    private void onItemClick(int position) {
        User user = users.get(position);
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, user.getId());
        startActivity(intent);
    }

    private void onItemLongClick(int position) {
        User user = users.get(position);
        Snackbar
                .make(binding.getRoot(), "Long click: " + user.getFirstName(), Snackbar.LENGTH_LONG)
                .show();

    }


}