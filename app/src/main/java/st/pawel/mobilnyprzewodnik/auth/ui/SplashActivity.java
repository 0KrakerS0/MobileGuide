package st.pawel.mobilnyprzewodnik.auth.ui;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import st.pawel.mobilnyprzewodnik.MainActivity;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.auth.logic.AuthManager;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;

public class SplashActivity extends BaseActivity {

    private static final long SPLASH_DURATION = 2000l;

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        runTimer();
    }

    void runTimer() {
        handler.postDelayed(this::displayWelcomeActivity, SPLASH_DURATION);
    }

    void displayWelcomeActivity() {
        final Intent intent = AuthManager.INSTANCE.isLogged()
                ? MainActivity.IntentFactory.forDisplay(this)
                : LoginActivity.IntentFactory.forDisplay(this);
        startActivity(intent);
        finish();
    }
}
