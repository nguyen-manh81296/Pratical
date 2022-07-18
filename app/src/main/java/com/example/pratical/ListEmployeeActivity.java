package com.example.pratical;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pratical.adapter.EmployeeAdapter;
import com.example.pratical.database.AppDatabase;
import com.example.pratical.database.Employee;

import java.util.List;

public class ListEmployeeActivity extends AppCompatActivity {
    AppDatabase appDatabase;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.getAppDatabase(this);
        List<Employee> list = appDatabase.employeeDao().getAllEmployee();
        EmployeeAdapter adapter = new EmployeeAdapter(this,list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);



    }
}
