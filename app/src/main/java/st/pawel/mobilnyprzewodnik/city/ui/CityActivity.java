package st.pawel.mobilnyprzewodnik.city.ui;

import android.content.Context;
import android.content.Intent;
import android.location.LocationProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;

public class CityActivity extends BaseActivity {

    @Bind(R.id.city_toolbar)
    Toolbar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        ButterKnife.bind(this);
        prepareActionBar(actionBar);
        if(savedInstanceState != null){
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


    public static class IntentFactory {

        public static Intent forDisplay(Context context) {
            return new Intent(context, CityActivity.class);
        }
    }
}
