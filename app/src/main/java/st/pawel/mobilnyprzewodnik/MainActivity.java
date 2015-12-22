package st.pawel.mobilnyprzewodnik;

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
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.main.delegate.MenuFragmentDelegate;
import st.pawel.mobilnyprzewodnik.main.model.MainMenu;
import st.pawel.mobilnyprzewodnik.main.ui.MenuFragment;
import st.pawel.mobilnyprzewodnik.map.ui.MainMapFragment;

public class MainActivity extends BaseActivity implements MenuFragmentDelegate<MainMenu> {


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
        Toast.makeText(this, "Jeszcze nie działa dla " + menu.name(), Toast.LENGTH_SHORT).show();
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
}
