package longh.dev.mvppattern.persenter;

import android.app.Activity;

import longh.dev.mvppattern.constract.ConstractSignup;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;

public class PresenterSignup implements ConstractSignup.IPresenter {
    private ConstractSignup.IView mIView;

    public PresenterSignup(ConstractSignup.IView mIView) {
        this.mIView = mIView;
        DatabaseDao.init(new Database((Activity) mIView));
    }

    @Override
    public void doSignup(String email, String pass) {
       UserDao userDao = Database.getInstance().getUserDao();
        if(userDao.insert(new User(email,pass)) == true){
            mIView.showSignUpSuccess();
        }
        else mIView.showSignUpFailed();
    }

    @Override
    public void intentLogin() {
        mIView.showLogin();
    }
}
