package com.chadb.htctest.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chadb.htctest.R;
import com.chadb.htctest.data.dto.EmployeeDTO;
import com.chadb.htctest.databinding.EmployeeListItemBinding;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {

    private List<EmployeeDTO> employeeList;

    public EmployeeAdapter(List<EmployeeDTO> employeeList) {
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        EmployeeListItemBinding binding = DataBindingUtil
            .inflate(inflater, R.layout.employee_list_item, viewGroup, false);
        return new EmployeeViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder viewHolder, int i) {
        final EmployeeDTO employee = employeeList.get(i);
        viewHolder.bind(employee);
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private final EmployeeListItemBinding binding;

        public EmployeeViewHolder(EmployeeListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(EmployeeDTO employee) {
            binding.setEmployee(employee);
            binding.executePendingBindings();
        }
    }
}
