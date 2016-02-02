package st.pawel.mobilnyprzewodnik.object.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import lombok.Setter;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.map.model.MarkerType;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;


@Parcel
public class ObjectModel implements ObjectView {

    private interface Metadata {

        String OBJECT_IMAGE = "objectImage";

        String OBJECT_NAME = "objectName";

        String OBJECT_TYPE = "objectType";

        String OBJECT_CITY_NAME = "objectCityName";

        String OBJECT_RATE = "objectRate2";

        String LATITUDE = "latitude";

        String LONGITUDE = "longitude";

        String CITY = "city";
    }

    @Setter
    @SerializedName(Metadata.OBJECT_IMAGE)
    String objectImageUrl;

    @Setter
    @SerializedName(Metadata.OBJECT_NAME)
    String objectName;

    @Setter
    @SerializedName(Metadata.OBJECT_TYPE)
    MarkerType objectType;

    @Setter
    @SerializedName(Metadata.OBJECT_CITY_NAME)
    String objectCityName;

    @Setter
    @SerializedName(Metadata.OBJECT_RATE)
    float objectRate;

    @Setter
    @SerializedName(Metadata.LATITUDE)
    double latitude;

    @Setter
    @SerializedName(Metadata.LONGITUDE)
    double longitude;

    @Setter
    @SerializedName(Metadata.CITY)
    CityModel city;

    @Override
    public String objectImageUrl() {
        return objectImageUrl;
    }

    @Override
    public String objectName() {
        return objectName;
    }


    @Override
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }


    @Override
    public int typeRes() {
        return objectType == null ? MarkerType.UNKNOWN.typeRes() : objectType.typeRes();
    }

    @Override
    public int markerIcon() {
        return objectType == null ? MarkerType.UNKNOWN.markerIcon() : objectType.markerIcon();
    }

    @Override
    public String objectCityName() {
        return objectCityName;
    }

    @Override
    public float objectRate() {
        return objectRate;
    }
}