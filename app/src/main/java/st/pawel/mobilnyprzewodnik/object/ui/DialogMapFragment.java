package st.pawel.mobilnyprzewodnik.object.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
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
import st.pawel.mobilnyprzewodnik.R;

public class DialogMapFragment extends DialogFragment implements GoogleMap.OnMapClickListener {

    private static final String LATITUDE_ARG = "LATITUDE_ARG";

    private static final String LONGITUDE_ARG = "LONGITUDE_ARG";

    @Bind(R.id.dialog_map)
    MapView dialogMap;

    GoogleMap.OnMapClickListener delegate;

    public static DialogMapFragment newInstance(LatLng latLng) {
        final DialogMapFragment dialogMapFragment = new DialogMapFragment();
        if (latLng != null) {
            Bundle args = new Bundle();
            args.putDouble(LATITUDE_ARG, latLng.latitude);
            args.putDouble(LONGITUDE_ARG, latLng.longitude);
            dialogMapFragment.setArguments(args);
        }
        return dialogMapFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.dialog_fragment_map, container, false);
        ButterKnife.bind(this, inflate);
        dialogMap.onCreate(savedInstanceState);
        final GoogleMap map = dialogMap.getMap();
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.setMyLocationEnabled(true);

        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.setOnMapClickListener(this);
        try {
            MapsInitializer.initialize(this.getActivity());
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
        Bundle arguments = getArguments();
        double lat = arguments.getDouble(LATITUDE_ARG, 50.971350);
        double lng = arguments.getDouble(LONGITUDE_ARG, 22.050517);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(lat, lng), 15);
        map.moveCamera(cameraUpdate);
        return inflate;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setTitle(R.string.add_object_map_dialog_title);
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        delegate.onMapClick(latLng);

        dismiss();
    }

    @Override
    public void dismiss() {
        dialogMap.onPause();
        dialogMap.onDestroy();
        dialogMap = null;
        super.dismiss();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GoogleMap.OnMapClickListener) {
            //noinspection unchecked
            delegate = (GoogleMap.OnMapClickListener) context;
            return;
        }
        throw new IllegalArgumentException("Context musi implementowac GoogleMap.OnMapClickListener");
    }

    @Override
    public void onDetach() {
        delegate = null;
        super.onDetach();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onResume() {
        dialogMap.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (dialogMap != null) {
            dialogMap.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public void onPause() {
        if (dialogMap != null) {
            dialogMap.onPause();
        }
        super.onPause();
    }

    @Override
    public void onLowMemory() {
        if (dialogMap != null) {
            dialogMap.onLowMemory();
        }
        super.onLowMemory();
    }
}
