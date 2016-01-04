package st.pawel.mobilnyprzewodnik.object.network;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import st.pawel.mobilnyprzewodnik.common.network.RestApi;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;

public interface ObjectClient {

    @GET(RestApi.Endpoint.CLASSES_OBJECT)
    Call<ObjectResult> getClassesObject();

    @POST(RestApi.Endpoint.CLASSES_OBJECT)
    Call<Void> postClassesObject(@Body ObjectModel objectModel);
}
