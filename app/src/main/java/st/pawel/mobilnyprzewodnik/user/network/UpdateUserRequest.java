package st.pawel.mobilnyprzewodnik.user.network;

import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.user.model.User;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;

public class UpdateUserRequest extends NetworkRequest<UserResult, UserClient> {

    String userId;

    User user;

    public static UpdateUserRequest instance(String userId, User user){
        return new UpdateUserRequest(userId, user);
    }

    public UpdateUserRequest(String userId, User user) {
        super(UserClient.class);
        this.userId = userId;
        this.user = user;
    }

    @Override
    public Call<UserResult> request() {
        return client().updateUser(userId, user);
    }
}
