package st.pawel.mobilnyprzewodnik.main.delegate;

import st.pawel.mobilnyprzewodnik.main.ui.model.MenuItemView;

public interface MenuFragmentDelegate<MENU_ITEM extends MenuItemView> {

    void onMenuItemClick(MENU_ITEM menuItem);
}
