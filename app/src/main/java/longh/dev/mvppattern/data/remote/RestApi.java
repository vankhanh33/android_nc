package longh.dev.mvppattern.data.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestApi {
//    private static final String BASE_URL = "https://example.com/"; // thay BASE_URL bằng địa chỉ server của bạn
//    private static final String JOB_PATH = "json.php";

    public static String getJsonString(String url) throws IOException {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String resultJsonString = null;
        // Tạo URL kết nối
        URL requestUrl = new URL(url);

        try {
            // Kết nối đến URL
            urlConnection = (HttpURLConnection) requestUrl.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Đọc dữ liệu từ response
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line + "\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            resultJsonString = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return resultJsonString;
    }
}
