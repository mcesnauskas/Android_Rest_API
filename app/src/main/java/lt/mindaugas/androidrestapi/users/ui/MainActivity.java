package lt.mindaugas.androidrestapi.users.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    private boolean requiredData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        requiredData = true;

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        setUpLiveDataObserver();
        setUpRecyclerView();
        scrollOnRecyclerView();
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

    private void scrollOnRecyclerView() {
        LinearLayoutManager linearLayoutManager =
                (LinearLayoutManager) binding.usersRecycleView.getLayoutManager();

        RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int totalItemCount = linearLayoutManager.getItemCount();
                int visibleItemCount = linearLayoutManager.getChildCount();
                int fistVisibleItem = linearLayoutManager.findFirstVisibleItemPosition();
                if (fistVisibleItem + visibleItemCount >= totalItemCount - 2) {
//                    Snackbar.make(recyclerView, "scroled to " + totalItemCount, Snackbar.LENGTH_LONG
//                    ).show();
                    requiredData = true;
                    mainViewModel.requestUsersResponse();
                }
            }
        };

        if (binding.usersRecycleView != null){
            binding.usersRecycleView.removeOnScrollListener(scrollListener);
        }
        binding.usersRecycleView.addOnScrollListener(scrollListener);
    }

    private void setUpLiveDataObserver() {
        mainViewModel.getUsersResponseLiveData().observe(this, usersResponse -> {
            if (usersResponse != null) {
                if (recyclerAdapter != null) {
                    if (recyclerAdapter.getItemCount() == 0 || requiredData) {
                        recyclerAdapter.addList(usersResponse.getUsers());
                        requiredData = false;
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