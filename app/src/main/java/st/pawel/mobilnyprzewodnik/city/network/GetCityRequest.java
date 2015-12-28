package st.pawel.mobilnyprzewodnik.city.network;


import retrofit.Call;
import st.pawel.mobilnyprzewodnik.city.model.CityResults;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;

public class GetCityRequest extends NetworkRequest<CityResults, CityClient>{

    public static GetCityRequest instance(){
        return new GetCityRequest();
    }

    GetCityRequest() {
        super(CityClient.class);
    }

    @Override
    public Call<CityResults> request() {
        return client().getClassesCity();
    }
}
