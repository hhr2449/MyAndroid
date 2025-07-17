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

public class LoginActivity extends AppCompatActivity {

    private EditText username, password;
    private Button loginButton, toRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        //登录，注册界面的退出键
        ImageView login_back = findViewById(R.id.login_back_button);
        login_back.setOnClickListener(v -> {
            finish();
        });

        username = findViewById(R.id.login_username);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.btn_login);
        toRegisterButton = findViewById(R.id.btn_to_register);
        toRegisterButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        loginButton.setOnClickListener(v -> {
            String userName = username.getText().toString();
            String passWord = password.getText().toString();

            new Thread(() -> {
                UserNote user = AppDatabase.getInstance(this).userDao().queryByUserName(userName);

                runOnUiThread(() -> {
                    if(user != null && user.password.equals(passWord)) {
                        UserManager.setCurrentUserName(userName);
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                    }

                });
            }).start();
        });

    }


}
