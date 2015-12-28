package st.pawel.mobilnyprzewodnik.city.network;


import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.city.model.CityResults;
import st.pawel.mobilnyprzewodnik.common.network.RestApi;

public interface CityClient {

    @GET(RestApi.Endpoint.CLASSES_CITY)
    Call<CityResults> getClassesCity();

    @POST(RestApi.Endpoint.CLASSES_CITY)
    Call<Void> postClassesCity(@Body CityModel cityModel);
}
