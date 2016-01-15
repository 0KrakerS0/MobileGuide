package st.pawel.mobilnyprzewodnik.object.listener;

import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;

public interface OnObjectRequestListener {

    void onObjectRequestStart();

    void onObjectRequestSuccess(ObjectResult objectResult);

    void onObjectRequestFailure();
}
