package longh.dev.mvppattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import longh.dev.mvppattern.constract.ConstractListUser;
import longh.dev.mvppattern.R;
import longh.dev.mvppattern.data.model.User;
import longh.dev.mvppattern.persenter.PresenterListUser;
import longh.dev.mvppattern.view.adapter.UserAdapter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListUserActivity extends AppCompatActivity implements ConstractListUser.IView{
    ConstractListUser.IPresenter mIPresenter;
    UserAdapter mAdapter;
    ListView lvUser;
    TextView mTextViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_user);
        init();

        mTextViewResult = findViewById(R.id.textView);
    }
    void init(){
        lvUser = findViewById(R.id.lv_user);
        mIPresenter = new PresenterListUser(this);
        mIPresenter.loadData();
    }
    @Override
    public void showDeleteSuccess() {

    }

    @Override
    public void showDeleteFailed() {

    }

    @Override
    public void updateUserList(List<User> userList) {
        mAdapter = new UserAdapter(this, userList);
        lvUser.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

}