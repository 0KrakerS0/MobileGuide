package st.pawel.mobilnyprzewodnik.main.model;

import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

@AllArgsConstructor
public enum MainMenu implements MenuItemView {
    MAP(R.drawable.ic_menu_maps_map, R.string.main_menu_map),
    CITY_LIST(R.drawable.ic_menu_city_list, R.string.main_menu_ciy_list),
    TRAVEL_LIST(R.drawable.ic_menu_travel_list, R.string.main_menu_travel_list),
    USER_DATA(R.drawable.ic_menu_user_data, R.string.main_menu_user_data),
    LOGOUT(R.drawable.ic_menu_logout, R.string.main_menu_logout),
    ;

    private final int iconRes;

    private final int labelRes;

    @Override
    public int iconRes() {
        return iconRes;
    }

    @Override
    public int labelRes() {
        return labelRes;
    }
}
