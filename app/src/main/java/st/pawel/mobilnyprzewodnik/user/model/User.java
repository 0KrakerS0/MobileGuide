package st.pawel.mobilnyprzewodnik.user.model;


import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.main.ui.model.UserView;

@Parcel
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
        return imageUrl;
    }

    @Override
    public String userFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String userEmail() {
        return email;
    }
}
