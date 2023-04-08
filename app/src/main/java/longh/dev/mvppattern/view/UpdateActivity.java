package longh.dev.mvppattern.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import longh.dev.mvppattern.constract.ConstractUpdate;
import longh.dev.mvppattern.R;
import longh.dev.mvppattern.data.model.User;
import longh.dev.mvppattern.persenter.PresenterUpdate;

public class UpdateActivity extends AppCompatActivity implements ConstractUpdate.IView {
    EditText usertxt,passtxt;
    TextView idtxt;
    ConstractUpdate.IPresenter mIPresenter;
    Button btnupdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        initGUI();
    }
    void initGUI(){
        usertxt = findViewById(R.id.textUserIF);
        passtxt = findViewById(R.id.textPasswordIF);
        idtxt = findViewById(R.id.textIdIF);
        btnupdate = findViewById(R.id.buttonUpdate);
        User user = (User) getIntent().getSerializableExtra("user");
        idtxt.setText(user.getId()+"");
        usertxt.setText(user.getEmail());
        passtxt.setText(user.getPassword());
        mIPresenter = new PresenterUpdate(this);
        btnupdate.setOnClickListener(view -> {
                String usern_name = usertxt.getText().toString();
                String pass = passtxt.getText().toString();
                mIPresenter.doUpate(user.getId(),usern_name,pass);
        });
    }


    @Override
    public void showSuccessUpdate() {
        Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
        startActivity( new Intent(this,ListUserActivity.class));
    }

    @Override
    public void showFailedUpdate() {
        Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
    }
}