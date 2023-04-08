package longh.dev.mvppattern.persenter;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import longh.dev.mvppattern.constract.ConstractListUser;
import longh.dev.mvppattern.data.remote.RestApi;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PresenterListUser implements ConstractListUser.IPresenter {
    ConstractListUser.IView mIView;

    public PresenterListUser(ConstractListUser.IView mIView) {
        this.mIView = mIView;
        DatabaseDao.init(new Database((Activity) mIView));
    }

    @Override
    public void deleteItem(int id) {
        UserDao mUserDao = DatabaseDao.getInstance().getUserDao();
        if(mUserDao.delete(id) == true){
            mIView.showDeleteSuccess();
        }else mIView.showDeleteFailed();
    }

    @Override
    public void loadData() {
        new GetDataTask().execute();
    }
    public class GetDataTask extends AsyncTask<Void, Void, List<User>> {
        private static final String URL_ACCOUNTS = "http://lunaduca-001-site1.dtempurl.com/account.php";
        private final OkHttpClient client = new OkHttpClient();
        private final Gson gson = new Gson();

        // phương thức này sẽ được gọi trên một thread khác
        @Override
        protected List<User> doInBackground(Void... voids) {
            // tạo một request để gửi đến server
            Request request = new Request.Builder().url(URL_ACCOUNTS).build();

            try {
                // gửi request đi và nhận lại response từ server
                Response response = client.newCall(request).execute();

                // kiểm tra response có thành công không
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                // chuyển đổi dữ liệu từ response body sang List<User> bằng Gson
                Type listType = new TypeToken<List<User>>(){}.getType();
                String responseBodyJson = response.body().string();
                List<User> mList = gson.fromJson(responseBodyJson, listType);
                return mList;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        // phương thức được gọi khi background task đã thực hiện xong, đối số accounts chính là kết quả trả về từ doInBackground
        @Override
        protected void onPostExecute(List<User> mList) {
            if (mList != null) {
                // sử dụng danh sách accounts để hiển thị lên giao diện
                // ví dụ: gán danh sách accounts cho một Adapter, rồi gán Adapter đó cho một ListView, RecyclerView, hay ListView
                // adapter.setData(accounts);
                // listView.setAdapter(adapter);
                mIView.updateUserList(mList);
            } else {
                Toast.makeText((Activity) mIView, "Failed to fetch accounts", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
