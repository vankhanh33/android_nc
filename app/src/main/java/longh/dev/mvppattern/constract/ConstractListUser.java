package longh.dev.mvppattern.constract;

import android.widget.Adapter;

import java.util.List;

import longh.dev.mvppattern.data.model.User;

public interface ConstractListUser {
    interface IView{
        void showDeleteSuccess();
        void showDeleteFailed();

        void updateUserList(List<User> userList);
    }
    interface IPresenter{
        void deleteItem(int id);

        void loadData();
    }
}
