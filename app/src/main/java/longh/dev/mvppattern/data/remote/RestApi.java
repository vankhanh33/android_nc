package longh.dev.mvppattern.data.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RestApi {
//    private static final String BASE_URL = "https://example.com/"; // thay BASE_URL bằng địa chỉ server của bạn
//    private static final String JOB_PATH = "json.php";

    public static String getJsonString(String url) throws IOException {
        //HttpURLConnection
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
