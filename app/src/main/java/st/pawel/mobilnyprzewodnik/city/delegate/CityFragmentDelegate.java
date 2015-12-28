package st.pawel.mobilnyprzewodnik.city.delegate;


import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;

public interface CityFragmentDelegate<CITY extends CityView>{

    void onCityClick(CITY city);

    void requestForCityList();
}
