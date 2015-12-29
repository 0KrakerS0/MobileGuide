package st.pawel.mobilnyprzewodnik.travels.model;


import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

@AllArgsConstructor(suppressConstructorProperties=true)
public class TravelModel implements TravelView {

    String travelImageUrl;

    String travelName;

    String cityName;

    float travelRate;

    int objectNumber;

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
