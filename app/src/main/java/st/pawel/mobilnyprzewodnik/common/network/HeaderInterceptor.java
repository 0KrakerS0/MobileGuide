package st.pawel.mobilnyprzewodnik.common.network;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HeaderInterceptor implements Interceptor{

    HeaderInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request()
                .newBuilder()
                .addHeader(RestApi.Header.CONTENT_TYPE, "application/json")
                .addHeader(RestApi.Header.X_PARSE_APPLICATION_ID, "lJ6yLyTXIeXrTcaQK13liC6PN5fEyFnTjio3unhT")
                .addHeader(RestApi.Header.X_PARSE_REST_API_KEY, "jbZEtb2Xa7IRDMhVsKFXdCHlnZbyomcmQ0kPcH7j");
        return chain.proceed(builder.build());
    }

    //TODO zastapic daggerem
    public static HeaderInterceptor newInstance(){
        return new HeaderInterceptor();
    }
}
