package st.pawel.mobilnyprzewodnik.auth.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import st.pawel.mobilnyprzewodnik.MainActivity;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.auth.logic.AuthManager;
import st.pawel.mobilnyprzewodnik.common.ui.BaseActivity;
import st.pawel.mobilnyprzewodnik.user.model.UserResult;
import st.pawel.mobilnyprzewodnik.user.network.LoginRequest;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.login_login)
    EditText loginLogin;

    @Bind(R.id.login_password)
    EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_button)
    void onLoginButtonClick() {
        final Editable login = loginLogin.getText();
        final Editable password = loginPassword.getText();
        if (login.length() == 0 || password.length() == 0) {
            Toast.makeText(this, R.string.login_empty_data_message, Toast.LENGTH_SHORT).show();
            return;
        }
        new LoginRequest(login.toString(), password.toString()).request().enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Response<UserResult> response, Retrofit retrofit) {
                if (response.body().isEmpty()) {
                    Toast.makeText(LoginActivity.this, R.string.login_wrong_data_message, Toast.LENGTH_SHORT).show();
                    return;
                }
                AuthManager.INSTANCE.userId(response.body().getFirst().getObjectId());
                startActivity(MainActivity.IntentFactory.forDisplay(LoginActivity.this));
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(LoginActivity.this, R.string.login_server_error_message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static class IntentFactory {

        public static Intent forDisplay(Context context) {
            return new Intent(context, LoginActivity.class);
        }
    }
}
