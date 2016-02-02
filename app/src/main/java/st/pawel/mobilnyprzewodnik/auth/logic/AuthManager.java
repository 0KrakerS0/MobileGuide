package st.pawel.mobilnyprzewodnik.auth.logic;


import android.content.Context;
import android.content.SharedPreferences;

public enum AuthManager {

    INSTANCE;

    private static final String AUTH_SP = "AUTH_SP";

    private static final String USER_ID = "USER_ID";

    SharedPreferences auth;

    public void initialize(Context context) {
        auth = context.getSharedPreferences(AUTH_SP, Context.MODE_APPEND);
    }

    public boolean isLogged() {
        return auth.contains(USER_ID);
    }

    public String userId() {
        final String userId = auth.getString(USER_ID, null);
        if (userId == null) {
            throw new IllegalStateException("Sprawdz czy istnieje userId za pomocą metody isLogged zanim wywołasz tą metodę");
        }
        return userId;
    }

    public void userId(String userId) {
        auth.edit().putString(USER_ID, userId).apply();
    }

    public void logout() {
        auth.edit().clear().apply();
    }
}
