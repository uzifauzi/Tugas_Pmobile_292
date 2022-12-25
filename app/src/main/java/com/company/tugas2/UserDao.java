package com.company.tugas2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Insert
    void insertUser(UserTable userTable);

//    mengecek apakah data nama sudah pernah didaftarkan/belum
    @Query("SELECT EXISTS(SELECT * FROM usertable WHERE nama=:nama)")
    boolean isTaken(String nama);

//    fungsi login menggunakan nim dan password
    @Query("SELECT EXISTS(SELECT nim, password FROM UserTable WHERE nim=:nim AND password=:password)")
    boolean login(String nim, String password);
}
