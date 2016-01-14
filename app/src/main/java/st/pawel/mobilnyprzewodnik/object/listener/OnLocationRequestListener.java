package st.pawel.mobilnyprzewodnik.object.listener;

import com.google.android.gms.maps.model.LatLng;

public interface OnLocationRequestListener {

    void onLocationRequestSuccess(LatLng latLng);
}
