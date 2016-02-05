package st.pawel.mobilnyprzewodnik.user.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import org.parceler.Parcels;
import st.pawel.mobilnyprzewodnik.R;
import st.pawel.mobilnyprzewodnik.common.ui.DelegateBaseFragment;
import st.pawel.mobilnyprzewodnik.common.util.ImageLoaderProvider;
import st.pawel.mobilnyprzewodnik.user.delegate.UserDataFragmentDelegate;
import st.pawel.mobilnyprzewodnik.user.model.User;

public class UserDataFragment extends DelegateBaseFragment<UserDataFragmentDelegate> {

    private static final String USER = "USER";

    @Bind(R.id.user_data_image)
    ImageView image;

    @Bind(R.id.user_data_login)
    EditText login;

    @Bind(R.id.user_data_first_name)
    EditText firstName;

    @Bind(R.id.user_data_last_name)
    EditText lastName;

    @Bind(R.id.user_data_email)
    EditText email;

    @Bind(R.id.user_data_password)
    EditText password;

    @Bind(R.id.user_data_confirm_password)
    EditText confirmPassword;

    @Bind(R.id.user_data_button)
    Button button;

    public static UserDataFragment newInstance(User user) {
        UserDataFragment userDataFragment = new UserDataFragment();
        Bundle args = new Bundle();
        args.putParcelable(USER, Parcels.wrap(user));
        userDataFragment.setArguments(args);
        return userDataFragment;
    }

    @Override
    protected boolean instanceOfDelegate(Context context) {
        return context instanceof UserDataFragmentDelegate;
    }

    @Override
    protected String delegateClassName() {
        return UserDataFragmentDelegate.class.getSimpleName();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    User getUserFromArgs() {
        Bundle args = getArguments();
        if (args == null) {
            throw new IllegalArgumentException("Podaj usera w argumentach.");
        }
        Parcelable parcelable = args.getParcelable(USER);
        if (parcelable == null) {
            throw new IllegalArgumentException("Podaj usera w argumentach.");
        }
        return Parcels.unwrap(parcelable);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillForm(savedInstanceState == null);
    }

    private void fillForm(boolean firstTime) {
        User user = getUserFromArgs();
        ImageLoaderProvider.newInstance(getActivity()).displayImage(user.getImageUrl(), image);
        if (!firstTime) {
            return;
        }
        login.setText(user.getLogin());
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

}
