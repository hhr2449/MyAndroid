package com.java.huhaoran;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.java.huhaoran.note.UserNote;

public class RegisterActivity extends AppCompatActivity {

    private EditText username, password, confirm;
    private Button registerButton, toLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //登录，注册界面的退出键
        ImageView register_back = findViewById(R.id.register_back_button);
        register_back.setOnClickListener(v -> {
            finish();
        });


        username = findViewById(R.id.register_username);
        password = findViewById(R.id.register_password);
        confirm = findViewById(R.id.register_password_again);
        registerButton = findViewById(R.id.btn_register);
        toLoginButton = findViewById(R.id.btn_to_login);
        toLoginButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(v -> {
            String st_username = username.getText().toString().trim();
            String st_password = password.getText().toString();
            String st_confirm = confirm.getText().toString();

            if (!st_password.equals(st_confirm)) {
                Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                return;
            }

            new Thread(() -> {
                boolean exists = AppDatabase.getInstance(getApplicationContext()).userDao().existsByUserName(st_username);
                if (exists) {
                    runOnUiThread(() ->
                            Toast.makeText(this, "用户名已存在", Toast.LENGTH_SHORT).show());
                } else {
                    AppDatabase.getInstance(getApplicationContext()).userDao().insert(new UserNote(st_username, st_password));
                    runOnUiThread(() -> {
                        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
                        UserManager.setCurrentUserName(st_username);
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    });
                }
            }).start();
        });
    }
}
