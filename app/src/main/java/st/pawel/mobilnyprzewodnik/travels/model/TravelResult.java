package st.pawel.mobilnyprzewodnik.travels.model;

import com.google.gson.annotations.SerializedName;
import java.util.Iterator;
import java.util.List;
import org.parceler.Parcel;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;

@Parcel
public class TravelResult implements Iterable<TravelModel> {

    private static final Iterator<TravelModel> EMPTY = new Iterator<TravelModel>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public TravelModel next() {
            return null;
        }

        @Override
        public void remove() {

        }
    };

    private interface Metadata{
        String RESULTS = "results";
    }

    @SerializedName(Metadata.RESULTS)
    List<TravelModel> travelModels;
    @Override
    public Iterator<TravelModel> iterator() {
        return travelModels == null ? EMPTY : travelModels.iterator();
    }
}

