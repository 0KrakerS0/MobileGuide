package st.pawel.mobilnyprzewodnik.user.network;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import st.pawel.mobilnyprzewodnik.common.network.RestApi;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;

public interface UserClient {

    @GET(RestApi.Endpoint.CLASSES_USERS)
    Call<UserResult> login(@Query(RestApi.Query.WHERE) String where);
}
