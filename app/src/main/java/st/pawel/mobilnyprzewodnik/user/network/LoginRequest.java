package st.pawel.mobilnyprzewodnik.user.network;

import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;

public class LoginRequest extends NetworkRequest<UserResult, UserClient> {

    private static final String WHERE_JSON_MASK = "{\"login\":\"%1$s\", \"password\":\"%2$s\"}";
    String login;

    String password;

    public LoginRequest(String login, String password) {
        super(UserClient.class);
        this.login = login;
        this.password = password;
    }

    @Override
    public Call<UserResult> request() {
        return client().login(String.format(WHERE_JSON_MASK, login, password));
    }
}
