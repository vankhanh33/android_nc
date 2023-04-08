package longh.dev.mvppattern.constract;

public interface ConstractSignup {
    interface IView{
        void showSignUpSuccess();
        void showSignUpFailed();

        void showLogin();
    }
    interface IPresenter{
        void doSignup(String email, String pass);
        void intentLogin();
    }
}
