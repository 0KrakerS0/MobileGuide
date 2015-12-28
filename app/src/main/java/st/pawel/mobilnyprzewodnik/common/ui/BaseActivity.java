package st.pawel.mobilnyprzewodnik.common.ui;

import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    protected ActionBar prepareActionBar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        return getSupportActionBar();
    }
}
