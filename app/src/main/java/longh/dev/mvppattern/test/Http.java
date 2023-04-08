package longh.dev.mvppattern.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import longh.dev.mvppattern.R;

public class Http extends AppCompatActivity  {

    private EditText edtemail, edtpass;
    TextView txtQMK, txtSignup;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        HttpConnection httpGetJson = new HttpConnection();
        Account account = null;
        try {
            account = httpGetJson.execute("http://lunaduca-001-site1.dtempurl.com/account.php").get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
//
//// Sử dụng thông tin tài khoản trong đối tượng "account"
        int id = account.getId();
        String username = account.getUsername();
        String password = account.getPassword();
        initgui();
    }

    public void initgui() {
        edtemail = findViewById(R.id.textUser);
        edtpass = findViewById(R.id.textPassword);
        btnlogin = findViewById(R.id.buttonLogin);
        txtSignup = findViewById(R.id.textSignup);
        txtQMK = findViewById(R.id.textQuenMK);

    }
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
}
