package st.pawel.mobilnyprzewodnik.travels.listener;

import st.pawel.mobilnyprzewodnik.travels.model.TravelResult;

public interface OnTravelsRequestListener {

    void onTravelsRequestSuccess(TravelResult travelViews);

    void onTravelsRequestFailure();
}
