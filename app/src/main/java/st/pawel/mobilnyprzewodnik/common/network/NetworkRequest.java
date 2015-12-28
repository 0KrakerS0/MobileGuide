package st.pawel.mobilnyprzewodnik.common.network;

import retrofit.Call;
import retrofit.Retrofit;

public abstract class NetworkRequest<RESULT, CLIENT> {

    Class<CLIENT> clientClass;

    Retrofit retrofit;

    protected NetworkRequest(Class<CLIENT> clientClass) {
        this.clientClass = clientClass;
        retrofit = RetrofitProvider.retrofit();
    }

    protected CLIENT client(){
        return retrofit.create(clientClass);
    }

    protected abstract Call<RESULT> request();
}
