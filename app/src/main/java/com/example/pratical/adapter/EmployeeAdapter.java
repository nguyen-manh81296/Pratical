package com.example.pratical.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pratical.R;
import com.example.pratical.database.Employee;
import com.example.pratical.util.ItemClickListener;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter {
    private Activity activity;
    private List<Employee> listEmployee;
    private ItemClickListener itemClickListener;


    public void setItemClickListener(ItemClickListener clickListener) {
        this.itemClickListener = clickListener;
    }
    public EmployeeAdapter(Activity activity, List<Employee> listEmployee) {
        this.activity = activity;
        this.listEmployee = listEmployee;
    }
    public void reloadData(List<Employee> listEmployee) {
        this.listEmployee = listEmployee;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_user, parent, false);
        EmployeeHolder holder = new EmployeeHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeHolder vh = (EmployeeHolder) holder;
        Employee model = listEmployee.get(position);

        vh.iName.setText(model.getUsername() + "");
        vh.iDes.setText(model.getDesignation());
        vh.iSa.setText(model.getSalary());
    }

    @Override
    public int getItemCount() {
        return listEmployee.size();
    }

    public class EmployeeHolder extends RecyclerView.ViewHolder  {
        TextView iName, iDes, iSa;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onClick(view,getAdapterPosition(),false);
                }
            });
            iName = itemView.findViewById(R.id.iName);
            iDes = itemView.findViewById(R.id.iDes);
            iSa = itemView.findViewById(R.id.iSa);
        }

    }
}
