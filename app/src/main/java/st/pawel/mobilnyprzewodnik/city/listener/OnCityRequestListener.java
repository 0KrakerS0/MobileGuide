package st.pawel.mobilnyprzewodnik.city.listener;

import st.pawel.mobilnyprzewodnik.city.model.CityResults;

public interface OnCityRequestListener {

    void onCityRequestStart();

    void onCityRequestSuccess(CityResults cityViews);

    void onCityRequestFailure();
}
