package lt.mindaugas.androidrestapi.user_details;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class UserDetailsViewModel extends ViewModel {
    @Getter
    private MutableLiveData<UserResponse> userResponseLiveData;
    private final RemoteRepository remoteRepository;

    public UserDetailsViewModel() {
        this.remoteRepository = new RemoteRepository();
        userResponseLiveData = new MutableLiveData<>();
    }

    public void requestUserResponse(long userId) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                UserResponse userResponse = remoteRepository.fetchUser(userId).get();
                userResponseLiveData.postValue(userResponse);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }
}
