package lt.mindaugas.androidrestapi.user_details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UserResponse;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class UserDetailsViewModel extends ViewModel {
    @Getter
    private LiveData<UserResponse> userResponseLiveData = new MutableLiveData<>();
    private final RemoteRepository remoteRepository;

    public UserDetailsViewModel() {
        this.remoteRepository = new RemoteRepository();
    }

    public void requestUserResponse(long userId) {
        userResponseLiveData = remoteRepository.fetchUser(userId);
    }

}
