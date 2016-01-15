package st.pawel.mobilnyprzewodnik.travels.network;

import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;
import st.pawel.mobilnyprzewodnik.travels.model.TravelResult;


public class GetTravelRequest extends NetworkRequest<TravelResult, TravelClient> {

    public static GetTravelRequest instance() {
        return new GetTravelRequest();
    }

    GetTravelRequest() {
        super(TravelClient.class);
    }

    @Override
    public Call<TravelResult> request() {
        return client().getClassesTravel();
    }
}

