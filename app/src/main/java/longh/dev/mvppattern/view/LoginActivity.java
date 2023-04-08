package longh.dev.mvppattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import longh.dev.mvppattern.constract.ConstractLogin;
import longh.dev.mvppattern.R;
import longh.dev.mvppattern.persenter.PresenterLogin;

public class LoginActivity extends AppCompatActivity implements ConstractLogin.IView {

    private ConstractLogin.IPresenter iPresenter;

    private EditText edtemail, edtpass;
    TextView txtQMK, txtSignup;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initgui();
    }

    public void initgui() {
        edtemail = findViewById(R.id.textUser);
        edtpass = findViewById(R.id.textPassword);
        btnlogin = findViewById(R.id.buttonLogin);
        iPresenter = new PresenterLogin(this);
        txtSignup = findViewById(R.id.textSignup);
        txtQMK = findViewById(R.id.textQuenMK);
        btnlogin.setOnClickListener(view -> {
            String user = edtemail.getText().toString();
            String pass = edtpass.getText().toString();
            iPresenter.dologin(user, pass);
        });
        txtSignup.setOnClickListener(view-> {
                iPresenter.signUP();
        });
    }

    @Override
    public void showloginsuccess() {
        Toast.makeText(LoginActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,ListUserActivity.class));
    }

    @Override
    public void showloginFailed() {
        Toast.makeText(LoginActivity.this, "Login Failes", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void intentForgotPass() {
        startActivity(new Intent(this,SignupActivity.class));
    }

    @Override
    public void showSignUpSuccess() {
        startActivity(new Intent(this,SignupActivity.class));
    }


}