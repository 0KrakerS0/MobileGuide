package st.pawel.mobilnyprzewodnik.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.View;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onRestoreInstanceState(savedInstanceState);
    }

    protected void onRestoreInstanceState(@Nullable Bundle saveInstanceState){

    }
    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        super.onDestroyView();
    }
}
