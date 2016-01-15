package st.pawel.mobilnyprzewodnik.city.model;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class CityResults implements Iterable<CityModel> {

    private static final Iterator<CityModel> EMPTY = new Iterator<CityModel>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public CityModel next() {
            return null;
        }

        @Override
        public void remove() {

        }
    };
    private interface Metadata {
        String RESULTS = "results";
    }

    @SerializedName(Metadata.RESULTS)
    List<CityModel> cityModels;

    @Override
    public Iterator<CityModel> iterator() {
        return cityModels == null ? EMPTY : cityModels.iterator();
    }
}
