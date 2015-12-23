package st.pawel.mobilnyprzewodnik.city.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import java.util.LinkedList;
import java.util.List;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.city.ui.adapter.CityAdapter;
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;


public class CityFragment extends BaseFragment{
    @Bind(R.id.city_list)
    RecyclerView cityList;

    public static CityFragment newInstance() {
        CityFragment cityFragment = new CityFragment();
        return cityFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_city, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareCityList();
    }

    private void prepareCityList() {
        cityList.setLayoutManager(new LinearLayoutManager(getContext()));
        final CityAdapter adapter = new CityAdapter();
        cityList.setAdapter(adapter);
        List<CityModel> list = createCityList();
        for (CityModel travel : list) {
            adapter.add(travel);
        }
    }


    List<CityModel> createCityList() {
        List<CityModel> list = new LinkedList<>();
        list.add(new CityModel("Lublin"));
        list.add(new CityModel("Warszawa"));
        list.add(new CityModel("Poznań"));
        list.add(new CityModel("Kraków"));
        list.add(new CityModel("Wrocław"));
        list.add(new CityModel("Gdańsk"));
        list.add(new CityModel("Szczecin"));
        return list;
    }

}
