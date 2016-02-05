package st.pawel.mobilnyprzewodnik.user.network;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.http.Query;
import st.pawel.mobilnyprzewodnik.common.network.RestApi;
import st.pawel.mobilnyprzewodnik.user.model.User;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;

public interface UserClient {

    @GET(RestApi.Endpoint.CLASSES_USERS)
    Call<UserResult> login(@Query(RestApi.Query.WHERE) String where);

    @GET(RestApi.Endpoint.CLASSES_USERS)
    Call<UserResult> userData(@Query(RestApi.Query.WHERE) String where);


    @PUT(RestApi.Endpoint.CLASSES_USERS_USER_ID)
    Call<UserResult> updateUser(@Path(RestApi.Param.USER_ID)String userId, @Body() User where);
}
