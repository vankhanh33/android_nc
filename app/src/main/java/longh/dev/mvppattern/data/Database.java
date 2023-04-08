package longh.dev.mvppattern.data;

import android.content.Context;

import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.impl.UserDaoImpl;

public class Database extends DatabaseDao{
    Context context;

    public Database(Context context) {
        this.context = context;
    }

    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl(context);
    }
}
