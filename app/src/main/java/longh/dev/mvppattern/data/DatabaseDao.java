package longh.dev.mvppattern.data;

import longh.dev.mvppattern.data.dao.UserDao;

public abstract class DatabaseDao {
    private static DatabaseDao instance;

    public static void init(DatabaseDao inst){
        instance = inst;
    }
    public static DatabaseDao getInstance(){
        return instance;
    }
    public abstract UserDao getUserDao();
}
