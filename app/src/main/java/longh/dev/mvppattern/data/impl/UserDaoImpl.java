package longh.dev.mvppattern.data.impl;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import longh.dev.mvppattern.data.dao.UserDao;
import longh.dev.mvppattern.data.driver.SQLiteDb;
import longh.dev.mvppattern.data.model.User;

public class UserDaoImpl implements UserDao {
    Context context;
    String DB_NAME = "Accountt.db";
    int id = 0;
    SQLiteDatabase mDatabase;

    public UserDaoImpl(Context context) {
        this.context = context;
    }

    @Override
    public boolean insert(User user) {
        mDatabase = SQLiteDb.initDatabase((Activity) context, DB_NAME);
        Cursor cursor = mDatabase.rawQuery("select * from user",null);
        for (int i = 0 ; i < cursor.getCount(); i++ ){
            cursor.moveToPosition(i);
            if(user.getEmail().equals(cursor.getString(1)) || user.getEmail().isEmpty() || user.getPassword().isEmpty()){
                return false;
            }else{
                ContentValues contentValues = new ContentValues();
                contentValues.put("user_name",user.getEmail());
                contentValues.put("password",user.getPassword());
                mDatabase.insert("user",null,contentValues);
                return  true;
            }
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        mDatabase = SQLiteDb.initDatabase((Activity) context,DB_NAME);
        Cursor cursor = mDatabase.rawQuery("select * from user",null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            if(user.getEmail().equals(cursor.getString(1)) && user.getId() != cursor.getInt(0)) return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("user_name",user.getEmail());
        contentValues.put("password",user.getPassword());
        mDatabase.update("user", contentValues,"id =?", new String[]{user.getId()+""});
        return true;
    }

    @Override
    public boolean delete(int id) {
        mDatabase = SQLiteDb.initDatabase((Activity) context, DB_NAME);
        mDatabase.delete("user","id =?",new String[]{id+""});
        return true;
    }

    @Override
    public User find(int id) {
        return null;
    }

    @Override
    public User find(String email, String password) {
        mDatabase = SQLiteDb.initDatabase((Activity) context,DB_NAME);
        Cursor mCursor = mDatabase.rawQuery("select * from user",null);
        for(int i = 0; i < mCursor.getCount(); i++){
            mCursor.moveToPosition(i);
            if(email.equals(mCursor.getString(1)) && password.equals(mCursor.getString(2))){
                return new User(email,password);
            }
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        mDatabase = SQLiteDb.initDatabase((Activity) context, DB_NAME);
        List<User> lstUsers = new ArrayList<>();
        Cursor cursor = mDatabase.rawQuery("select * from user",null);
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            lstUsers.add(new User( cursor.getInt(0),cursor.getString(1), cursor.getString(2)));
        }
        return lstUsers;
    }
}
