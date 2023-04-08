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

import longh.dev.mvppattern.constract.Api;
import longh.dev.mvppattern.constract.ConstractListUser;
import longh.dev.mvppattern.data.remote.Constants;
import longh.dev.mvppattern.data.remote.RestApi;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PresenterListUser implements ConstractListUser.IPresenter {
    ConstractListUser.IView mIView;

    public PresenterListUser(ConstractListUser.IView mIView) {
        this.mIView = mIView;
        DatabaseDao.init(new Database((Activity) mIView));
    }

    @Override
    public void deleteItem(int id) {
        UserDao mUserDao = DatabaseDao.getInstance().getUserDao();
        if (mUserDao.delete(id) == true) {
            mIView.showDeleteSuccess();
        } else mIView.showDeleteFailed();
    }

    @Override
    public void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Constants.API_URL).addConverterFactory(GsonConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        api.getAllAccounts().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, retrofit2.Response<List<User>> response) {
                if (response.isSuccessful()) {
                    List<User> mList = response.body();
                    mIView.updateUserList(mList);
                    // xử lý danh sách account
                } else {
                    // xử lý khi response không thành công
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText((Activity) mIView, "Xảy ra lỗi", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
