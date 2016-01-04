package st.pawel.mobilnyprzewodnik.object.ui;

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
import butterknife.OnClick;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectfFragmentDelegate;
import st.pawel.mobilnyprzewodnik.object.listener.OnObjectRequestListener;
import st.pawel.mobilnyprzewodnik.object.ui.adapter.ObjectAdapter;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;

public class ObjectFragment extends DelegateBaseFragment<ObjectfFragmentDelegate> implements OnObjectRequestListener{

    @Bind(R.id.main_object_list)
    RecyclerView mainObjectList;

    @Bind(R.id.object_list_refresh)
    SwipeRefreshLayout objectListRefresh;


    ObjectAdapter objectAdapter;

    public static ObjectFragment newInstance() {
        ObjectFragment objectFragment = new ObjectFragment();
        return objectFragment;
    }
    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof ObjectfFragmentDelegate;
    }

    @Override
    protected String delegateClassName() {
        return ObjectfFragmentDelegate.class.getSimpleName();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_object, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prepareObjectList();
        objectListRefresh.setOnRefreshListener(delegate::requestForObjectList);
        objectListRefresh.post(() -> objectListRefresh.setRefreshing(true));
    }

    private void prepareObjectList() {
        mainObjectList.setLayoutManager(new LinearLayoutManager(getContext()));
        objectAdapter = new ObjectAdapter();
        objectAdapter.setOnObjectItemClickListener(delegate::onObjectClick);
        mainObjectList.setAdapter(objectAdapter);
        delegate.requestForObjectList();
    }

    @Override
    public void onObjectRequestStart() {
        objectListRefresh.setRefreshing(true);
    }

    @Override
    public void onObjectRequestSuccess(List<ObjectView> objectViews) {

        objectAdapter.setNewListItems(objectViews);
        objectAdapter.notifyDataSetChanged();
        objectListRefresh.setRefreshing(false);
    }

    @Override
    public void onObjectRequestFailure() {

        objectListRefresh.setRefreshing(false);

    }
    @OnClick(R.id.object_list_add_object_button)
    void onAddObjectButtonClick() {
        delegate.onAddObjectButtonClick();
    }
}



