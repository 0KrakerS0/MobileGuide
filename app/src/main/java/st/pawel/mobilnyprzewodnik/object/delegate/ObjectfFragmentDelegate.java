package st.pawel.mobilnyprzewodnik.object.delegate;

import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;


public interface ObjectfFragmentDelegate <OBJECT extends ObjectView> {

    void onObjectClick(OBJECT object);

    void requestForObjectList();
}
