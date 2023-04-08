package longh.dev.mvppattern.persenter;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import longh.dev.mvppattern.constract.ConstractListUser;
import longh.dev.mvppattern.data.remote.RestApi;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;

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
    private class GetDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String jsonString = null;

            try {
                jsonString = RestApi.getJsonString("http://lunaduca-001-site1.dtempurl.com/account.php");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonString;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            List<User> userList = new ArrayList<>();
            try {
                JSONArray jsonArray = new JSONArray(result);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject arrObject = jsonArray.getJSONObject(i);
                    userList.add(new User(arrObject.getInt("id"), arrObject.getString("user_name"), arrObject.getString("password")));
                }
            }catch (JSONException e){
                e.printStackTrace();
            }
            mIView.updateUserList(userList);
        }
        // Hiển thị kết quả từ json.php vào TextView

    }
}
