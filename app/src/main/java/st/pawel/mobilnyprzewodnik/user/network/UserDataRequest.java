package st.pawel.mobilnyprzewodnik.user.network;

import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;

public class UserDataRequest extends NetworkRequest<UserResult, UserClient> {

    private static final String WHERE_JSON_MASK = "{\"objectId\":\"%s\"}";

    String objectId;

    public static UserDataRequest instance(String objectId) {
        return new UserDataRequest(objectId);
    }

    public UserDataRequest(String objectId) {
        super(UserClient.class);
        this.objectId = objectId;
    }

    @Override
    public Call<UserResult> request() {
        return client().userData(String.format(WHERE_JSON_MASK, objectId));
    }
}
