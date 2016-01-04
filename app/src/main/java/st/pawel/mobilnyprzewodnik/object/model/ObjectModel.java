package st.pawel.mobilnyprzewodnik.object.model;

import android.widget.RatingBar;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Setter;
import st.pawel.mobilnyprzewodnik.object.ui.model.ObjectView;


public class ObjectModel implements ObjectView {

    private interface Metadata{
        String OBJECT_IMAGE = "objectImage";

        String OBJECT_NAME ="objectName";

        String OBJECT_TYPE = "objectType";

        String OBJECT_CITY_NAME = "objectCityName";

        String OBJECT_RATE = "objectRate";
    }

    @Setter
    @SerializedName(Metadata.OBJECT_IMAGE)
    String objectImageUrl;

    @Setter
    @SerializedName(Metadata.OBJECT_NAME)
    String objectName;

    @Setter
    @SerializedName(Metadata.OBJECT_TYPE)
    String objectType;

    @Setter
    @SerializedName(Metadata.OBJECT_CITY_NAME)
    String objectCityName;

    @Setter
    @SerializedName(Metadata.OBJECT_RATE)
    String objectRate;

    @Override
    public String objectImageUrl() {
        return objectImageUrl;
    }

    @Override
    public String objectName() {
        return objectName;
    }

    @Override
    public String objectType() {
        return objectType;
    }

    @Override
    public String objectCityName() {
        return objectCityName;
    }

    @Override
    public String objectRate() { return ("Ocena: " + objectRate); }
}