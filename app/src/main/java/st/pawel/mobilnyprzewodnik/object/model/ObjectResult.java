package st.pawel.mobilnyprzewodnik.object.model;

import com.google.gson.annotations.SerializedName;

import java.util.Iterator;
import java.util.List;

public class ObjectResult implements Iterable<ObjectModel> {

    private static final Iterator<ObjectModel> EMPTY = new Iterator<ObjectModel>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public ObjectModel next() {
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
    List<ObjectModel> objectModels;
    @Override
    public Iterator<ObjectModel> iterator() {
        return objectModels == null ? EMPTY : objectModels.iterator();
    }
}

