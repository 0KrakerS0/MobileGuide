package st.pawel.mobilnyprzewodnik.object.ui.model;

import st.pawel.mobilnyprzewodnik.map.model.MarkerView;

public interface ObjectView extends MarkerView {

    String objectImageUrl();

    String objectName();

    String objectCityName();

    float objectRate();
}
