package st.pawel.mobilnyprzewodnik.travels.model;


import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

@Parcel
@AllArgsConstructor(suppressConstructorProperties = true)
public class TravelModel implements TravelView {

    private interface Metadata {

        String IMAGE_URL = "imageUrl";
        String NAME = "name";
        String CITY_NAME = "cityName";
        String RATE = "rate";
        String OBJECTS_NUMBER = "objectsNumber";
    }

    @SerializedName(Metadata.IMAGE_URL)
    String travelImageUrl;

    @SerializedName(Metadata.NAME)
    String travelName;

    @SerializedName(Metadata.CITY_NAME)
    String cityName;

    @SerializedName(Metadata.RATE)
    float travelRate;

    @SerializedName(Metadata.OBJECTS_NUMBER)
    int objectNumber;

    public TravelModel() {

    }

    @Override
    public String travelName() {
        return travelName;
    }

    @Override
    public String cityName() {
        return cityName;
    }

    @Override
    public float travelRate() {
        return travelRate;
    }

    @Override
    public int objectNumber() {
        return objectNumber;
    }

    @Override
    public String travelImageUrl() {
        return travelImageUrl;
    }
}
