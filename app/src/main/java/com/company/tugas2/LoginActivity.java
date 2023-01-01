package com.company.tugas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.company.tugas2.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    MyDatabase myDb;
    UserDao userDao;

//    shared preference
    EditText nim, password;
    Button loginBtn;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nim = binding.nim.getText().toString();
                String password = binding.password.getText().toString();
                if (userDao.login(nim, password)){
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                } else{
                    Toast.makeText(LoginActivity.this,"NIM atau Password yang diinputkan salah!", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        menyimpan variable inputan
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        editor = preferences.edit();

        if (preferences.contains("savedNim")){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        } else{
            nim = findViewById(R.id.nim);
            password = findViewById(R.id.password);
            loginBtn = findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String myNim = nim.getText().toString();
                    String myPassword = password.getText().toString();
                    editor.putString("savedNim", myNim);
                    editor.putString("savedPassword", myPassword);
                    editor.commit();
                    Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                    startActivity(intent);

                }
            });
        }

        binding.goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });

    }
}