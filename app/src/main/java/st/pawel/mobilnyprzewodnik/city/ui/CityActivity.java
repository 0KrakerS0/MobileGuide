package st.pawel.mobilnyprzewodnik.city.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.city.delegate.CityAddDelegate;
import st.pawel.mobilnyprzewodnik.city.model.CityModel;
import st.pawel.mobilnyprzewodnik.city.network.PostCityRequest;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;

public class CityActivity extends BaseActivity implements CityAddDelegate {

    @Bind(R.id.city_toolbar)
    Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        prepareActionBar(actionBar);
        if (savedInstanceState != null) {
            return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.city_container, AddCityFragment.newInstance()).commit();
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
    public void addCity(CityModel cityModel) {
        PostCityRequest.instance().withCityModel(cityModel).request().enqueue(new Callback<Void>() {
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

    public static class IntentFactory {

        public static Intent forDisplay(Context context) {
            return new Intent(context, CityActivity.class);
        }
    }
}
