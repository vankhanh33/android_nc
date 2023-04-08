package longh.dev.mvppattern.test;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection extends AsyncTask<String,Void, Account> {
    @Override
    protected Account doInBackground(String... strings) {
        try {
            // Tạo URL
            URL url = new URL(strings[0]);

            // Mở kết nối
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Đọc dữ liệu từ kết nối
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
            in.close();

            // Phân tích kết quả JSON và tạo đối tượng Account
            JSONObject jsonObject = new JSONObject(response.toString());
            int id = jsonObject.getInt("id");
            String username = jsonObject.getString("username");
            String password = jsonObject.getString("password");
            Account account = new Account(id, username, password);

            // Trả về đối tượng Account
            return account;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}
