package st.pawel.mobilnyprzewodnik.object.delegate;

import com.google.android.gms.maps.model.LatLng;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;

public interface ObjectAddDelegate {
    void addObject(ObjectModel objectModel);

    LatLng requestForLastKnownPosition();
}
