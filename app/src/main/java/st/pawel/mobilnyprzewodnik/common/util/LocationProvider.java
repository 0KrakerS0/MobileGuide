package st.pawel.mobilnyprzewodnik.common.util;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import java.util.List;

public class LocationProvider {

    private final LocationManager locationManager;

    private long REQUEST_LOCATION_DURATION = 3000l;

    public LocationProvider(LocationManager locationManager) {
        this.locationManager = locationManager;
    }

    @SuppressWarnings("ResourceType")
    public Location getLastKnownBestLocation() {
        Location bestResult = null;
        float bestAccuracy = Float.MAX_VALUE;

        final List<String> matchingProviders = locationManager.getAllProviders();
        for (final String provider : matchingProviders) {
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                float accuracy = location.getAccuracy();
                if (accuracy < bestAccuracy) {
                    bestResult = location;
                    bestAccuracy = accuracy;
                } else if (bestAccuracy == Float.MAX_VALUE) {
                    bestResult = location;
                }
            }
        }
        return bestResult;
    }

    @SuppressWarnings("ResourceType")
    public void requestLocation(LocationListener locationListener) {
        final List<String> matchingProviders = locationManager.getAllProviders();
        for (String provider : matchingProviders) {
            locationManager.requestLocationUpdates(provider, REQUEST_LOCATION_DURATION, 0, locationListener);
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
