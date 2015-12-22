package st.pawel.mobilnyprzewodnik.map.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseFragment;

public class MainMapFragment extends BaseFragment {

    @Bind(R.id.map_map)
    MapView mapView;

    public static MainMapFragment newInstance() {
        return new MainMapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, v);
        mapView.onCreate(savedInstanceState);

        GoogleMap map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.addMarker(new MarkerOptions().position(new LatLng(50.9660138, 22.0427032)).title("Test"));
        map.addPolygon(new PolygonOptions().add(new LatLng(50.9660138, 22.0427032),new LatLng(51.9660138, 21.0427032)));
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(50.9660138, 22.0427032), 10);
        map.animateCamera(cameraUpdate);

        return v;
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
