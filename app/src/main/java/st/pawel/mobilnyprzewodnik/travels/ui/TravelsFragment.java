package st.pawel.mobilnyprzewodnik.travels.ui;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.travels.delegate.TravelFragmentDelegate;
import st.pawel.mobilnyprzewodnik.travels.listener.OnTravelsRequestListener;
import st.pawel.mobilnyprzewodnik.travels.model.Travel;
import st.pawel.mobilnyprzewodnik.travels.ui.adapter.TravelAdapter;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public class TravelsFragment extends DelegateBaseFragment<TravelFragmentDelegate> implements OnTravelsRequestListener {

    @Bind(R.id.main_travel_list)
    RecyclerView mainTravelList;
    @Bind(R.id.travel_list_refresh)
    SwipeRefreshLayout travelListRefresh;

    TravelAdapter adapter;

    public static TravelsFragment newInstance() {
        TravelsFragment travelsFragment = new TravelsFragment();
        return travelsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_travel, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareTravelList();
        travelListRefresh.setOnRefreshListener(delegate::requestForTravelList);
        travelListRefresh.post(() -> travelListRefresh.setRefreshing(true));
    }

    private void prepareTravelList() {
        mainTravelList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TravelAdapter();
        adapter.setOnTravelItemClickListener(delegate::onTravelClick);
        mainTravelList.setAdapter(adapter);
        delegate.requestForTravelList();
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof TravelFragmentDelegate;
    }

    @Override
    protected String delegateClassName() {
        return TravelFragmentDelegate.class.getSimpleName();
    }

    @Override
    public void onTravelsRequestSuccess(List<TravelView> travelViews) {
        adapter.clearAndAddall(travelViews);
        adapter.notifyDataSetChanged();
        travelListRefresh.setRefreshing(false);
    }

    @Override
    public void onTravelsRequestFailure() {
        travelListRefresh.setRefreshing(false);
    }
}
