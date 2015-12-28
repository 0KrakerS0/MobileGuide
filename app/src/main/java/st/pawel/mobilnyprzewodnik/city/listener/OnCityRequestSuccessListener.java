package st.pawel.mobilnyprzewodnik.city.listener;

import java.util.List;

import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

public interface OnCityRequestSuccessListener {

    void onCityRequestSuccess(List<CityView> cityViews);
}
