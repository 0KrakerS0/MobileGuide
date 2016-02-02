package st.pawel.mobilnyprzewodnik.auth.ui;

import android.content.Context;
import android.content.Intent;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;

public class LoginActivity extends BaseActivity {

    public static class IntentFactory {

        public static Intent forDisplay(Context context) {
            return new Intent(context, LoginActivity.class);
        }
    }
}
