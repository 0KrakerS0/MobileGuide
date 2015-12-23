package st.pawel.mobilnyprzewodnik.city.model;

import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

@AllArgsConstructor(suppressConstructorProperties = true)
public class CityModel implements CityView {

    String cityName;

    @Override
    public String cityName() {
        return cityName;
    }
}
