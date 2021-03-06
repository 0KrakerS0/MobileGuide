package st.pawel.mobilnyprzewodnik.city.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.LinkedList;
import java.util.List;
import org.parceler.Parcels;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.delegate.CityFragmentDelegate;
import st.pawel.mobilnyprzewodnik.city.listener.OnCityRequestListener;
import st.pawel.mobilnyprzewodnik.city.model.CityResults;
import st.pawel.mobilnyprzewodnik.city.ui.adapter.CityAdapter;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;


public class CityFragment extends DelegateBaseFragment<CityFragmentDelegate> implements OnCityRequestListener {

    private static final String CITY_LIST = "CITY_LIST";

    @Bind(R.id.city_list)
    RecyclerView cityList;

    @Bind(R.id.city_list_refresh)
    SwipeRefreshLayout cityListRefresh;

    CityAdapter adapter;

    CityResults cityResults;

    public static CityFragment newInstance() {
        return new CityFragment();
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
        cityListRefresh.setOnRefreshListener(delegate::requestForCityList);
    }

    private void prepareCityList() {
        cityList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CityAdapter();
        adapter.setOnCityItemClickListener(delegate::onCityClick);
        cityList.setAdapter(adapter);
        if (cityResults == null) {
            cityListRefresh.post(() -> cityListRefresh.setRefreshing(true));
            delegate.requestForCityList();
            return;
        }
        onCityRequestSuccess(cityResults);
    }

    @Override
    public void onCityRequestStart() {
        cityListRefresh.setRefreshing(true);
    }

    @Override
    public void onCityRequestSuccess(CityResults cityResults) {
        this.cityResults = cityResults;
        final List<CityView> result = new LinkedList<>();
        for (CityView city : cityResults) {
            result.add(city);
        }
        adapter.setNewListItems(result);
        adapter.notifyDataSetChanged();
        cityListRefresh.setRefreshing(false);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(CITY_LIST, Parcels.wrap(cityResults));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@Nullable Bundle saveInstanceState) {
        if (saveInstanceState == null) {
            return;
        }
        cityResults = Parcels.unwrap(saveInstanceState.getParcelable(CITY_LIST));
    }

    @Override
    public void onCityRequestFailure() {
        cityListRefresh.setRefreshing(false);
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof CityFragmentDelegate;
    }

    @Override
    protected String delegateClassName() {
        return CityFragmentDelegate.class.getSimpleName();
    }

    @OnClick(R.id.city_list_add_city_button)
    void onAddCityButtonClick() {
        delegate.onAddCityButtonClick();
    }
}
