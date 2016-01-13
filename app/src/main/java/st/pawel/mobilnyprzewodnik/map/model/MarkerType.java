package st.pawel.mobilnyprzewodnik.map.model;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import st.pawel.mobilnyprzewodnik.R;

@AllArgsConstructor(suppressConstructorProperties = true)
public enum MarkerType {

    //TODO Dodac wlasciwe resource dla ikonek
    ANTIQUE(R.drawable.ic_menu_maps_map, R.string.marker_type_antique),
    MUSEUM(R.drawable.ic_menu_maps_map, R.string.marker_type_museum),
    MONUMENT(R.drawable.ic_menu_maps_map, R.string.marker_type_monument),
    GALLERY(R.drawable.ic_menu_maps_map, R.string.marker_type_gallery),
    RESTAURANT(R.drawable.ic_menu_maps_map, R.string.marker_type_restaurant),
    TOURIST_ATTRACTION(R.drawable.ic_menu_maps_map, R.string.marker_type_tourist_attraction),
    HOTEL(R.drawable.ic_menu_maps_map, R.string.marker_type_museum),
    CASH_MACHINE(R.drawable.ic_menu_maps_map, R.string.marker_type_cash_machine),
    FAST_FOOD(R.drawable.ic_menu_maps_map, R.string.marker_type_fast_food),
    TOURIST_INFORMATION(R.drawable.ic_menu_maps_map, R.string.marker_type_tourist_information),;

    @Getter
    @DrawableRes
    int markerDrawable;

    @Getter
    @StringRes
    int typeNameRes;
}
