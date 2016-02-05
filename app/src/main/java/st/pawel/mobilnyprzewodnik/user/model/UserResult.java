package st.pawel.mobilnyprzewodnik.user.model;


import com.google.gson.annotations.SerializedName;
import java.util.Iterator;
import java.util.List;

public class UserResult implements Iterable<User> {

    private static final Iterator<User> EMPTY = new Iterator<User>() {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public User next() {
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
    List<User> userList;

    @Override
    public Iterator<User> iterator() {
        return userList == null ? EMPTY : userList.iterator();
    }

    public boolean isEmpty() {
        return userList == null || userList.isEmpty();
    }

    public User getFirst() {
        return userList.get(0);
    }

    public int size(){
        return userList != null ? userList.size() : 0;
    }
}
