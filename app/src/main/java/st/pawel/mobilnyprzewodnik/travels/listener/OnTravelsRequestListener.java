package st.pawel.mobilnyprzewodnik.travels.listener;

import java.util.List;

import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public interface OnTravelsRequestListener {

    void onTravelsRequestSuccess(List<TravelView> travelViews);

    void onTravelsRequestFailure();
}
