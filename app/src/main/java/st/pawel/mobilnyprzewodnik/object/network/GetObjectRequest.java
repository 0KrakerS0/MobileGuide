package st.pawel.mobilnyprzewodnik.object.network;

import retrofit.Call;
import st.pawel.mobilnyprzewodnik.common.network.NetworkRequest;
import st.pawel.mobilnyprzewodnik.object.model.ObjectResult;

public class GetObjectRequest extends NetworkRequest<ObjectResult, ObjectClient>{

    public static GetObjectRequest instance(){
        return new GetObjectRequest();
    }

    GetObjectRequest(){
        super(ObjectClient.class);
    }
    @Override
    public Call<ObjectResult> request() {
        return client().getClassesObject();
    }
}