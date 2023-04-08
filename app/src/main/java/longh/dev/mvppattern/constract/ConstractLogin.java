package longh.dev.mvppattern.constract;

import android.widget.Adapter;

public interface ConstractLogin {
    interface IView{
        void showloginsuccess();
        void showloginFailed();
        void intentForgotPass();

        void showSignUpSuccess();
    }
    interface IPresenter{
        void dologin(String email, String pass);
        void forgotpass();
        void signUP();
    }
}
