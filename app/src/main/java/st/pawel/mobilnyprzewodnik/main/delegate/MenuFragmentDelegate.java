package st.pawel.mobilnyprzewodnik.main.delegate;

import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;
import st.pawel.mobilnyprzewodnik.main.ui.model.UserView;

public interface MenuFragmentDelegate<MENU_ITEM extends MenuItemView, USER extends  UserView> {

    void onMenuItemClick(MENU_ITEM menuItem, USER userView);

    void requestForUserData();
}
