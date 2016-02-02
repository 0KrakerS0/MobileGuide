package st.pawel.mobilnyprzewodnik.user.ui;

import android.content.Context;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.travels.delegate.TravelFragmentDelegate;

public class UserDataFragment extends DelegateBaseFragment<TravelFragmentDelegate> {

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return false;
    }

    @Override
    protected String delegateClassName() {
        return null;
    }
}
