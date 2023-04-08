package longh.dev.mvppattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import longh.dev.mvppattern.constract.ConstractSignup;
import longh.dev.mvppattern.R;
import longh.dev.mvppattern.persenter.PresenterSignup;

public class SignupActivity extends AppCompatActivity implements ConstractSignup.IView {

    private ConstractSignup.IPresenter iPresenter;

    private EditText edtemail, edtpass;
    TextView txtLogin;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initgui();
    }

    public void initgui() {
        edtemail = findViewById(R.id.textUser);
        edtpass = findViewById(R.id.textPassword);
        btnlogin = findViewById(R.id.buttonLogin);
        iPresenter = new PresenterSignup(this);
        btnlogin.setOnClickListener(view -> {
            String user = edtemail.getText().toString();
            String pass = edtpass.getText().toString();
            iPresenter.doSignup(user,pass);
        });
        txtLogin = findViewById(R.id.textLogin);
        txtLogin.setOnClickListener(v -> {
            iPresenter.intentLogin();
        });
    }

    @Override
    public void showSignUpSuccess() {
        Toast.makeText(SignupActivity.this, "SignUp Success", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,LoginActivity.class));
    }

    @Override
    public void showSignUpFailed() {
        Toast.makeText(SignupActivity.this, "SignUp Failes", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showLogin() {
        startActivity(new Intent(this,LoginActivity.class));
    }
}