package st.pawel.mobilnyprzewodnik.common.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import java.util.List;

import retrofit.CallAdapter;
import retrofit.Converter;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

//TODO zastapic modulem w daggerze
public class RetrofitProvider {

    static Retrofit retrofit;

    public static Retrofit retrofit(){
        if(retrofit == null){
            retrofit = provideRetrofit();
        }
        return retrofit;
    }
    static Retrofit provideRetrofit() {
        return provideRetrofitBuilder().build();
    }

    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit
                .Builder()
                .baseUrl("https://api.parse.com")
                .client(provideOkHttpClient())
                .addConverterFactory(provideConverterFactory())
                .addCallAdapterFactory(provideRxCallAdapterFactory());
    }

    static  HttpUrl.Builder provideHttpUrlBuilder() {
        return new HttpUrl.Builder();
    }

    static  OkHttpClient provideOkHttpClient() {
        final OkHttpClient result = new OkHttpClient();
        final List<Interceptor> interceptors = result.interceptors();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        interceptors.add(HeaderInterceptor.newInstance());
        interceptors.add(interceptor);
        return result;
    }


    static  CallAdapter.Factory provideRxCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }


    static Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create(provideGson());
    }

    static  Gson provideGson() {
        return provideGsonBuilder().create();
    }

    static  GsonBuilder provideGsonBuilder() {
        return new GsonBuilder();
    }
}
