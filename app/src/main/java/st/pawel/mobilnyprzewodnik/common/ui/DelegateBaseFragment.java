package st.pawel.mobilnyprzewodnik.common.ui;


import android.content.Context;

public abstract class DelegateBaseFragment<DELEGATE> extends BaseFragment {

    DELEGATE delegate;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (instanceOfDelegate(context)) {
            //noinspection unchecked
            delegate = (DELEGATE) context;
            return;
        }
        throw new IllegalArgumentException("Context musi implementowac " + delegateClassName());
    }

    @Override
    public void onDetach() {
        delegate = null;
        super.onDetach();
    }

    protected abstract boolean instanceOfDelegate(Context context);

    protected abstract String delegateClassName();
}
