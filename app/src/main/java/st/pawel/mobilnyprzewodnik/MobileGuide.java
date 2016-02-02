package st.pawel.mobilnyprzewodnik;

import android.app.Application;
import st.pawel.mobilnyprzewodnik.auth.logic.AuthManager;

public class MobileGuide extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AuthManager.INSTANCE.initialize(this);
    }
}
