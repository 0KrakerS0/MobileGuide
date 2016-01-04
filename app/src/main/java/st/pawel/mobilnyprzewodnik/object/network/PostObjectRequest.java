package st.pawel.mobilnyprzewodnik.object.network;


import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.object.model.ObjectModel;

public class PostObjectRequest extends NetworkRequest<Void, ObjectClient>{

    public static PostObjectRequest instance(){
        return new PostObjectRequest();
    }

    ObjectModel objectModel;

    PostObjectRequest(){
        super(ObjectClient.class);
    }

    public PostObjectRequest withObjectModel(ObjectModel objectModel){
        this.objectModel = objectModel;
        return this;
    }

    @Override
    public Call<Void> request() {
        if (objectModel == null){
            throw new IllegalStateException("Ustaw object model przed wywolaniem requesta");
        }
        return client().postClassesObject(objectModel);
    }
}

