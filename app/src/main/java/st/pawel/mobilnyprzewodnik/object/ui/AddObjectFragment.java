package st.pawel.mobilnyprzewodnik.object.ui;


import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.google.android.gms.maps.model.LatLng;
import java.security.SecureRandom;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.map.model.MarkerType;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectAddDelegate;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.ui.adapter.ObjectTypeSpinnerAdapter;

public class AddObjectFragment extends DelegateBaseFragment<ObjectAddDelegate> implements LocationListener {

    @Bind(R.id.add_object_name)
    EditText objectName;

    @Bind(R.id.add_object_city_name)
    EditText objectCityName;

    @Bind(R.id.add_object_location)
    TextView objectLocation;

    @Bind(R.id.add_object_type)
    Spinner objectType;

    @Bind(R.id.add_object_rate)
    RatingBar objectRate;

    private final StringBuilder sb = new StringBuilder();

    public static AddObjectFragment newInstance() {
        AddObjectFragment fragment = new AddObjectFragment();
        return fragment;
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_add_object, container, false);
        ButterKnife.bind(this, result);
        prepareObjectTypeSpinner();
        prepareObjectLocationText();
        return result;
    }

    private void prepareObjectLocationText(){
        LatLng latLong = delegate.requestForLastKnownPosition();
        if(latLong == null){
           return;
        }
        objectLocation.setText(locationText(latLong.latitude, latLong.longitude));
    }

    private void prepareObjectTypeSpinner() {
        final ObjectTypeSpinnerAdapter adapter = new ObjectTypeSpinnerAdapter();
        MarkerType[] markerTypes = MarkerType.values();
        for (MarkerType markerType : markerTypes) {
            if (markerType.isStandard()) {
                adapter.add(markerType);
            }
        }
        objectType.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_accept, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_accept) {
            final ObjectModel objectModel = new ObjectModel();
            objectModel.setObjectName(objectName.getText().toString());
            //TODO tymczasowy obrazek
            objectModel.setObjectImageUrl("http://lorempixel.com/200/400/city/" + new SecureRandom().nextInt(10) + "/");
            objectModel.setObjectType((MarkerType) objectType.getSelectedItem());
            objectModel.setObjectCityName(objectCityName.getText().toString());
            objectModel.setObjectRate(objectRate.getRating());
            String location = objectLocation.getText().toString();
            if(!location.isEmpty()){
                String[] locationArray = location.split(";");
                objectModel.setLatitude(Double.parseDouble(locationArray[0]));
                objectModel.setLongitude(Double.parseDouble(locationArray[1]));
            }
            delegate.addObject(objectModel);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof ObjectAddDelegate;
    }

    @Override
    protected String delegateClassName() {
        return ObjectAddDelegate.class.getSimpleName();
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location == null){
            return;
        }
        objectLocation.setText(locationText(location.getLatitude(), location.getLongitude()));
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

    private String locationText(double latitude, double longitude){
        sb.setLength(0);
        return sb.append(latitude).append(";").append(longitude).toString();
    }
}
