package longh.dev.mvppattern.persenter;

import android.content.Context;

import longh.dev.mvppattern.constract.ConstractUpdate;
import longh.dev.mvppattern.data.Database;
import longh.dev.mvppattern.data.DatabaseDao;
import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.model.User;

public class PresenterUpdate implements ConstractUpdate.IPresenter {
    ConstractUpdate.IView mIView;

    public PresenterUpdate(ConstractUpdate.IView mIView) {
        this.mIView = mIView;
        DatabaseDao.init(new Database((Context) mIView));
    }

    @Override
    public void doUpate(int id, String user_name, String pass) {
        UserDao userDao = DatabaseDao.getInstance().getUserDao();
        if(userDao.update(new User(id,user_name,pass)) == true){
            mIView.showSuccessUpdate();
        }else mIView.showFailedUpdate();
    }
}
