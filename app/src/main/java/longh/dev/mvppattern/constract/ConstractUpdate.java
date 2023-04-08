package longh.dev.mvppattern.constract;

public interface ConstractUpdate {
    interface IView{
        void showSuccessUpdate();
        void showFailedUpdate();

    }
    interface IPresenter{
        void doUpate(int id,String user_name, String pass);
    }
}
