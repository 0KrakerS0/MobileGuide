package st.pawel.mobilnyprzewodnik.city.network;


import retrofit.Call;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;

public class PostCityRequest extends NetworkRequest<Void, CityClient>{

    public static PostCityRequest instance(){
        return new PostCityRequest();
    }

    CityModel cityModel;

    PostCityRequest() {
        super(CityClient.class);
    }

    public PostCityRequest withCityModel(CityModel cityModel){
        this.cityModel = cityModel;
        return this;
    }

    @Override
    public Call<Void> request() {
        if(cityModel == null){
            throw new IllegalStateException("Musisz ustawic city model zanim wywolasz request");
        }
        return client().postClassesCity(cityModel);
    }
}
