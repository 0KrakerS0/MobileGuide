package st.pawel.mobilnyprzewodnik;

import android.os.Handler;
import  android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
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
import st.pawel.mobilnyprzewodnik.city.network.PostCityRequest;
import st.pawel.mobilnyprzewodnik.city.ui.CityFragment;
import st.pawel.mobilnyprzewodnik.city.ui.model.CityView;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.main.delegate.MenuFragmentDelegate;
import st.pawel.mobilnyprzewodnik.main.model.MainMenu;
import st.pawel.mobilnyprzewodnik.main.ui.MenuFragment;
import st.pawel.mobilnyprzewodnik.map.ui.MainMapFragment;
import st.pawel.mobilnyprzewodnik.travels.delegate.TravelFragmentDelegate;
import st.pawel.mobilnyprzewodnik.travels.listener.OnTravelsRequestListener;
import st.pawel.mobilnyprzewodnik.travels.model.Travel;
import st.pawel.mobilnyprzewodnik.travels.ui.TravelsFragment;
import st.pawel.mobilnyprzewodnik.travels.ui.model.TravelView;

public class MainActivity extends BaseActivity implements MenuFragmentDelegate<MainMenu>, CityFragmentDelegate<CityModel>, TravelFragmentDelegate<Travel> {


    @Bind(R.id.main_toolbar)
    Toolbar mainActionBar;

    @Bind(R.id.main_drawer)
    DrawerLayout mainDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        prepareActionBar();
        if(savedInstanceState != null){
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.main_menu_container, MenuFragment.newInstance()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, MainMapFragment.newInstance()).commit();
    }

    void prepareActionBar() {
        setSupportActionBar(mainActionBar);
        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_action_menu);
        }

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
        CityModel cm = new CityModel();
        cm.setCityName("Poznań");
        PostCityRequest.instance().withCityModel(cm).request().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Response<Void> response, Retrofit retrofit) {
                if (response.isSuccess()) {
                    Toast.makeText(MainActivity.this, "Dodales miasto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public void onTravelClick(Travel travel) {
        Toast.makeText(this, "Kliknales " + travel.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                openDrawer();
                return true;
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
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
                if(fragment == null || !(fragment instanceof OnCityRequestListener)){
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
                if(fragment == null || !(fragment instanceof OnCityRequestListener)){
                    return;
                }
                OnCityRequestListener listener = (OnCityRequestListener) fragment;
                listener.onCityRequestFailure();
            }
        });
    }

    @Override
    public void requestForTravelList() {
        new Handler().postDelayed(()->{
                    Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.main_container);
                    if(fragment == null || !(fragment instanceof OnTravelsRequestListener)){
                        return;
                    }
                    OnTravelsRequestListener listener = (OnTravelsRequestListener) fragment;
                    List<TravelView> result = new LinkedList<>();
                    List<Travel> list= createTravelList();
                    for(TravelView travelView : list){
                        result.add(travelView);
                    }
                    listener.onTravelsRequestSuccess(result);
                },
                1000);

    }
    //TODO do wywalenia - tylko testowo
    List<Travel> createTravelList() {
        List<Travel> list = new LinkedList<>();
        list.add(new Travel("Podróż po górach", "Zakopane", 5.0f, 12));
        list.add(new Travel("Podróż po Krakowiak", "Krakow", 5.0f, 20));
        list.add(new Travel("Podróż po Zamościu", "Zamość", 2.0f, 11));
        list.add(new Travel("Podróż po Warszawie", "Warszawa", 1, 12));
        list.add(new Travel("Podróż po Szczecinie", "Szczecin", 5.0f, 6));
        list.add(new Travel("Podróż po Zamościu", "Zamość", 5.0f, 21));
        list.add(new Travel("Wyjazd nad jeziora", "Mazury", 4.0f, 14));
        list.add(new Travel("Podróż nad morze", "Gdańsk", 5.0f, 1));
        return list;
    }
}
