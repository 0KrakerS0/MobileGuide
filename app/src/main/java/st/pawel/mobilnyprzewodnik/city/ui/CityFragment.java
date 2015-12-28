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

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.delegate.CityFragmentDelegate;
import st.pawel.mobilnyprzewodnik.city.listener.OnCityRequestListener;
import st.pawel.mobilnyprzewodnik.city.ui.adapter.CityAdapter;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;


public class CityFragment extends DelegateBaseFragment<CityFragmentDelegate> implements OnCityRequestListener {
    @Bind(R.id.city_list)
    RecyclerView cityList;
    @Bind(R.id.city_list_refresh)
    SwipeRefreshLayout cityListRefresh;

    CityAdapter adapter;

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
        cityListRefresh.setOnRefreshListener(delegate::requestForCityList);
        cityListRefresh.post(() -> cityListRefresh.setRefreshing(true));
    }

    private void prepareCityList() {
        cityList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CityAdapter();
        adapter.setOnCityItemClickListener(delegate::onCityClick);
        cityList.setAdapter(adapter);
        delegate.requestForCityList();
    }

    @Override
    public void onCityRequestSuccess(List<CityView> cityViews) {
        adapter.setNewListItems(cityViews);
        adapter.notifyDataSetChanged();
        cityListRefresh.setRefreshing(false);
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
}
