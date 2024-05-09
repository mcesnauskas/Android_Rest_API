package lt.mindaugas.androidrestapi.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import lombok.Getter;
import lt.mindaugas.androidrestapi.entity.UsersResponse;
import lt.mindaugas.androidrestapi.repository.RemoteRepository;

public class MainViewModel extends ViewModel {
    @Getter
    private final LiveData<UsersResponse> usersResponseLiveData;
    private final RemoteRepository remoteRepository;

    public MainViewModel() {
        remoteRepository = new RemoteRepository();
        usersResponseLiveData = remoteRepository.getUsersLiveData();
    }

    public void requestUsersResponse() {
        remoteRepository.fetchAllUsers();
    }

}
