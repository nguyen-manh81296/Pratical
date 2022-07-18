package com.example.pratical.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert
    long insert(Employee employee);

    @Update
    int update(Employee employee);

    @Delete
    int delete(Employee employee);


    @Query("Select * from employees ")
    List<Employee> getAllEmployee();

    @Query("Select * from employees where id = :id")
    Employee findById(int id);
}
