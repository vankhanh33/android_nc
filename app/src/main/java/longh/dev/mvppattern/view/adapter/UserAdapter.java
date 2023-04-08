package longh.dev.mvppattern.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

import longh.dev.mvppattern.view.UpdateActivity;
import longh.dev.mvppattern.constract.ConstractListUser;
import longh.dev.mvppattern.R;
import longh.dev.mvppattern.data.model.User;
import longh.dev.mvppattern.persenter.PresenterListUser;

public class UserAdapter extends BaseAdapter {
    Context context;
    List<User> mListUsers;
    ConstractListUser.IPresenter mIPresenter;

    public UserAdapter(Context context, List<User> mListUsers) {
        this.context = context;
        this.mListUsers = mListUsers;
        mIPresenter = new PresenterListUser((ConstractListUser.IView) context);
    }

    @Override
    public int getCount() {
        return mListUsers.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.row_listuser,null);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtUser = row.findViewById(R.id.textView_Username);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView txtPass = row.findViewById(R.id.textView_Password);
        TextView txtID = row.findViewById(R.id.textView_ID);
        ImageButton btnDelete = row.findViewById(R.id.btn_delete);
        ImageButton btn_Update = row.findViewById(R.id.btn_edit);
        User user = mListUsers.get(i);
        txtUser.setText(user.getEmail());
        txtID.setText(user.getId()+"");
        txtPass.setText(user.getPassword());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa tài khoản?");
                builder.setMessage("Bạn muốn xóa tài khoản này không?");
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mListUsers.remove(i);
                        mIPresenter.deleteItem(user.getId());
                    }
                });
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.create().show();
                notifyDataSetChanged();
            }
        });
        btn_Update.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("user",user);
                row.getContext().startActivity(intent);
            }
        });
        return row;
    }
}
