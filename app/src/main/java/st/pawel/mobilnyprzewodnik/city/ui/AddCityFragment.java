package st.pawel.mobilnyprzewodnik.city.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;

//DelegateBaseFragment - delegata musi implementowac City activity
public class AddCityFragment extends BaseFragment {

    @Bind(R.id.add_city_name)
    EditText cityName;

    public static AddCityFragment newInstance() {
        AddCityFragment fragment = new AddCityFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_add_city, container, false);
        ButterKnife.bind(this, result);
        return result;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_accept, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_accept){
            //stworz city model
            //delegte.addCity(cityModel)
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
