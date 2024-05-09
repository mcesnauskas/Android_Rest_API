package lt.mindaugas.androidrestapi.users.ui;

import android.content.Intent;
import android.os.Bundle;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();
        setUpRecyclerView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainViewModel.requestUsersResponse();
    }

    private void setUpRecyclerView() {
        binding.usersRecycleView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(
                this, this::onItemClick, this::onItemLongClick)
        ;
        binding.usersRecycleView.setAdapter(recyclerAdapter);
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                if (recyclerAdapter != null) {
                    if (recyclerAdapter.getItemCount() == 0) {
                        recyclerAdapter.addList(usersResponse.getUsers());
                    }
                }
            }
        });
    }

    private void onItemClick(int position) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, recyclerAdapter.getUserByPosition(position));
        startActivity(intent);
    }

    private void onItemLongClick(int position) {
        User user = recyclerAdapter.getUserDetails(position);
        Snackbar
                .make(binding.getRoot(), "Long click: " + user.getFirstName(), Snackbar.LENGTH_LONG)
                .show();
    }
}