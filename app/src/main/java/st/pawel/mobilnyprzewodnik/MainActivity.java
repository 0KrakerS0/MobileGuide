package st.pawel.mobilnyprzewodnik;

import android.content.Intent;
import android.os.Handler;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestResult;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.city.delegate.CityFragmentDelegate;
import st.pawel.mobilnyprzewodnik.city.listener.OnCityRequestListener;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.city.model.CityResults;
import st.pawel.mobilnyprzewodnik.city.network.GetCityRequest;
import st.pawel.mobilnyprzewodnik.city.ui.CityActivity;
import st.pawel.mobilnyprzewodnik.city.ui.CityFragment;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.common.util.C;
import st.pawel.mobilnyprzewodnik.main.delegate.MenuFragmentDelegate;
import st.pawel.mobilnyprzewodnik.main.model.MainMenu;
import st.pawel.mobilnyprzewodnik.main.ui.MenuFragment;
import st.pawel.mobilnyprzewodnik.map.ui.MainMapFragment;
import st.pawel.mobilnyprzewodnik.object.delegate.ObjectfFragmentDelegate;
import st.pawel.mobilnyprzewodnik.object.listener.OnObjectRequestListener;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;
import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;
import st.pawel.mobilnyprzewodnik.object.network.GetObjectRequest;
import st.pawel.mobilnyprzewodnik.object.ui.ObjectActivity;
import st.pawel.mobilnyprzewodnik.object.ui.ObjectFragment;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;
import st.pawel.mobilnyprzewodnik.travels.delegate.TravelFragmentDelegate;
import st.pawel.mobilnyprzewodnik.travels.listener.OnTravelsRequestListener;
import st.pawel.mobilnyprzewodnik.travels.model.TravelModel;
import st.pawel.mobilnyprzewodnik.travels.model.TravelResult;
import st.pawel.mobilnyprzewodnik.travels.network.GetTravelRequest;
import st.pawel.mobilnyprzewodnik.travels.ui.TravelsFragment;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public class MainActivity extends BaseActivity implements MenuFragmentDelegate<MainMenu>, CityFragmentDelegate<CityModel>, TravelFragmentDelegate<TravelModel>, ObjectfFragmentDelegate<ObjectModel> {

    @Bind(R.id.main_toolbar)
    Toolbar mainActionBar;

    @Bind(R.id.main_drawer)
    DrawerLayout mainDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareActionBar(mainActionBar);
        if(savedInstanceState != null){
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_menu_container, MenuFragment.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, MainMapFragment.newInstance()).commit();
    }

    protected ActionBar prepareActionBar(Toolbar toolbar) {
        ActionBar actionBar = super.prepareActionBar(toolbar);
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
        }
        return actionBar;
    }

    @Override
    public void onMenuItemClick(MainMenu menu) {

        switch (menu) {
            case MAP:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, MainMapFragment.newInstance()).commit();
                break;
            case CITY_LIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, CityFragment.newInstance()).commit();
                break;
            case TRAVEL_LIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, TravelsFragment.newInstance()).commit();
                break;
            case OBJECTS_LIST:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_container, ObjectFragment.newInstance()).commit();
                break;
            case USER_DATA:
                Toast.makeText(this, "Jeszcze nie działa dla " + menu.name(), Toast.LENGTH_SHORT).show();
                break;
            case LOGOUT:
                Toast.makeText(this, "Jeszcze nie działa dla " + menu.name(), Toast.LENGTH_SHORT).show();
                break;
        }
        mainDrawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onCityClick(CityModel cityModel) {
        Toast.makeText(this, "Kliknales " + cityModel.cityName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTravelClick(TravelModel travelModel) {
        Toast.makeText(this, "Kliknales " + travelModel.travelName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onObjectClick(ObjectModel objectModel) {
        Toast.makeText(this,"Kliknales " + objectModel.objectName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                openDrawer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openDrawer() {
        if (!mainDrawer.isDrawerOpen(GravityCompat.START)) {
            mainDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public void requestForCityList() {
        GetCityRequest.instance().request().enqueue(new Callback<CityResults>() {
            @Override
            public void onResponse(Response<CityResults> response, Retrofit retrofit) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnCityRequestListener)) {
                    return;
                }
                OnCityRequestListener listener = (OnCityRequestListener) fragment;

                listener.onCityRequestSuccess(response.body());

            }

            @Override
            public void onFailure(Throwable t) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnCityRequestListener)) {
                    return;
                }
                OnCityRequestListener listener = (OnCityRequestListener) fragment;
                listener.onCityRequestFailure();
            }
        });
    }

    @Override
    public void onAddCityButtonClick() {
       startActivityForResult(CityActivity.IntentFactory.forDisplay(this), C.RequestCode.ADD_NEW_CITY);
    }

    @Override
    public void onAddObjectButtonClick() {
        startActivityForResult(ObjectActivity.IntentFactory.forDisplay(this), C.RequestCode.ADD_NEW_OBJECT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK){
            return;
        }
        if(requestCode == C.RequestCode.ADD_NEW_CITY){
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
            if(fragment != null && fragment instanceof OnCityRequestListener){
                OnCityRequestListener onCityRequestListener = (OnCityRequestListener) fragment;
                onCityRequestListener.onCityRequestStart();
                requestForCityList();
                return;
            }
        }
        if(requestCode == C.RequestCode.ADD_NEW_OBJECT){
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
            if(fragment != null && fragment instanceof OnObjectRequestListener){
                OnObjectRequestListener onObjectRequestListener = (OnObjectRequestListener) fragment;
                onObjectRequestListener.onObjectRequestStart();
                requestForObjectList();
                return;
            }
        }
    }

    @Override
    public void requestForTravelList() {
        GetTravelRequest.instance().request().enqueue(new Callback<TravelResult>() {
            @Override
            public void onResponse(Response<TravelResult> response, Retrofit retrofit) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnTravelsRequestListener)) {
                    return;
                }
                OnTravelsRequestListener listener = (OnTravelsRequestListener) fragment;
                listener.onTravelsRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnTravelsRequestListener)) {
                    return;
                }
                OnTravelsRequestListener listener = (OnTravelsRequestListener) fragment;
                listener.onTravelsRequestFailure();
            }
        });
    }

    @Override
    public void requestForObjectList() {
        GetObjectRequest.instance().request().enqueue(new Callback<ObjectResult>() {
            @Override
            public void onResponse(Response<ObjectResult> response, Retrofit retrofit) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnObjectRequestListener)){
                    return;
                }
                OnObjectRequestListener listener = (OnObjectRequestListener) fragment;
                listener.onObjectRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                if (fragment == null || !(fragment instanceof OnObjectRequestListener)){
                    return;
                }
                OnObjectRequestListener listener = (OnObjectRequestListener) fragment;
                listener.onObjectRequestFailure();
            }
        });

    }
}
