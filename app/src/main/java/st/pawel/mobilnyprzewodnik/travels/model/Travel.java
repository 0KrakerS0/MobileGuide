package st.pawel.mobilnyprzewodnik.travels.model;


import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

@AllArgsConstructor(suppressConstructorProperties=true)
public class Travel implements TravelView {

    String travelImageUrl;

    String name;

    String cityName;

    float rate;

    int objectNumber;

    @Override
    public String name() {
        return name;
    }

    @Override
    public String cityName() {
        return cityName;
    }

    @Override
    public float rate() {
        return rate;
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
