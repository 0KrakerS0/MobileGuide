package st.pawel.mobilnyprzewodnik.travels.network;

import retrofit.Call;
import retrofit.http.GET;
import st.pawel.mobilnyprzewodnik.common.network.RestApi;
import st.pawel.mobilnyprzewodnik.travels.model.TravelResult;

public interface TravelClient {

    @GET(RestApi.Endpoint.CLASSES_TRAVEL)
    Call<TravelResult> getClassesTravel();

}
