package st.pawel.mobilnyprzewodnik.map.ui;

import android.content.Context;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import org.parceler.Parcels;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectfFragmentDelegate;
import st.pawel.mobilnyprzewodnik.object.listener.OnObjectRequestListener;
import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;

public class MainMapFragment extends DelegateBaseFragment<ObjectfFragmentDelegate> implements OnObjectRequestListener {

    private static final String OBJECT_RESULT = "OBJECT_RESULT";

    @Bind(R.id.map_map)
    MapView mapView;

    ObjectResult objectResult;

    public static MainMapFragment newInstance() {
        return new MainMapFragment();
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
        final View v = inflater.inflate(R.layout.fragment_map, container, false);
        ButterKnife.bind(this, v);
        if (savedInstanceState != null) {
            //To jest wyjątkowa sytuacja bo mapView nie radzi sobie z Parcel z Parcelera
            objectResult = Parcels.unwrap(savedInstanceState.getParcelable(OBJECT_RESULT));
            savedInstanceState.remove(OBJECT_RESULT);
        }
        mapView.onCreate(savedInstanceState);

        final GoogleMap map = mapView.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);
        map.addPolygon(new PolygonOptions().add(new LatLng(50.9660138, 22.0427032), new LatLng(51.9660138, 21.0427032)));

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (objectResult == null) {
            delegate.requestForObjectList();
            return;
        }
        onObjectRequestSuccess(objectResult);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(OBJECT_RESULT, Parcels.wrap(objectResult));
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onObjectRequestStart() {

    }

    @Override
    public void onObjectRequestSuccess(ObjectResult objectResult) {
        this.objectResult = objectResult;
        final GoogleMap map = mapView.getMap();
        for (ObjectView objectView : objectResult) {
            map.addMarker(new MarkerOptions()
                            .position(objectView.getLatLng())
                            .title(objectView.objectName() + " " + getString(objectView.typeRes()))
                            .icon(BitmapDescriptorFactory.fromResource(objectView.markerIcon()))
            );
        }

    }

    @Override
    public void onObjectRequestFailure() {

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroyView() {
        mapView.onDestroy();
        super.onDestroyView();
    }

    @Override
    public void onLowMemory() {
        mapView.onLowMemory();
        super.onLowMemory();
    }
}
