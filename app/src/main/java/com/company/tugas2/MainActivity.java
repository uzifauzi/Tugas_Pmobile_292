package com.company.tugas2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.animation.AccelerateInterpolator;
import android.widget.Toast;

import com.company.tugas2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MyDatabase myDb;
    UserDao userDao;
    public boolean isAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        myDb = Room.databaseBuilder(this, MyDatabase.class, "usertable").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        userDao = myDb.getDao();

        binding.nama.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String nama = editable.toString();
                if (userDao.isTaken(nama)){
                    isAllowed = false;
                    Toast.makeText(MainActivity.this,"Nama sudah pernah didaftarkan", Toast.LENGTH_SHORT).show();
                } else {
                    isAllowed = true;

                }

            }
        });

//        code untuk berhasil sign up
    }
}