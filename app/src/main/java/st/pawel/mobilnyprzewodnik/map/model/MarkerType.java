package st.pawel.mobilnyprzewodnik.map.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.R;

@AllArgsConstructor(suppressConstructorProperties = true)
public enum MarkerType implements MarkerView {

    //TODO Dodac wlasciwe resource dla ikonek
    @SerializedName(Metadata.ANTIQUE)
    ANTIQUE(R.drawable.ic_antique, R.string.marker_type_antique),
    @SerializedName(Metadata.MUSEUM)
    MUSEUM(R.drawable.ic_museum, R.string.marker_type_museum),
    @SerializedName(Metadata.MONUMENT)
    MONUMENT(R.drawable.ic_monument, R.string.marker_type_monument),
    @SerializedName(Metadata.GALLERY)
    GALLERY(R.drawable.ic_gallery, R.string.marker_type_gallery),
    @SerializedName(Metadata.RESTAURANT)
    RESTAURANT(R.drawable.ic_restauant, R.string.marker_type_restaurant),
    @SerializedName(Metadata.TOURIST_ATTRACTION)
    TOURIST_ATTRACTION(R.drawable.ic_turistic_attraction, R.string.marker_type_tourist_attraction),
    @SerializedName(Metadata.HOTEL)
    HOTEL(R.drawable.ic_hotel, R.string.marker_type_museum),
    @SerializedName(Metadata.CASH_MACHINE)
    CASH_MACHINE(R.drawable.ic_cash_machine, R.string.marker_type_cash_machine),
    @SerializedName(Metadata.FAST_FOOD)
    FAST_FOOD(R.drawable.ic_fast_food, R.string.marker_type_fast_food),
    @SerializedName(Metadata.TOURIST_INFORMATION)
    TOURIST_INFORMATION(R.drawable.ic_tourist_information, R.string.marker_type_tourist_information),
    UNKNOWN(R.drawable.ic_turistic_attraction, R.string.marker_type_unknown) {
        @Override
        public boolean isStandard() {
            return false;
        }
    },;

    @Override
    public LatLng getLatLng() {
        throw new IllegalArgumentException("Nie używaj tej metody dla " + getClass().getSimpleName());
    }

    @Override
    public int typeRes() {
        return typeNameRes;
    }

    @Override
    public int markerIcon() {
        return markerDrawable;
    }

    public boolean isStandard() {
        return true;
    }

    interface Metadata {

        String ANTIQUE = "ANTIQUE";
        String MUSEUM = "MUSEUM";
        String MONUMENT = "MONUMENT";
        String GALLERY = "GALLERY";
        String RESTAURANT = "RESTAURANT";
        String TOURIST_ATTRACTION = "TOURIST_ATTRACTION";
        String HOTEL = "HOTEL";
        String CASH_MACHINE = "CASH_MACHINE";
        String FAST_FOOD = "FAST_FOOD";
        String TOURIST_INFORMATION = "TOURIST_INFORMATION";
    }

    @DrawableRes
    int markerDrawable;

    @StringRes
    int typeNameRes;


}
