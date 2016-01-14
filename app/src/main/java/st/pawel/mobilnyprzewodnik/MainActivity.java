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
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }

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
                List<CityView> result = new LinkedList<>();
                for (CityModel city : response.body()) {
                    result.add(city);
                }
                listener.onCityRequestSuccess(result);
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
        new Handler().postDelayed(() -> {
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                    if (fragment == null || !(fragment instanceof OnTravelsRequestListener)) {
                        return;
                    }
                    OnTravelsRequestListener listener = (OnTravelsRequestListener) fragment;
                    List<TravelView> result = new LinkedList<>();
                    List<TravelModel> list = createTravelList();
                    for (TravelView travelView : list) {
                        result.add(travelView);
                    }
                    listener.onTravelsRequestSuccess(result);
                },
                1000);

    }
    //TODO do wywalenia - tylko testowo
    List<TravelModel> createTravelList() {
        List<TravelModel> list = new LinkedList<>();
        list.add(new TravelModel("https://upload.wikimedia.org/wikipedia/commons/thumb/1/1e/Rysy_2.jpg/240px-Rysy_2.jpg","Podróż po górach", "Zakopane", 5.0f, 12));
        list.add(new TravelModel("http://images.polskaniezwykla.pl/user/item/144398.jpg","Podróż po Krakowie", "Krakow", 3.5f, 20));
        list.add(new TravelModel("https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Zamosc_rynek_ratusz.jpg/240px-Zamosc_rynek_ratusz.jpg","Podróż po Zamościu", "Zamość", 2.0f, 11));
        list.add(new TravelModel("https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcQyVT6Tv-S58WVux4qtHOr_bAqL7Yw_rRbOHK2as-p2Y3BZ8RLe","Podróż po Warszawie", "Warszawa", 1.0f, 12));
        list.add(new TravelModel("http://www.szczecin.eu/sites/all/themes/szczecin/slideshow/1.jpg","Podróż po Szczecinie", "Szczecin", 4.5f, 6));
        list.add(new TravelModel("http://www.ssm.konin.pl/sites/default/files/jezioro.jpg", "Wyjazd nad jeziora", "Mazury", 4.0f, 14));
        list.add(new TravelModel("http://usasa.pl/wp-content/uploads/2015/04/POL_2007_08_04_Jaroslawiec_zachodniopomorskie_02-1170x429.jpg", "Podróż nad morze", "Gdańsk", 2.5f, 1));
        return list;
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
                List<ObjectView> result = new LinkedList<>();
                for (ObjectModel object : response.body()){
                    result.add(object);
                }
                listener.onObjectRequestSuccess(result);
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
