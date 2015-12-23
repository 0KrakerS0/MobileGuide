package st.pawel.mobilnyprzewodnik.travels.delegate;

import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public interface TravelFragmentDelegate <TRAVEL extends TravelView>{
    void onTravelClick(TRAVEL travel);

}
