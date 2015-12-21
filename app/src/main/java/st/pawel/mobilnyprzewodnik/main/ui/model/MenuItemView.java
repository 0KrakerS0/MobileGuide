package st.pawel.mobilnyprzewodnik.main.ui.model;


import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

public interface MenuItemView {

    @DrawableRes
    int iconRes();
    
    @StringRes
    int labelRes();
}
