package com.example.pratical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pratical.adapter.EmployeeAdapter;
import com.example.pratical.database.AppDatabase;
import com.example.pratical.database.Employee;
import com.example.pratical.util.ItemClickListener;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText iName,iDes,iSa;
    Button addBtn,btnReUpdate,deleteBtn;
    AppDatabase db;
    EmployeeAdapter adapter;
    Employee employee;
    RecyclerView recyclerView;
    List<Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = AppDatabase.getAppDatabase(this);

        iName = findViewById(R.id.iName);
        iDes = findViewById(R.id.iDes);
        iSa = findViewById(R.id.iSa);


        addBtn = findViewById(R.id.addBtn);
        addBtn.setOnClickListener(this);
        btnReUpdate = findViewById(R.id.btnReUpdate);
        btnReUpdate.setOnClickListener(this);
        deleteBtn = findViewById(R.id.deleteBtn);
        deleteBtn.setOnClickListener(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        employees = db.employeeDao().getAllEmployee();
        adapter.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                employee = employees.get(position);
                getInfoEmployee(employee);

            }
        });
    }

@SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addBtn:
                add();
                break;
            case R.id.btnReUpdate:
                update();
                break;
            case R.id.deleteBtn:
                delete();
                break;
            default:
                break;
        }
    }
    private void add() {
        int salary = Math.max(Integer.parseInt(iSa.getText().toString()), 0);
        employee = new Employee(iName.getText().toString(), iDes.getText().toString(), salary);
        long id = db.employeeDao().insert(employee);
        if (id > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            reloadData();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

        }

    }
    private void update() {
        if (employee == null) {
            Toast.makeText(this, "No Employee", Toast.LENGTH_SHORT).show();
            return;
        }
        employee.setUsername(iName.getText().toString());
        employee.setDesignation(iDes.getText().toString());
        employee.setSalary(Integer.parseInt(iSa.getText().toString()));
        int id = db.employeeDao().update(employee);
        if (id > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            reloadData();

        }
    }

    private void delete() {
        if (employee == null) {
            Toast.makeText(this, "No Employee", Toast.LENGTH_SHORT).show();
            return;
        }
        if (db.employeeDao().delete(employee) > 0) {
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
            reloadData();
        }
    }



    @SuppressLint("NotifyDataSetChanged")
    public void reloadData() {
        employees = db.employeeDao().getAllEmployee();
        adapter.reloadData(employees);
        adapter.notifyDataSetChanged();
        reset();

    }

    @SuppressLint("SetTextI18n")
    public void getInfoEmployee(Employee employee) {
        iName.setText(employee.getUsername());
        iDes.setText(employee.getDesignation());
        iSa.setText(employee.getSalary()+"");
    }

    public void reset() {
        employee = null;
        iName.setText("");
        iSa.setText("");
        iDes.setText("");
    }
}