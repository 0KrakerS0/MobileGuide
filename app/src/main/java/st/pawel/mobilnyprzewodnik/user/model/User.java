package st.pawel.mobilnyprzewodnik.user.model;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import st.pawel.mobilnyprzewodnik.main.ui.model.UserView;

public class User implements UserView {

    private interface Metadata {

        String OBJECT_ID = "objectId";
        String FIRST_NAME = "firstName";
        String LAST_NAME = "lastName";
        String LOGIN = "login";
        String EMAIL = "email";
        String PASSWORD = "password";
        String IMAGE_URL = "imageUrl";
    }

    @Setter
    @Getter
    @SerializedName(Metadata.OBJECT_ID)
    String objectId;

    @Setter
    @Getter
    @SerializedName(Metadata.FIRST_NAME)
    String firstName;

    @Setter
    @Getter
    @SerializedName(Metadata.LAST_NAME)
    String lastName;

    @Setter
    @Getter
    @SerializedName(Metadata.LOGIN)
    String login;

    @Setter
    @Getter
    @SerializedName(Metadata.EMAIL)
    String email;

    @Setter
    @Getter
    @SerializedName(Metadata.PASSWORD)
    String password;

    @Setter
    @Getter
    @SerializedName(Metadata.IMAGE_URL)
    String imageUrl;

    @Override
    public String userImageUrl() {
        return null;
    }

    @Override
    public String userFullName() {
        return null;
    }

    @Override
    public String userEmail() {
        return email;
    }
}
