package st.pawel.mobilnyprzewodnik.object.listener;

import java.util.List;

import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;

public interface OnObjectRequestListener {

    void onObjectRequestStart();

    void onObjectRequestSuccess(List<ObjectView> objectViews);

    void onObjectRequestFailure();
}
