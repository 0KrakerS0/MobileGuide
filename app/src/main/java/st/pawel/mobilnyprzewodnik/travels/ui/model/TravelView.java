package st.pawel.mobilnyprzewodnik.travels.ui.model;


import android.support.annotation.StringRes;

public interface TravelView {

    @StringRes

    String name();

    String cityName();

    float rate();

    int objectNumber();
}
