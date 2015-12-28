package st.pawel.mobilnyprzewodnik.common.network;


public interface RestApi {

    interface Header {

        String CONTENT_TYPE="Content-Type";
        String X_PARSE_APPLICATION_ID = "X-Parse-Application-Id";
        String X_PARSE_REST_API_KEY = "X-Parse-REST-API-Key";
    }

    interface HeaderValue {

    }
    interface Endpoint {
        String PREFIX_ONE_CLASSES = "/1/classes";
        String CLASSES_CITY = PREFIX_ONE_CLASSES +"/City";
    }
}
