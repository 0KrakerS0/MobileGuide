package st.pawel.mobilnyprzewodnik.city.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

public class CityModel implements CityView {

    private interface Metadata {
        String CITY_NAME = "cityName";
    }

    @SerializedName(Metadata.CITY_NAME)
    String cityName;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String cityName() {
        return cityName;
    }
}
