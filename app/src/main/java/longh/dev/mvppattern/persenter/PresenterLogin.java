package longh.dev.mvppattern.persenter;

import android.app.Activity;
import android.widget.Adapter;

import longh.dev.mvppattern.constract.ConstractLogin;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.view.adapter.UserAdapter;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;

public class PresenterLogin implements ConstractLogin.IPresenter {
    private ConstractLogin.IView mView;
    public PresenterLogin(ConstractLogin.IView view){
        mView = view;
        DatabaseDao.init(new Database((Activity) mView));
    }
    @Override
    public void dologin(String email, String pass) {
        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        User user = userDao.find(email, pass);
        if(user == null){
            mView.showloginFailed();
        }else{
            mView.showloginsuccess();
        }

    }

    @Override
    public void forgotpass() {

    }

    @Override
    public void signUP() {
        mView.showSignUpSuccess();
    }


}
