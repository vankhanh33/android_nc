package longh.dev.mvppattern.data.dao;

import java.util.List;

import longh.dev.mvppattern.data.model.User;

public interface UserDao {

    boolean insert(User user);
    boolean update(User user);
    boolean delete(int id);
    User find(int id);
    User find(String email, String password);
    List<User> findAll();
}
