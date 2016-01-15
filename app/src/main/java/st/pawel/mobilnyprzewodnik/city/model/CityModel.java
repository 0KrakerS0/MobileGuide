package st.pawel.mobilnyprzewodnik.city.model;

import com.google.gson.annotations.SerializedName;

import lombok.Setter;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

@Parcel
public class CityModel implements CityView {

    private interface Metadata {
        String CITY_NAME = "cityName";
        String CITY_IMAGE = "cityImage";
    }

    @Setter
    @SerializedName(Metadata.CITY_NAME)
    String cityName;

    @Setter
    @SerializedName(Metadata.CITY_IMAGE)
    String cityUrl;

    @Override
    public String cityImageUrl() {
        return cityUrl;
    }

    @Override
    public String cityName() {
        return cityName;
    }
}
