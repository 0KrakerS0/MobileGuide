package st.pawel.mobilnyprzewodnik.object.model;

import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;

@AllArgsConstructor(suppressConstructorProperties = true)
public class ObjectModel implements ObjectView {

    String objectImageUrl;

    String objectName;

    String objectType;

    String objectCityName;

    float objectRate;

    @Override
    public String objectImageUrl() {
        return objectImageUrl;
    }

    @Override
    public String objectName() {
        return objectName;
    }

    @Override
    public String objectType() {
        return objectType;
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
