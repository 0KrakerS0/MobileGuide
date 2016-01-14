package st.pawel.mobilnyprzewodnik.object.ui;


import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.gms.maps.model.LatLng;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.common.util.LocationProvider;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectAddDelegate;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.network.PostObjectRequest;

public class ObjectActivity extends BaseActivity implements ObjectAddDelegate, LocationListener {

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
        if(!locationManager.isProviderEnabled()){
            Toast.makeText(this, R.string.no_location_provider, Toast.LENGTH_SHORT).show();
        }
        final Location lastKnownBestLocation = locationManager.getLastKnownBestLocation();
        locationManager.requestLocation(this);
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
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.object_container);
        if (fragment != null && fragment instanceof LocationListener) {
            ((LocationListener) fragment).onLocationChanged(location);
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