package st.pawel.mobilnyprzewodnik.travels.ui;


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
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;
import st.pawel.mobilnyprzewodnik.travels.model.Travel;
import st.pawel.mobilnyprzewodnik.travels.ui.adapter.TravelAdapter;

public class TravelsFragment extends BaseFragment {

    @Bind(R.id.main_travel_list)
    RecyclerView mainTravelList;

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
    }

    private void prepareTravelList() {
        mainTravelList.setLayoutManager(new LinearLayoutManager(getContext()));
        final TravelAdapter adapter = new TravelAdapter();
        mainTravelList.setAdapter(adapter);
        List<Travel> list = createTravelList();
        for (Travel travel : list) {
            adapter.add(travel);
        }
    }

    //TODO do wywalenia - tylko testowo

    List<Travel> createTravelList() {
        List<Travel> list = new LinkedList<>();
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Krakowiak", "Krakow", 5.0f, 20));
        list.add(new Travel("Podróż po Zamościu", "Zamość", 5.0f, 11));
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Górach", "Zakopane", 5.0f, 12));
        return list;
    }
}
