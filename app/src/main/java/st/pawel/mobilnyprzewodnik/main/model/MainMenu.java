package st.pawel.mobilnyprzewodnik.main.model;

import lombok.AllArgsConstructor;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

@AllArgsConstructor
public enum MainMenu implements MenuItemView {
    MAP(R.mipmap.ic_launcher, R.string.main_menu_map),
    CITY_LIST(R.mipmap.ic_launcher, R.string.main_menu_ciy_list),
    TRAVEL_LIST(R.mipmap.ic_launcher, R.string.main_menu_travel_list),
    USER_DATA(R.mipmap.ic_launcher, R.string.main_menu_user_data),
    LOGOUT(R.mipmap.ic_launcher, R.string.main_menu_logout),
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
