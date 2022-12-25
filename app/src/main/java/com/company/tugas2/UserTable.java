package com.company.tugas2;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

//membuat model dari table database
@Entity(tableName = "usertable")
public class UserTable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nama;
    private String nim;
    private String password;

    public UserTable(int id, String nama, String nim, String password) {
        this.id = id;
        this.nama = nama;
        this.nim = nim;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
