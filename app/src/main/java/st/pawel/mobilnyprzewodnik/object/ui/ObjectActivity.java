package st.pawel.mobilnyprzewodnik.object.ui;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import java.io.File;
import java.io.IOException;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.listener.OnCityRequestListener;
import st.pawel.mobilnyprzewodnik.city.model.CityResults;
import st.pawel.mobilnyprzewodnik.city.network.GetCityRequest;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.common.util.C;
import st.pawel.mobilnyprzewodnik.common.util.LocationProvider;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectAddDelegate;
import st.pawel.mobilnyprzewodnik.object.listener.OnLocationRequestListener;
import st.pawel.mobilnyprzewodnik.object.listener.OnPhotoTakenListener;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.network.PostObjectRequest;

public class ObjectActivity extends BaseActivity implements ObjectAddDelegate, LocationListener, GoogleMap.OnMapClickListener {

    private static final String MAP_TAG = "MAP_TAG";

    private static final String FILE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/temp.png";

    @Bind(R.id.object_toolbar)
    Toolbar actionBar;

    LocationProvider locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = new LocationProvider((LocationManager) getSystemService(Context.LOCATION_SERVICE));
        setContentView(R.layout.activity_object);
        ButterKnife.bind(this);
        prepareActionBar(actionBar);
        if (savedInstanceState != null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.object_container, AddObjectFragment.newInstance()).commit();
    }

    @Nullable
    @Override
    protected ActionBar prepareActionBar(Toolbar toolbar) {
        final ActionBar actionBar = super.prepareActionBar(toolbar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        return actionBar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void addObject(ObjectModel objectModel) {
        PostObjectRequest.instance().withObjectModel(objectModel).request().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    setResult(RESULT_OK);
                    finish();
                }
            }


            @Override
            public void onFailure(Throwable t) {
            }
        });

    }

    @Override
    public LatLng requestForLastKnownPosition() {
        if (!locationManager.isProviderEnabled()) {
            Toast.makeText(this, R.string.no_location_provider, Toast.LENGTH_SHORT).show();
        }
        final Location lastKnownBestLocation = locationManager.getLastKnownBestLocation(this);
        locationManager.requestLocation(this, this);
        if (lastKnownBestLocation == null) {
            return null;
        }
        return new LatLng(lastKnownBestLocation.getLatitude(), lastKnownBestLocation.getLongitude());
    }

    public static class IntentFactory {

        public static Intent forDisplay(Context context) {
            return new Intent(context, ObjectActivity.class);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        onMapClick(new LatLng(location.getLatitude(), location.getLongitude()));
    }

    @Override
    public void requestForLocationFromMap(LatLng latLng) {
        locationManager.removeUpdates(this, this);
        final DialogMapFragment dialogMapFragment = DialogMapFragment.newInstance(latLng);
        dialogMapFragment.show(getSupportFragmentManager(), MAP_TAG);
    }

    @Override
    public void requestForCityList() {
        GetCityRequest.instance().request().enqueue(new Callback<CityResults>() {
            @Override
            public void onResponse(Response<CityResults> response, Retrofit retrofit) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.object_container);
                if (fragment == null || !(fragment instanceof OnCityRequestListener)) {
                    return;
                }
                OnCityRequestListener listener = (OnCityRequestListener) fragment;

                listener.onCityRequestSuccess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.object_container);
                if (fragment == null || !(fragment instanceof OnCityRequestListener)) {
                    return;
                }
                OnCityRequestListener listener = (OnCityRequestListener) fragment;
                listener.onCityRequestFailure();
            }
        });
    }

    @Override
    public void onMapClick(LatLng latLng) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.object_container);
        if (fragment != null && fragment instanceof OnLocationRequestListener) {
            ((OnLocationRequestListener) fragment).onLocationRequestSuccess(latLng);
        }
    }

    @Override
    public void requestForTakePhoto() {
        File newfile = new File(FILE_PATH);
        try {
            if (!newfile.exists()) {
                newfile.createNewFile();
            }
        } catch (IOException e) {
            Log.e(ObjectActivity.class.getSimpleName(), e.getMessage());
        }
        Uri outputFileUri = Uri.fromFile(newfile);
        final Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(cameraIntent, C.RequestCode.TAKE_A_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == C.RequestCode.TAKE_A_PHOTO) {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.object_container);
            if (fragment != null && fragment instanceof OnPhotoTakenListener) {
                ((OnPhotoTakenListener) fragment).onPhotoTaken(FILE_PATH);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        //nic nie rób
    }

    @Override
    public void onProviderEnabled(String provider) {
        //nic nie rób
    }

    @Override
    public void onProviderDisabled(String provider) {
        //nic nie rób
    }
}