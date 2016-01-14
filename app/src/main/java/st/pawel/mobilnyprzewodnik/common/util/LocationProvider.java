package st.pawel.mobilnyprzewodnik.common.util;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import java.util.List;

public class LocationProvider {

    private final LocationManager locationManager;

    private static final long REQUEST_LOCATION_DURATION = 3000l;

    public LocationProvider(LocationManager locationManager) {
        this.locationManager = locationManager;
    }


    public Location getLastKnownBestLocation(Context context) {
        Location bestResult = null;
        final List<String> matchingProviders = locationManager.getAllProviders();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            bestResult = getBestLastKnownLocation(matchingProviders);
            return bestResult;
        }
        if (Build.VERSION.SDK_INT < 23) {
            bestResult = getBestLastKnownLocation(matchingProviders);
            return bestResult;
        }
        return bestResult;
    }

    @SuppressWarnings("ResourceType")
    Location getBestLastKnownLocation(List<String> matchingProviders) {
        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;
        for (final String provider : matchingProviders) {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                float accuracy = location.getAccuracy();
                if (accuracy < bestAccuracy) {
                    bestResult = location;
                } else if (bestAccuracy == Float.MAX_VALUE) {
                    bestResult = location;
                }
            }
        }
        return bestResult;
    }

    public void requestLocation(Context context, LocationListener locationListener) {
        final List<String> matchingProviders = locationManager.getAllProviders();
        for (String provider : matchingProviders) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(provider, REQUEST_LOCATION_DURATION, 0, locationListener);
            }
        }
        if (Build.VERSION.SDK_INT < 23) {
            for (String provider : matchingProviders) {
                locationManager.requestLocationUpdates(provider, REQUEST_LOCATION_DURATION, 0, locationListener);
            }
        }
    }

    public void removeUpdates(Context context, LocationListener locationListener) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            locationManager.removeUpdates(locationListener);
        }
        if (Build.VERSION.SDK_INT < 23) {
            locationManager.removeUpdates(locationListener);
        }
    }

    public boolean isProviderEnabled() {
        final List<String> matchingProviders = locationManager.getAllProviders();
        for (final String provider : matchingProviders) {
            if (locationManager.isProviderEnabled(provider)) {
                return true;
            }
        }
        return false;
    }
}
