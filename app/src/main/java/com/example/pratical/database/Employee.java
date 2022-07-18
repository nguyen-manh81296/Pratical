package com.example.pratical.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employees")
public class Employee {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "username")
    public String username;

    @ColumnInfo(name = "designation")
    public String designation;

    @ColumnInfo(name = "salary")
    public int salary;

    public Employee(String s, String toString, int salary) {
    }

    public Employee(int id, String username, String designation, int salary) {
        this.id = id;
        this.username = username;
        this.designation = designation;
        this.salary = salary;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
}
