package st.pawel.mobilnyprzewodnik.travels.model;


import lombok.AllArgsConstructor;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

@Parcel
@AllArgsConstructor(suppressConstructorProperties=true)
public class TravelModel implements TravelView {

    String travelImageUrl;

    String travelName;

    String cityName;

    float travelRate;

    int objectNumber;

    public TravelModel(){

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
