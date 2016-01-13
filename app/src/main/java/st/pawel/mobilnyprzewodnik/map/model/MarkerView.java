package st.pawel.mobilnyprzewodnik.map.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import com.google.android.gms.maps.model.LatLng;

public interface MarkerView {

    LatLng getLatLng();

    @StringRes
    int typeRes();

    @DrawableRes
    int markerIcon();
}
