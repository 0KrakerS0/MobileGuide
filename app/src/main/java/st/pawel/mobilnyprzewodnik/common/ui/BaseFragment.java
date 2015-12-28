package st.pawel.mobilnyprzewodnik.common.ui;

import android.support.v4.app.Fragment;

import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
